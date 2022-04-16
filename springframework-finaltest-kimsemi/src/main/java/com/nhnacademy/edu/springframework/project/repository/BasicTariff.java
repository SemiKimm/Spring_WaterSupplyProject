package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.Map;
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
}
