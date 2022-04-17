package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project.annotation.Json;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import com.nhnacademy.edu.springframework.project.exception.IllegalExtensionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Json 포멧의 데이터를 파싱합니다.
 */
@Component
@Order(0)
@Json
public class JsonDataParser implements DataParser {
    private final Log log = LogFactory.getLog(JsonDataParser.class);

    @Override
    public Map<Integer, WaterRate> parse(String path) {
        Map<Integer, WaterRate> result = new HashMap<>();
        if (checkInvalidExtension(path)) {
            throw new IllegalExtensionException("file extension is not json : " + path);
        }
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

    @Override
    public boolean checkInvalidExtension(String path) {
        return !FilenameUtils.getExtension(String.valueOf(Path.of(path).getFileName()))
            .equals("json");
    }
}
