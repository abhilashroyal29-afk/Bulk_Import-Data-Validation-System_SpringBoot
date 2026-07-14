package com.example.demo.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImportSummaryDto;
import com.example.demo.entity.ImportJob;
import com.example.demo.exception.DuplicateFileException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ImportJobRespository;
import com.example.demo.repository.ImportRecordRepository;
import com.example.demo.service.FileProcessingService;
import com.example.demo.service.ImportService;

import lombok.extern.slf4j.Slf4j;

@Service

public class ImportServiceImpl implements ImportService{
	
	@Autowired
	private ImportJobRespository importJobRepository;
	@Autowired
	private ImportRecordRepository importRecordRepository;
	@Autowired
	private FileProcessingService fileProcessingService;
	

	@Override
	public String uploadFile(MultipartFile file) throws IOException {

	    if (importJobRepository.existsByFileName(file.getOriginalFilename())) {
	        throw new DuplicateFileException("File Already Imported");
	    }

	    ImportJob job = new ImportJob();
	    job.setFileName(file.getOriginalFilename());
	    job.setStatus("PROCESSING");
	    job = importJobRepository.save(job);

	    Path uploadDir = Paths.get("uploads");
	    Files.createDirectories(uploadDir);

	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    Path filePath = uploadDir.resolve(fileName);

	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	    fileProcessingService.processFile(filePath.toString(), job.getId());
	   

	    return "File Uploaded Successfully";
	}
	

	@Override
	public ImportSummaryDto getImportSummary(Long jobId) {
		
		ImportJob job = importJobRepository.findById(jobId).orElseThrow(()->
		                new ResourceNotFoundException("Job Not Found"));
		ImportSummaryDto summary = new ImportSummaryDto();
		
		summary.setJobId(job.getId());
		summary.setTotalRecords((int) importRecordRepository.countByImportJob(job));
		summary.setSuccessCount((int) importRecordRepository.countByStatus("SUCCESS"));
		summary.setFailedCount((int) importRecordRepository.countByStatus("FAILED"));
		summary.setStatus(job.getStatus());
	
		return summary;
	}

	@Override
	public String getJobStatus(Long jobId) {
		
		ImportJob job =  importJobRepository.findById(jobId).orElseThrow(()->
        new ResourceNotFoundException("Job Not Found"));
		
	
		return job.getStatus();
	}

}
