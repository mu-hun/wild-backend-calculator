package com.example.demo.infrastructure;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class CalculationRepository {
    private final List<Calculation> calculations = new ArrayList<>();

    public synchronized void add(Calculation calculation) {
        Objects.requireNonNull(calculation, "calculation must not be null");
        calculations.add(calculation);
    }

    public synchronized List<Calculation> getAll() {
        return Collections.unmodifiableList(calculations);
    }
}
