package com.nhnacademy.edu.springframework.project.domain;

import java.util.Objects;

/**
 * 수도요금입니다.
 */
public class WaterRate {
    private final int number;
    private final String city;
    private final String sector;
    private final long level;
    private final long unitStart;
    private final long unitEnd;
    private final long unitPrice;

    /**
     * 다음과 같은 속성을 갖습니다.
     *
     * @param number : 순번
     * @param city : 지자체명
     * @param sector : 업종
     * @param level : 단계
     * @param unitStart : 구간시작(세제곱미터)
     * @param unitEnd : 구간끝(세제곱미터)
     * @param unitPrice : 구간금액(원)
     */
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

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public long getUnitStart() {
        return unitStart;
    }

    public long getUnitEnd() {
        return unitEnd;
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
        return number == waterRate.number && level == waterRate.level
            && unitStart == waterRate.unitStart && unitEnd == waterRate.unitEnd
            && unitPrice == waterRate.unitPrice && Objects.equals(city, waterRate.city)
            && Objects.equals(sector, waterRate.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, sector, level, unitStart, unitEnd, unitPrice);
    }

    @Override
    public String toString() {
        return "WaterRate{"
            + "number=" + number
            + ", city='" + city + '\''
            + ", sector='" + sector + '\''
            + ", level=" + level
            + ", unitStart=" + unitStart
            + ", unitEnd=" + unitEnd
            + ", unitPrice=" + unitPrice
            + '}';
    }
}
