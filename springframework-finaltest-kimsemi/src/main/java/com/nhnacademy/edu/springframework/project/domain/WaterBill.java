package com.nhnacademy.edu.springframework.project.domain;

import java.util.Objects;

/**
 * 입력받은 사용량에 대한 billTotal 값을 갖는 수도요금입니다.
 */
public class WaterBill {
    private final String city;
    private final String sector;
    private final long unitPrice;
    private final long billTotal;

    /**
     * 다음과 같은 속성을 갖습니다.
     *
     * @param city : 지자체명
     * @param sector : 업종
     * @param unitPrice : 구간별 금액(원)
     * @param billTotal : 입력된 사용량에 대한 총 금액(원)
     */
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
        var waterBill = (WaterBill) o;
        return unitPrice == waterBill.unitPrice && billTotal == waterBill.billTotal
            && Objects.equals(city, waterBill.city)
            && Objects.equals(sector, waterBill.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, sector, unitPrice, billTotal);
    }

    @Override
    public String toString() {
        return "WaterBill{"
            + "city='" + city + '\''
            + ", sector='" + sector + '\''
            + ", unitPrice=" + unitPrice
            + ", billTotal=" + billTotal
            + '}';
    }
}
