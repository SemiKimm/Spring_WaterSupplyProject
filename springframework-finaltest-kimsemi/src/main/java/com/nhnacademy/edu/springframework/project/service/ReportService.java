package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import java.util.List;

public interface ReportService {
    void report(List<WaterBill> data);
}
