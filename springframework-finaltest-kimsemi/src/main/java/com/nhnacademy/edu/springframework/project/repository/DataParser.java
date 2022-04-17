package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.Map;

/**
 * DataParser interface.
 */
public interface DataParser {
    Map<Integer, WaterRate> parse(String path);
}
