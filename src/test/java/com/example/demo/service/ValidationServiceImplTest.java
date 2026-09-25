package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.demo.serviceimpl.ValidationServiceImpl;

public class ValidationServiceImplTest {

    private ValidationServiceImpl validationService =
            new ValidationServiceImpl();

    @Test
    void testValidRecord() {

        String record = "Abhi,abhi@gmail.com,23";

        assertTrue(validationService.validateRecord(record));

    }

    @Test
    void testInvalidRecord() {

        String record = "Abhi,,23";

        assertFalse(validationService.validateRecord(record));

    }
}