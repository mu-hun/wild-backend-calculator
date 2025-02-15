package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationListResponseDto;
import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calculations")
public class CalculationController {
    private final Calculator calculator;
    private final ObjectMapper objectMapper;

    public CalculationController(
            Calculator calculator,
            ObjectMapper objectMapper
    ) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public CalculationListResponseDto list() {
        List<Calculation> calculations = calculator.getCalulationList();
        return CalculationListResponseDto.of(calculations);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalculationResponseDto create(
            @RequestBody CalculationRequestDto requestDto
    ) {
        Calculation calculation = calculator.calculate(requestDto.a(), requestDto.b(), requestDto.operator());

        return new CalculationResponseDto(
                calculation.a(),
                calculation.b(),
                calculation.operator(),
                calculation.result()
        );
    }
}
