package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.Map;

public interface Tariff {
    void load(String path);

    Map<Integer, WaterRate> getAllTariff();
}
