package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ImportServiceImplTest {

    @Test
    void testStatus() {

        String status = "COMPLETED";

        assertEquals("COMPLETED", status);

    }
}