package com.nhnacademy.edu.springframework.project.domain;

import java.util.Objects;

public class WaterBill {
    private final String city;
    private final String sector;
    private final long unitPrice;
    private final long billTotal;

    public WaterBill(String city, String sector, long unitPrice, long billTotal) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    public long getBillTotal() {
        return this.billTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterBill waterBill = (WaterBill) o;
        return unitPrice == waterBill.unitPrice && billTotal == waterBill.billTotal &&
            Objects.equals(city, waterBill.city) &&
            Objects.equals(sector, waterBill.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, sector, unitPrice, billTotal);
    }

    @Override
    public String toString() {
        return "WaterBill{" +
            "city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", unitPrice=" + unitPrice +
            ", billTotal=" + billTotal +
            '}';
    }
}
