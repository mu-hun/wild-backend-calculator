package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CalculationCreateHandler extends ResourceMethodHandler {
    private final Calculator calculator;
    private final ObjectMapper objectMapper;

    public CalculationCreateHandler(
            Calculator calculator,
            ObjectMapper objectMapper
    ) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }


    @Override
    public String key() {
        return "POST /calculations";
    }

    public String handle(String content) throws JsonProcessingException, IllegalArgumentException {
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
