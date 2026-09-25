package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImportSummaryDto;
import com.example.demo.service.ImportService;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException{

        return ResponseEntity.ok(importService.uploadFile(file));
    }

    @GetMapping("/summary/{jobId}")
    public ResponseEntity<ImportSummaryDto> getImportSummary(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(importService.getImportSummary(jobId));
    }

    @GetMapping("/status/{jobId}")
    public ResponseEntity<String> getJobStatus(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(importService.getJobStatus(jobId));
    }

}
