package repository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.repository.DataParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataParserTest {
    Log log = LogFactory.getLog(DataParserTest.class);
    DataParser dataParser;
    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
    }

    @Test
    void parse(){
        assertDoesNotThrow(()->dataParser.parse());
    }

    @Test
    void isEmptyFile(){
        String path = "./empty.csv";
        boolean result = dataParser.isEmptyFile(path);
        assertThat(result).isFalse();
    }
}
