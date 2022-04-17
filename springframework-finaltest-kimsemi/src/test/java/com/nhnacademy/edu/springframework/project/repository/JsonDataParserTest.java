package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import com.nhnacademy.edu.springframework.project.exception.IllegalExtensionException;
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

    @Test
    void parse_isEmptyFile_throwFileIsEmptyException() {
        String path = "./empty.json";
        assertThatThrownBy(() -> jsonDataParser.parse(path))
            .isInstanceOf(FileIsEmptyException.class)
            .hasMessageContainingAll("empty");
    }

    @Test
    void parse_fileIsNotJsonFile_throwIllegalExtensionException() {
        String path = "data/Tariff_20220331.csv";
        assertThatThrownBy(() -> jsonDataParser.parse(path))
            .isInstanceOf(IllegalExtensionException.class)
            .hasMessageContainingAll("file extension is not json");
    }

    @Test
    void checkInvalidExtension_true() {
        String path = "data/Tariff_20220331.csv";
        boolean result = jsonDataParser.checkInvalidExtension(path);
        assertThat(result).isTrue();
    }

    @Test
    void checkInvalidExtension_false() {
        String path = "data/Tariff_20220331.json";
        boolean result = jsonDataParser.checkInvalidExtension(path);
        assertThat(result).isFalse();
    }

    @Test
    void isEmptyFile_true() {
        String path = "./empty.json";
        boolean result = jsonDataParser.isEmptyFile(path);
        assertThat(result).isTrue();
    }

    @Test
    void isEmptyFile_false() {
        String path = "data/Tariff_20220331.json";
        boolean result = jsonDataParser.isEmptyFile(path);
        assertThat(result).isFalse();
    }
}