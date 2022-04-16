package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import com.nhnacademy.edu.springframework.project.service.ReportService;
import com.nhnacademy.edu.springframework.project.service.WaterBillService;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        String path = "./Tariff_20220331.csv";
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            SpringConfig.class)){
            context.getBean("basicTariff", Tariff.class).load(path);
            List<WaterBill> data = context.getBean("basicWaterBillService", WaterBillService.class).calculateWaterFee(1_000L);
            context.getBean("basicReportService", ReportService.class).report(data);
        }
    }
}
