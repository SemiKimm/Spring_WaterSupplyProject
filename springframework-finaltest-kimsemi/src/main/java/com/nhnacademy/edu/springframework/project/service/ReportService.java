package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import java.util.List;

/**
 * 결과리포트 입니다.
 */
public interface ReportService {
    void report(List<WaterBill> data);
}
