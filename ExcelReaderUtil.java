package com.example.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelReaderUtil {

    public List<String> readExcel(MultipartFile file) throws IOException {

        List<String> records = new ArrayList<>();

        XSSFWorkbook workbook =
                new XSSFWorkbook(file.getInputStream());

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

            records.add(data.toString());
        }

        workbook.close();

        return records;
    }
}
