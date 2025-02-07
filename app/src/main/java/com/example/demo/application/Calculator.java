package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.CalculationRepository;

import java.util.List;

public class Calculator {

    private final CalculationRepository calculationRepository = CalculationRepository.getInstance();

    public Calculation calculate(int a, int b, String operator) {
        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };

        Calculation calculation = new Calculation(operator, a, b, result);
        
        calculationRepository.add(calculation);

        return calculation;
    }

    public List<Calculation> getCalulationList() {
        return calculationRepository.getAll();
    }
}
