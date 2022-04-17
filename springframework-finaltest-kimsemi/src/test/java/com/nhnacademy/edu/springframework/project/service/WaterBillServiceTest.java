package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.repository.BasicTariff;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterBillServiceTest {
    WaterBillService waterBillService;
    Tariff basicTariff;

    @BeforeEach
    void setUp() {
        basicTariff = mock(BasicTariff.class);
        waterBillService = new BasicWaterBillService(basicTariff);
    }

    @Test
    void calculateWaterFee() {
        long consumption = 1_000L;
        WaterBill waterBill = new WaterBill("동두천시", "가정용", 1_530L, 1_530_000L);
        List<WaterRate> waterRateList = givenMockReturn();
        when(basicTariff.findTariffByConsumption(consumption)).thenReturn(waterRateList);

        List<WaterBill> result = waterBillService.calculateWaterFee(consumption);

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result.stream().findFirst().get().getBillTotal())
            .isNotZero()
            .isEqualTo(waterRateList.stream().findFirst().get().getUnitPrice()*consumption);
        assertThat(result).contains(waterBill);
    }

    List<WaterRate> givenMockReturn(){
        List<WaterRate> result = new ArrayList<>();
        WaterRate waterRate = new WaterRate(3,"동두천시","가정용",3,31,999999, 1530, 0L);
        result.add(waterRate);
        return result;
    }
}
