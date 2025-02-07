package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationListResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CalculationListResource extends ResourceMethodHandler {
    public final static String KEY = "GET /calculations";

    private final Calculator calculator;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CalculationListResource(Calculator calculator) {
        this.calculator = Objects.requireNonNull(calculator, "calculator는 null일 수 없습니다");
    }


        return objectMapper.writeValueAsString(
                CalculationListResponseDto.of(calculations));
    }
}
