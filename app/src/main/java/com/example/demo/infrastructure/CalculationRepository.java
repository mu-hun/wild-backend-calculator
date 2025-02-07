package com.example.demo.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CalculationRepository {
    private static final CalculationRepository instance = new CalculationRepository();
    private final List<Calculation> calculations = new ArrayList<>();

    protected CalculationRepository() {
        if (instance != null) {
            throw new IllegalStateException("CalculationRepository is already initialized");
        }
    }

    public static CalculationRepository getInstance() {
        return instance;
    }

    public synchronized void add(Calculation calculation) {
        Objects.requireNonNull(calculation, "calculation must not be null");
        calculations.add(calculation);
    }

    public synchronized List<Calculation> getAll() {
        return Collections.unmodifiableList(calculations);
    }
}
