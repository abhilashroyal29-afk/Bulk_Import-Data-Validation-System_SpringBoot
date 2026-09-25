package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator {

    public boolean isValidFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename().toLowerCase();

        return fileName.endsWith(".csv")
                || fileName.endsWith(".xlsx")
                || fileName.endsWith(".xls");
    }

}