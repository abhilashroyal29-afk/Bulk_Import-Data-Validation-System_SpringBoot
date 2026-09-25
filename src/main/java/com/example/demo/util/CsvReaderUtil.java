package com.example.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvReaderUtil {

    public List<String> readCsv(MultipartFile file) throws IOException {

        List<String> records = new ArrayList<>();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(file.getInputStream()));

        reader.readLine();

        String line;

        while ((line = reader.readLine()) != null) {
            records.add(line);
        }

        reader.close();

        return records;
    }
}