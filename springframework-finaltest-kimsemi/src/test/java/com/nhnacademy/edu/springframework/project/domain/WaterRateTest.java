package com.nhnacademy.edu.springframework.project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterRateTest {
    WaterRate waterRate;

    @BeforeEach
    void setUp() {
        waterRate = new WaterRate(68,"서산시","가정용",3L,31L,999_999L,1_680L, 0L);
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
        WaterRate test = new WaterRate(68,"서산시","가정용",3L,31L,999_999L,1_680L, 0L);
        assertThat(waterRate)
            .isEqualTo(test)
            .isEqualTo(waterRate);
    }

    @Test
    void equals_fieldIsNotEqual() {
        WaterRate test = new WaterRate(66,"동두천시","공업용",2L,11L,990_000L,1_000, 1L);
        assertThat(waterRate)
            .isNotEqualTo(test);
    }

    @Test
    void equals_null() {
        assertThat(waterRate)
            .isNotEqualTo(null);
    }

    @Test
    void equals_classIsNotEqual() {
        WaterBill test = new WaterBill("서산시", "공업용", 0L,0L);
        assertThat(waterRate)
            .isNotEqualTo(test);
    }

    @Test
    void hashCode_equals() {
        WaterRate test = new WaterRate(68,"서산시","가정용",3L,31L,999_999L,1_680L, 0L);
        int result = waterRate.hashCode();
        assertThat(result).isEqualTo(test.hashCode());
    }
}