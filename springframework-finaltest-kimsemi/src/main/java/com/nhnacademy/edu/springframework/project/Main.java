package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.SpringConfig;
import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import com.nhnacademy.edu.springframework.project.service.ReportService;
import com.nhnacademy.edu.springframework.project.service.WaterBillService;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PATH = "data/Tariff_20220331.csv";

    /**
     * ..
     */
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(
            SpringConfig.class)) {
            context.getBean("basicTariff", Tariff.class).load(PATH);
            var waterBillService =
                context.getBean("basicWaterBillService", WaterBillService.class);
            var reportService =
                context.getBean("basicReportService", ReportService.class);

            System.out.print("> ");
            while (scanner.hasNext()) {
                var consumption = scanner.nextLong();
                List<WaterBill> data = waterBillService.calculateWaterFee(consumption);
                reportService.report(data);
                System.out.print("> ");
            }
        }
    }
}
