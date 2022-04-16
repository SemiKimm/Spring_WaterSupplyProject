package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BasicWaterBillService implements WaterBillService {
    private final Tariff basicTariff;

    public BasicWaterBillService(Tariff basicTariff) {
        this.basicTariff = basicTariff;
    }

    @Override
    public List<WaterBill> calculateWaterFee(@NonNull long consumption) {
        List<WaterBill> waterBillList = new ArrayList<>();
        List<WaterRate> waterRateList = basicTariff.findTariffByConsumption(consumption);
        waterRateList.forEach(waterRate -> {
            long billTotal = waterRate.getUnitPrice()*consumption;
            waterBillList.add(new WaterBill(waterRate.getCity(), waterRate.getSector(), waterRate.getUnitPrice(), billTotal));
        });
        return waterBillList;
    }
}
