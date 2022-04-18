package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    public void setParser(DataParser parser) {
        this.parser = parser;
    }

    @Override
    public void load(@NonNull String path) {
        if (isEmptyFile(path)) {
            throw new FileIsEmptyException("file is empty");
        }
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
            .sorted((rate1, rate2) -> (int) (rate1.getUnitPrice() - rate2.getUnitPrice())).limit(5)
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean isEmptyFile(String path) {
        Log log = LogFactory.getLog(BasicTariff.class);
        try (var fileReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))))) {
            if (fileReader.lines().findAny().isEmpty()) {
                return true;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
