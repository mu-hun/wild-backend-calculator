package com.example.demo.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class CalculationRepository {
    private static CalculationRepository instance = null;
    private final List<Calculation> calculations = new ArrayList<>();

    protected CalculationRepository() {
        
    }

    public static CalculationRepository getInstance() {
        if (instance == null) {
            instance = new CalculationRepository();
        }
        return instance;
    }

    public void add(Calculation calculation) {
        calculations.add(calculation);
    }

    public List<Calculation> getAll() {
        return new ArrayList<>(calculations);
    }
}
