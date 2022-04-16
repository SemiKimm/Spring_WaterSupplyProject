package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.exception.LackOfDataSizeException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * 기본_결과리포트 입니다.
 */
@Component
public class BasicReportService implements ReportService {
    private static final int DATA_MIN_SIZE = 5;
    private final Log log = LogFactory.getLog(BasicReportService.class);

    @Override
    public void report(@NonNull List<WaterBill> data) {
        int dataSize = data.size();
        if (isDataSizeLack(dataSize)) {
            throw new LackOfDataSizeException("data size is :" + dataSize);
        }
        for (var i = 0; i < 5; i++) {
            log.info(data.get(i).toString());
        }
    }

    @Override
    public boolean isDataSizeLack(int dataSize) {
        return dataSize < DATA_MIN_SIZE;
    }
}
