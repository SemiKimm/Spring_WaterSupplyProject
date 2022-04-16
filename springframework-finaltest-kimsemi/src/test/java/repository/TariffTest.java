package repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TariffTest {
    Tariff tariffRepository;

    @BeforeEach
    void setUp() {
        tariffRepository = new BasicTariff();
    }

    @Test
    void load(){

    }
}
