package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataParserTest {
    private DataParser dataParser;

    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
    }

    @Test
    void parse() {
        String path = "./Tariff_20220331.csv";
        assertDoesNotThrow(() -> dataParser.parse(path));
    }

    @Test
    void parse_fileIsEmpty_throwFileIsEmptyException(){
        String path = "./empty.csv";
        assertThatThrownBy(()->dataParser.parse(path))
            .isInstanceOf(FileIsEmptyException.class)
            .hasMessageContainingAll("empty");
    }

    @Test
    void isEmptyFile() {
        String path = "./empty.csv";
        boolean result = dataParser.isEmptyFile(path);
        assertThat(result).isTrue();
    }
}
