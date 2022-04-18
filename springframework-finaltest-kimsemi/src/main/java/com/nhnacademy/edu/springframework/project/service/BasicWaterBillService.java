package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * 기본_상수도사용요금서비스 입니다.
 */
@Service
public class BasicWaterBillService implements WaterBillService {
    private final Tariff basicTariff;

    public BasicWaterBillService(Tariff basicTariff) {
        this.basicTariff = basicTariff;
    }

    @Override
    public List<WaterBill> calculateWaterBill(long consumption) {
        List<WaterRate> waterRateList = basicTariff.findTariffByConsumption(consumption);
        List<WaterBill> waterBillList = waterRateList.isEmpty() ? Collections.emptyList() : new ArrayList<>();
            waterRateList.forEach(waterRate -> {
            long billTotal = waterRate.getUnitPrice() * consumption;
            waterBillList.add(
                new WaterBill(waterRate.getCity(), waterRate.getSector(), waterRate.getUnitPrice(),
                    billTotal));
        });
        return waterBillList;
    }
}
