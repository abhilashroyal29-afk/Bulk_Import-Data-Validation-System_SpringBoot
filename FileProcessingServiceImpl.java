package com.example.demo.serviceimpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ImportJob;
import com.example.demo.entity.ImportRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ImportJobRespository;
import com.example.demo.repository.ImportRecordRepository;
import com.example.demo.service.FileProcessingService;
import com.example.demo.service.ValidationService;

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

    @Autowired
    private ImportJobRespository importJobRepository;

    @Autowired
    private ImportRecordRepository importRecordRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    @Async
    public void processFile(String filePath, Long jobId) {

        ImportJob job = importJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));

        List<ImportRecord> records = new ArrayList<>();

        try {

            String fileName = filePath.toLowerCase();

            if (fileName.endsWith(".csv")) {
                processCsv(filePath, job, records);
            } else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                processExcel(filePath, job, records);
            } else {
                throw new RuntimeException("Invalid File Format");
            }

            importRecordRepository.saveAll(records);

            job.setStatus("COMPLETED");
            importJobRepository.save(job);

        } catch (Exception e) {

            e.printStackTrace();

            job.setStatus("FAILED");
            importJobRepository.save(job);

            throw new RuntimeException(e.getMessage());

        }
    }

    private void processCsv(String filePath,
                            ImportJob job,
                            List<ImportRecord> records) throws IOException {

        BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));

        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {

            ImportRecord record = new ImportRecord();

            record.setImportJob(job);
            record.setData(line);

            if (validationService.validateRecord(line)) {

                record.setStatus("SUCCESS");
                record.setErrorMessage(null);

            } else {

                record.setStatus("FAILED");
                record.setErrorMessage("Invalid Record");

            }

            records.add(record);
        }

        reader.close();
    }

    private void processExcel(String filePath,
                              ImportJob job,
                              List<ImportRecord> records) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));

        Sheet sheet = workbook.getSheetAt(0);

        boolean header = true;

        for (Row row : sheet) {

            if (header) {
                header = false;
                continue;
            }

            StringBuilder data = new StringBuilder();

            for (Cell cell : row) {
                data.append(cell.toString()).append(",");
            }

            ImportRecord record = new ImportRecord();

            record.setImportJob(job);
            record.setData(data.toString());

            if (validationService.validateRecord(data.toString())) {

                record.setStatus("SUCCESS");
                record.setErrorMessage(null);

            } else {

                record.setStatus("FAILED");
                record.setErrorMessage("Invalid Record");

            }

            records.add(record);
        }

        workbook.close();
    }

}