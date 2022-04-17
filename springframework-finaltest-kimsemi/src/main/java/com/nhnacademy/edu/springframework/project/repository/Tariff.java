package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.List;
import java.util.Map;

/**
 * 요금표저장소 입니다.
 * 파일위치를 통해 csv 파일을 로드합니다.
 * 입력된 물 사용량에 대한 표준금액을 찾습니다.
 */
public interface Tariff {
    void load(String path);

    Map<Integer, WaterRate> getAllTariff();

    List<WaterRate> findTariffByConsumption(long consumption);

    void setParser(DataParser parser);
}
