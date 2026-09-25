package com.example.demo.serviceimpl;

import org.springframework.stereotype.Service;

import com.example.demo.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validateRecord(String record) {

        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        String[] data = record.split(",");

        if (data.length < 3) {
            return false;
        }

        if (data[0].trim().isEmpty()) {
            return false;
        }

        if (data[1].trim().isEmpty()) {
            return false;
        }

        if (data[2].trim().isEmpty()) {
            return false;
        }

        return true;
    }

}