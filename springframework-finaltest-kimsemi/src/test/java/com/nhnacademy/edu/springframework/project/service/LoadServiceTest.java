package com.nhnacademy.edu.springframework.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.project.exception.IllegalExtensionException;
import com.nhnacademy.edu.springframework.project.repository.BasicTariff;
import com.nhnacademy.edu.springframework.project.repository.CsvDataParser;
import com.nhnacademy.edu.springframework.project.repository.DataParser;
import com.nhnacademy.edu.springframework.project.repository.JsonDataParser;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoadServiceTest {
    LoadService dataLoadService;

    DataParser csvDataParser;
    DataParser jsonDataParser;
    Tariff basicTariff;

    @BeforeEach
    void setUp() {
        basicTariff = mock(BasicTariff.class);
        csvDataParser = mock(CsvDataParser.class);
        jsonDataParser = mock(JsonDataParser.class);
        dataLoadService = new DataLoadService(basicTariff, csvDataParser, jsonDataParser);
    }

    @Test
    void selectParser() {
        String path = "data/Tariff_20220331.json";
        assertDoesNotThrow(()->dataLoadService.selectParser(path));
    }

    @Test
    void selectParser_extensionIsInvalid_throwExp(){
        String path = "data/Tariff_20220331.txt";
        assertThatThrownBy(()->dataLoadService.selectParser(path))
            .isInstanceOf(IllegalExtensionException.class)
            .hasMessageContainingAll("file extension is invalid");
    }

    @Test
    void checkJson_false() {
        String path = "data/Tariff_20220331.csv";
        boolean result = dataLoadService.checkJson(path);
        assertThat(result).isFalse();
    }

    @Test
    void checkJson_true() {
        String path = "data/Tariff_20220331.json";
        boolean result = dataLoadService.checkJson(path);
        assertThat(result).isTrue();
    }

    @Test
    void checkCsv_false() {
        String path = "data/Tariff_20220331.json";
        boolean result = dataLoadService.checkCsv(path);
        assertThat(result).isFalse();
    }

    @Test
    void checkCsv_true() {
        String path = "data/Tariff_20220331.csv";
        boolean result = dataLoadService.checkCsv(path);
        assertThat(result).isTrue();
    }

    @Test
    void selectParserAndLoad() {
        String path = "data/Tariff_20220331.csv";
        dataLoadService.selectParserAndLoad(path);
        verify(basicTariff).load(path);
    }
}
