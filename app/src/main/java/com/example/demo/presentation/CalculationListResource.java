package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationListResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

public class CalculationListResource extends ResourceMethodHandler {
    public final static String KEY = "GET /calculations";

    private final Calculator calculator;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CalculationListResource(Calculator calculator) {
        this.calculator = Objects.requireNonNull(calculator, "calculator는 null일 수 없습니다");
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
