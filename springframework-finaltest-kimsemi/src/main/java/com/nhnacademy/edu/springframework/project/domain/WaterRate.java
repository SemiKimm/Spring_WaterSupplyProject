package com.nhnacademy.edu.springframework.project.domain;

import java.util.Objects;

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

    public long getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterRate waterRate = (WaterRate) o;
        return number == waterRate.number && unitPrice == waterRate.unitPrice &&
            Objects.equals(city, waterRate.city) &&
            Objects.equals(sector, waterRate.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, sector, unitPrice);
    }

    @Override
    public String toString() {
        return "WaterRate{" +
            "number=" + number +
            ", city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", unitPrice=" + unitPrice +
            '}';
    }
}
