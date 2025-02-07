package com.example.demo.presentation;

public class HomeGetResource extends ResourceMethodHandler {
    public final static String KEY = "GET /";

    public String handle(String content) {
        return "{ \"body\": \"Hello, World!\" }";
    }
}
