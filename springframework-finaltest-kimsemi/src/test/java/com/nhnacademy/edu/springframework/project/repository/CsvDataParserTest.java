package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvDataParserTest {
    private DataParser csvDataParser;

    @BeforeEach
    void setUp() {
        csvDataParser = new CsvDataParser();
    }

    @Test
    void parse() {
        String path = "data/Tariff_20220331.csv";
        assertDoesNotThrow(() -> csvDataParser.parse(path));
    }

    @Test
    void parse_fileIsEmpty_throwFileIsEmptyException() {
        String path = "./empty.csv";
        assertThatThrownBy(() -> csvDataParser.parse(path))
            .isInstanceOf(FileIsEmptyException.class)
            .hasMessageContainingAll("empty");
    }

    @Test
    void parse_resultIsNotNull() {
        String path = "data/Tariff_20220331.csv";
        Map<Integer, WaterRate> result = csvDataParser.parse(path);
        assertThat(result).isNotNull().isNotEmpty();
    }

    @Test
    void isEmptyFile_true() {
        String path = "./empty.csv";
        boolean result = csvDataParser.isEmptyFile(path);
        assertThat(result).isTrue();
    }

    @Test
    void isEmptyFile_false() {
        String path = "data/Tariff_20220331.csv";
        boolean result = csvDataParser.isEmptyFile(path);
        assertThat(result).isFalse();
    }
}
