package com.nhnacademy.edu.springframework.project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterBillTest {
    WaterBill waterBill;

    @BeforeEach
    void setUp() {
        waterBill = new WaterBill("파주시","가정용",1_110L,1_110_000L);
    }

    @Test
    void getBillTotal() {
        long billTotal = 1_110_000L;
        assertThat(waterBill.getBillTotal()).isEqualTo(billTotal);
    }

    @Test
    void equals() {
        WaterBill test = new WaterBill("파주시","가정용",1_110L,1_110_000L);
        assertThat(waterBill)
            .isEqualTo(test)
            .isEqualTo(waterBill);
    }

    @Test
    void equals_false() {
        WaterBill test = new WaterBill("파주시","공업용",1_110L,1_110_000L);
        assertThat(waterBill.equals(test)).isFalse();
    }

    @Test
    void equals_null() {
        assertThat(waterBill)
            .isNotEqualTo(null);
    }

    @Test
    void equals_classIsNotEqual() {
        WaterRate test = new WaterRate(66,"서산시","가정용",1L,31L,999_999L,1_680L, 0L);
        assertThat(waterBill)
            .isNotEqualTo(test);
    }

    @Test
    void hashCode_equals() {
        WaterBill test = new WaterBill("파주시","가정용",1_110L,1_110_000L);
        int result = waterBill.hashCode();
        assertThat(result).isEqualTo(test.hashCode());
    }
}