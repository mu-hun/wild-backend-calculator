package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationListResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculationListHandler extends ResourceMethodHandler {
    private final Calculator calculator;
    private final ObjectMapper objectMapper;

    public CalculationListHandler(
            Calculator calculator,
            ObjectMapper objectMapper
    ) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }

    @Override
    public String key() {
        return "GET /calculations";
    }

    public String handle(String content) {
        try {
            List<Calculation> calculations = calculator.getCalulationList();
            return objectMapper.writeValueAsString(
                    CalculationListResponseDto.of(calculations));
        } catch (Exception e) {
            throw new RuntimeException("계산 목록을 가져오는 중 오류가 발생했습니다", e);
        }
    }
}
