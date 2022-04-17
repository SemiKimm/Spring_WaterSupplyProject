package com.nhnacademy.edu.springframework.project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * 수도요금입니다.
 */
public class WaterRate {
    @JsonProperty("순번")
    private final int number;
    @JsonProperty("지자체명")
    private final String city;
    @JsonProperty("업종")
    private final String sector;
    @JsonProperty("단계")
    private final long level;
    @JsonProperty("구간시작(세제곱미터)")
    private final long unitStart;
    @JsonProperty("구간끝(세제곱미터)")
    private final long unitEnd;
    @JsonProperty("구간금액(원)")
    private final long unitPrice;
    @JsonProperty("단계별 기본요금(원)")
    private final long levelPrice;

    public WaterRate() {
        this(0, null, null, 0L, 0L, 0L, 0L, 0L);
    }

    /**
     * .
     *
     * @param number     : 순번
     * @param city       : 지자체명
     * @param sector     : 업종
     * @param level      : 단계
     * @param unitStart  : 구간시작(세제곱미터)
     * @param unitEnd    : 구간끝(세제곱미터)
     * @param unitPrice  : 구간금액(원)
     * @param levelPrice : 단계별 기본요금(원)
     */
    public WaterRate(int number, String city, String sector, long level, long unitStart,
                     long unitEnd, long unitPrice, long levelPrice) {
        this.number = number;
        this.city = city;
        this.sector = sector;
        this.level = level;
        this.unitStart = unitStart;
        this.unitEnd = unitEnd;
        this.unitPrice = unitPrice;
        this.levelPrice = levelPrice;
    }

    public int getNumber() {
        return number;
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
        var waterRate = (WaterRate) o;
        return number == waterRate.number && level == waterRate.level
            && unitStart == waterRate.unitStart && unitEnd == waterRate.unitEnd
            && unitPrice == waterRate.unitPrice && Objects.equals(city, waterRate.city)
            && Objects.equals(sector, waterRate.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, sector, level, unitStart, unitEnd, unitPrice);
    }
}
