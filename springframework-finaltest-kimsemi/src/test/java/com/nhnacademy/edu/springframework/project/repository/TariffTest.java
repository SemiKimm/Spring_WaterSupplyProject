package com.nhnacademy.edu.springframework.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TariffTest {
    private Tariff tariffRepository;
    private DataParser parser;

    @BeforeEach
    void setUp() {
        parser = mock(CsvDataParser.class);
        tariffRepository = new BasicTariff();
        tariffRepository.setParser(parser);
    }

    @Test
    void load() {
        String path = "data/Tariff_20220331.csv";
        when(parser.parse(path)).thenReturn(new HashMap<>());
        assertDoesNotThrow(() -> tariffRepository.load(path));

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
        WaterRate waterRate =
            new WaterRate(number, city, sector, level, unitStart, unitEnd, unitPrice, 0L);
        String path = "data/Tariff_20220331.csv";
        when(parser.parse(path)).thenReturn(getMockReturn());
        tariffRepository.load(path);
        Map<Integer, WaterRate> result = tariffRepository.getAllTariff();

        assertThat(result).isNotNull()
            .isNotEmpty()
            .containsEntry(number, waterRate);
    }

    Map<Integer, WaterRate> getMockReturn() {
        Map<Integer, WaterRate> result = new HashMap<>();
        WaterRate waterRate1 = new WaterRate(1, "동두천시", "가정용", 1, 1, 20, 690, 0L);
        WaterRate waterRate2 = new WaterRate(2, "동두천시", "가정용", 2, 21, 30, 1090, 0L);
        WaterRate waterRate3 = new WaterRate(3, "동두천시", "가정용", 3, 31, 999999, 1530, 0L);
        result.put(1, waterRate1);
        result.put(2, waterRate2);
        result.put(3, waterRate3);
        return result;
    }

    @Test
    void findTariffByConsumption() {
        long consumption = 1_000L;
        String path = "data/Tariff_20220331.csv";

        when(parser.parse(path)).thenReturn(getMockReturn());
        tariffRepository.load(path);

        List<WaterRate> result = tariffRepository.findTariffByConsumption(consumption);
        assertThat(result).isNotNull().isNotEmpty();
        result.forEach(rate -> {
            assertThat(
                consumption >= rate.getUnitStart() && consumption <= rate.getUnitEnd()).isTrue();
        });
        assertThat(result).isSortedAccordingTo(
            (rate1, rate2) -> (int) (rate1.getUnitPrice() - rate2.getUnitPrice()));
    }
}
