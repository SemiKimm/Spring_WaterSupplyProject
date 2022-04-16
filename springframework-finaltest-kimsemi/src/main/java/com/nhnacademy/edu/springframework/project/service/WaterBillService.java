package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterBill;
import java.util.List;

/**
 * 상수도사용요금서비스 입니다.
 * 입력된 물 사용량에 대한 요금을 계산합니다.
 */
public interface WaterBillService {
    List<WaterBill> calculateWaterFee(long consumption);
}
