package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterFee;
import java.util.List;

public interface WaterFeeService {
    List<WaterFee> calculateWaterFee(long consumption);
}
