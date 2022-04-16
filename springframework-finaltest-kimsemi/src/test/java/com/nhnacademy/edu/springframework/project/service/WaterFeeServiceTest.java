package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springframework.project.domain.WaterFee;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WaterFeeServiceTest {
    WaterFeeService waterFeeService;

    @BeforeEach
    void setUp() {
        waterFeeService = new BasicWaterFeeService();
    }

    @Test
    void calculateWaterFee(){
        long consumption = 1_000L;

        List<WaterFee> result = waterFeeService.calculateWaterFee(consumption);

        assertThat(result).isNotNull();
    }
}
