package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.BeforeEach;

public class ReportServiceTest {
    ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new BasicReportService();
    }


}
