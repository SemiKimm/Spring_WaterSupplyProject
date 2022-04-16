package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BasicTariff implements Tariff {
    private final DataParser parser;
    private Map<Integer, WaterRate> tariff;

    public BasicTariff(DataParser parser) {
        this.parser = parser;
    }

    @Override
    public void load(String path) {
        tariff = parser.parse(path);
    }

    @Override
    public Map<Integer, WaterRate> getAllTariff() {
        return this.tariff;
    }

    @Override
    public List<WaterRate> findTariffByConsumption(long consumption) {
        List<WaterRate> result;
        result = tariff.values().stream()
            .sorted((rate1, rate2)-> (int) (rate1.getUnitPrice() - rate2.getUnitPrice()))
            .collect(Collectors.toList());
        return result;
    }
}
