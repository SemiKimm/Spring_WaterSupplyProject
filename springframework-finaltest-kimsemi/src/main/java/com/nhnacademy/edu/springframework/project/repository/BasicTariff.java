package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.annotation.Json;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * 기본_요금표저장소 입니다.
 */
@Repository
public class BasicTariff implements Tariff {
    private DataParser parser;
    private Map<Integer, WaterRate> tariff;

    @Override
    @Autowired
    public void setParser(@Json DataParser parser) {
        this.parser = parser;
    }

    @Override
    public void load(@NonNull String path) {
        tariff = parser.parse(path);
    }

    @Override
    public Map<Integer, WaterRate> getAllTariff() {
        return this.tariff;
    }

    @Override
    public List<WaterRate> findTariffByConsumption(@NonNull long consumption) {
        List<WaterRate> result;
        result = tariff.values().stream()
            .filter(waterRate ->
                waterRate.getUnitStart() <= consumption && waterRate.getUnitEnd() >= consumption
            )
            .sorted((rate1, rate2) -> (int) (rate1.getUnitPrice() - rate2.getUnitPrice()))
            .collect(Collectors.toList());
        return result;
    }
}
