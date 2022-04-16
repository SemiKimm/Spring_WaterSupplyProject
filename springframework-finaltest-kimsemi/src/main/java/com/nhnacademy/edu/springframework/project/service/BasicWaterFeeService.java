package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.WaterFee;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BasicWaterFeeService implements WaterFeeService {
    @Override
    public List<WaterFee> calculateWaterFee(long consumption) {
        return null;
    }
}
