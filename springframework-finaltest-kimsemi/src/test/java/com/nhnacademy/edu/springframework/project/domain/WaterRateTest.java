package com.nhnacademy.edu.springframework.project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterRateTest {
    WaterRate waterRate;

    @BeforeEach
    void setUp() {
        waterRate = new WaterRate(68,"서산시","가정용",3L,31L,999_999L,1_680L);
    }

    @Test
    void getCity() {
        assertThat(waterRate.getCity()).isEqualTo("서산시");
    }

    @Test
    void getSector() {
        assertThat(waterRate.getSector()).isEqualTo("가정용");
    }

    @Test
    void getUnitPrice() {
        assertThat(waterRate.getUnitPrice()).isEqualTo(1_680L);
    }

    @Test
    void getUnitStart() {
        assertThat(waterRate.getUnitStart()).isEqualTo(31L);
    }

    @Test
    void getUnitEnd() {
        assertThat(waterRate.getUnitEnd()).isEqualTo(999_999L);
    }

    @Test
    void equals() {
        WaterRate waterRate2 = new WaterRate(68,"서산시","가정용",3L,31L,999_999L,1_680L);
        assertThat(waterRate).isEqualTo(waterRate2);
    }
}