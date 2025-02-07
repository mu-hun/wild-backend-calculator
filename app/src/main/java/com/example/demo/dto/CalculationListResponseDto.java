package com.example.demo.dto;

import com.example.demo.infrastructure.Calculation;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public record CalculationListResponseDto(
        List<CalculationResponseDto> calculations) implements Serializable {

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

    @Override
    public List<CalculationResponseDto> calculations() {
        return Collections.unmodifiableList(calculations);
    }
}
