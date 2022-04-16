package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.exception.LackOfDataSizeException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportServiceTest {
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new BasicReportService();
    }

    @Test
    void report(){
        List<WaterBill> data = new ArrayList<>();
        WaterBill waterBill1 = new WaterBill("고령군", "공업용",370L,370_000L);
        WaterBill waterBill2 = new WaterBill("고령군", "공업용",370L,370_000L);
        WaterBill waterBill3 = new WaterBill("고령군", "공업용",370L,370_000L);
        WaterBill waterBill4 = new WaterBill("고령군", "공업용",370L,370_000L);
        WaterBill waterBill5 = new WaterBill("고령군", "공업용",370L,370_000L);
        data.add(waterBill1);
        data.add(waterBill2);
        data.add(waterBill3);
        data.add(waterBill4);
        data.add(waterBill5);
        assertDoesNotThrow(()->reportService.report(data));
    }

    @Test
    void report_dataSizeIsLowerThanFive(){
        List<WaterBill> data = new ArrayList<>();
        WaterBill waterBill1 = new WaterBill("고령군", "공업용",370L,370_000L);
        WaterBill waterBill2 = new WaterBill("고령군", "공업용",370L,370_000L);
        WaterBill waterBill3 = new WaterBill("고령군", "공업용",370L,370_000L);
        data.add(waterBill1);
        data.add(waterBill2);
        data.add(waterBill3);
        assertThatThrownBy(()->reportService.report(data))
            .isInstanceOf(LackOfDataSizeException.class)
            .hasMessageContainingAll("data size is");
    }

    @Test
    void isDataSizeLack_dataSizeIsThree(){
        int dataSize = 3;
        assertThat(reportService.isDataSizeLack(dataSize)).isTrue();
    }

    @Test
    void isDataSizeLack_dataSizeIsFive(){
        int dataSize = 5;
        assertThat(reportService.isDataSizeLack(dataSize)).isFalse();
    }
}
