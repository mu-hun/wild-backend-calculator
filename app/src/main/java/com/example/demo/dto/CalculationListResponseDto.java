package com.example.demo.dto;

import com.example.demo.infrastructure.Calculation;

import java.io.Serializable;
import java.util.List;

public class CalculationListResponseDto implements Serializable {
    private final List<CalculationResponseDto> calculations;

    public CalculationListResponseDto(List<CalculationResponseDto> calculations) {
        this.calculations = calculations;
    }

    public static CalculationListResponseDto of(List<Calculation> calculations) {
        return new CalculationListResponseDto(calculations.stream()
                .map(calculation ->
                        new CalculationResponseDto(
                                calculation.a(),
                                calculation.b(),
                                calculation.operator(),
                                calculation.result()
                        )
                )
                .toList());
    }

    public List<CalculationResponseDto> getCalculations() {
        return calculations;
    }
}
