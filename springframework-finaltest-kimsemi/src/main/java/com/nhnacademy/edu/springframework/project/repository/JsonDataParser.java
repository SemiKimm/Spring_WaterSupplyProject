package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonDataParser implements DataParser {
    @Override
    public Map<Integer, WaterRate> parse(String path) {
        Log log = LogFactory.getLog(JsonDataParser.class);
        if (isEmptyFile(path)) {
            throw new FileIsEmptyException("file is empty");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WaterRate waterRate = objectMapper.readValue(new File(path),WaterRate.class);
            log.info(waterRate);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
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
