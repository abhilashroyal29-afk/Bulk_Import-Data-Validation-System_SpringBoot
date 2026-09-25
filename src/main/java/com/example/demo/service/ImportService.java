package com.example.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImportSummaryDto;

public interface ImportService {
	
	String uploadFile(MultipartFile file) throws IOException;
	
	ImportSummaryDto getImportSummary(Long jobId);
	
	String getJobStatus(Long jobId);

}
