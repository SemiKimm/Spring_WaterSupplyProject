package com.nhnacademy.edu.springframework.project.domain;

public class WaterRate {
    private final int number;
    private final String city;
    private final String sector;
    private final long unitPrice;

    public WaterRate(int number, String city, String sector, long unitPrice) {
        this.number = number;
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
    }
}
