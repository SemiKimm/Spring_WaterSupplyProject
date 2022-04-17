package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Json 포멧의 데이터를 파싱합니다.
 */
@Component
public class JsonDataParser implements DataParser {
    private final Log log = LogFactory.getLog(JsonDataParser.class);

    @Override
    public Map<Integer, WaterRate> parse(String path) {
        Map<Integer, WaterRate> result = new HashMap<>();

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
}
