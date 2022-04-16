package com.nhnacademy.edu.springframework.project.domain;

import java.util.Objects;

public class WaterRate {
    private final int number;
    private final String city;
    private final String sector;
    private final long level;
    private final long unitStart;
    private final long unitEnd;
    private final long unitPrice;

    public WaterRate(int number, String city, String sector, long level, long unitStart,
                     long unitEnd, long unitPrice) {
        this.number = number;
        this.city = city;
        this.sector = sector;
        this.level = level;
        this.unitStart = unitStart;
        this.unitEnd = unitEnd;
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
        return number == waterRate.number && level == waterRate.level &&
            unitStart == waterRate.unitStart && unitEnd == waterRate.unitEnd &&
            unitPrice == waterRate.unitPrice && Objects.equals(city, waterRate.city) &&
            Objects.equals(sector, waterRate.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, sector, level, unitStart, unitEnd, unitPrice);
    }

    @Override
    public String toString() {
        return "WaterRate{" +
            "number=" + number +
            ", city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", level=" + level +
            ", unitStart=" + unitStart +
            ", unitEnd=" + unitEnd +
            ", unitPrice=" + unitPrice +
            '}';
    }
}
