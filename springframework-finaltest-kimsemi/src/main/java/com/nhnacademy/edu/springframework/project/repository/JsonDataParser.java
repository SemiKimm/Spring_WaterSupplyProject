package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Json 포멧의 데이터를 파싱합니다.
 */
@Component
public class JsonDataParser implements DataParser {
    @Override
    public Map<Integer, WaterRate> parse(String path) {
        var log = LogFactory.getLog(JsonDataParser.class);
        Map<Integer, WaterRate> result = new HashMap<>();
        if (isEmptyFile(path)) {
            throw new FileIsEmptyException("file is empty");
        }
        var objectMapper = new ObjectMapper();
        try {
            List<WaterRate> waterRates = Arrays.asList(
                objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(path),
                    WaterRate[].class));
            waterRates.forEach(waterRate -> {
                result.put(waterRate.getNumber(), waterRate);
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean isEmptyFile(String path) {
        var log = LogFactory.getLog(JsonDataParser.class);
        try (var fileReader = new BufferedReader(
            new InputStreamReader(
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
