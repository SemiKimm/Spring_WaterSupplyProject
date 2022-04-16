package com.nhnacademy.edu.springframework.project.domain;

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
    public String toString() {
        return "WaterBill{" +
            "city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", unitPrice=" + unitPrice +
            ", billTotal=" + billTotal +
            '}';
    }
}
