package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportServiceTest {
    ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new BasicReportService();
    }

    @Test
    void report(){
        List<WaterBill> data = new ArrayList<>();
        assertDoesNotThrow(()->reportService.report(data));
    }
}
