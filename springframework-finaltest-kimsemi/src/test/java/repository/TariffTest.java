package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.repository.BasicTariff;
import com.nhnacademy.edu.springframework.project.repository.CsvDataParser;
import com.nhnacademy.edu.springframework.project.repository.DataParser;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TariffTest {
    private Tariff tariffRepository;
    private DataParser parser;

    @BeforeEach
    void setUp() {
        parser = mock(CsvDataParser.class);
        tariffRepository = new BasicTariff(parser);
    }

    @Test
    void load(){
        String path = "./Tariff_20220331.csv";
        when(parser.parse(path)).thenReturn(new HashMap<>());
        assertDoesNotThrow(()->tariffRepository.load(path));

        verify(parser).parse(path);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 동두천시 , 가정용 ,1,1,20,690",
        "2, 동두천시 , 가정용 ,2,21,30,1090",
        "3, 동두천시 , 가정용 ,3,31,999999,1530"
    })
    void getAllTariff(int number, String city, String sector, long level, long unitStart,
                            long unitEnd, long unitPrice) {
        WaterRate waterRate = new WaterRate(number,city,sector,unitPrice);
        String path = "./Tariff_20220331.csv";
        when(parser.parse(path)).thenReturn(getMockReturn());
        tariffRepository.load(path);
        Map<Integer,WaterRate> result = tariffRepository.getAllTariff();

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result.get(number).equals(waterRate)).isTrue();
    }

    Map<Integer,WaterRate> getMockReturn(){
        Map<Integer,WaterRate> result = new HashMap<>();
        WaterRate waterRate1 = new WaterRate(1,"동두천시","가정용",690);
        WaterRate waterRate2 = new WaterRate(2,"동두천시","가정용",1090);
        WaterRate waterRate3 = new WaterRate(3,"동두천시","가정용",1530);
        result.put(1, waterRate1);
        result.put(2, waterRate2);
        result.put(3, waterRate3);
        return result;
    }
}
