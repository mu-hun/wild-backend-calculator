package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class CalculationCreateResource extends ResourceMethodHandler {
    public final static String KEY = "POST /calculations";

    private final Calculator calculator;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CalculationCreateResource(Calculator calculator) {
        this.calculator = Objects.requireNonNull(calculator, "calculator는 null일 수 없습니다");
    }

    public String handle(String content) throws JsonProcessingException {
        CalculationRequestDto requestDto = objectMapper.readValue(content, CalculationRequestDto.class);

        Calculation calculation = calculator.calculate(requestDto.a(), requestDto.b(), requestDto.operator());

        return objectMapper.writeValueAsString(new CalculationResponseDto(
                calculation.a(),
                calculation.b(),
                calculation.operator(),
                calculation.result()
        ));
    }
}
