package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;

public class BasicReportService implements ReportService {
    private final Log log = LogFactory.getLog(BasicReportService.class);
    @Override
    public void report(@NonNull List<WaterBill> data) {
        for (int i = 0; i < 5; i++) {
            log.info(data.get(i).toString());
        }
    }
}
