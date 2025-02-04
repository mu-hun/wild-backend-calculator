package com.example.demo.presentation;

public class CalculationCreateResource extends ResourceMethodHandler {
    public final static String KEY = "POST /calculations";

    public String handle(String content) {
        String[] values = content.split(" ");

        int a = Integer.parseInt(values[0]);
        int b = Integer.parseInt(values[2]);

        int result = a + b;

        return result + "\n";
    }
}
