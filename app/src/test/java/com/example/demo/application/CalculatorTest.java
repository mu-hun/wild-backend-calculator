package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.InMemoryCalculationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {
    CalculationRepository calculationRepository;
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculationRepository = new InMemoryCalculationRepository();
        calculator = new Calculator(calculationRepository);
    }

    @Test
    void plus() {
        Calculation calculation = calculator.calculate(1, 2, "+");

        assertThat(calculation.result()).isEqualTo(3);
    }

    @Test
    void minus() {
        Calculation calculation = calculator.calculate(1, 2, "-");

        assertThat(calculation.result()).isEqualTo(-1);
    }

    @Test
    void multiply() {
        Calculation calculation = calculator.calculate(1, 2, "*");

        assertThat(calculation.result()).isEqualTo(2);
    }

    @Test
    void divide() {
        Calculation calculation = calculator.calculate(4, 2, "/");

        assertThat(calculation.result()).isEqualTo(2);
    }
}
