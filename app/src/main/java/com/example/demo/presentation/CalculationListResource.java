package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationListResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CalculationListResource extends ResourceMethodHandler {
    public final static String KEY = "GET /calculations";

    private final Calculator calculator = new Calculator();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String handle(String content) throws JsonProcessingException {
        List<Calculation> calculations = calculator.getCalulationList();


        return objectMapper.writeValueAsString(
                CalculationListResponseDto.of(calculations));
    }
}
