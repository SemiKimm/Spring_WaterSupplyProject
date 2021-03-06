package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonDataParserTest {
    DataParser jsonDataParser;

    @BeforeEach
    void setUp() {
        jsonDataParser = new JsonDataParser();
    }

    @Test
    void parse() {
        String path = "data/Tariff_20220331.json";
        assertDoesNotThrow(() -> jsonDataParser.parse(path));
    }

    @Test
    void parse_resultIsNotNull() {
        String path = "data/Tariff_20220331.json";
        Map<Integer, WaterRate> result = jsonDataParser.parse(path);
        assertThat(result).isNotNull().isNotEmpty();
    }
}
