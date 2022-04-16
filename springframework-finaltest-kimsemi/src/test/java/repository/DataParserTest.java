package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.repository.CsvDataParser;
import com.nhnacademy.edu.springframework.project.repository.DataParser;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DataParserTest {
    private DataParser dataParser;

    private final Log log = LogFactory.getLog(DataParserTest.class);

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

    @ParameterizedTest
    @CsvSource({
        "1, 동두천시 , 가정용 ,1,1,20,690",
        "2, 동두천시 , 가정용 ,2,21,30,1090",
        "3, 동두천시 , 가정용 ,3,31,999999,1530"
    })
    void getParsingDataList(int number, String city, String sector, long level, long unitStart,
                            long unitEnd, long unitPrice) {
        WaterRate waterRate = new WaterRate(number,city,sector,unitPrice);
        String path = "./Tariff_20220331.csv";
        dataParser.parse(path);
        Map<Integer,WaterRate> result = dataParser.getParsingDataList();
        assertThat(result.get(number).equals(waterRate)).isTrue();
    }
}
