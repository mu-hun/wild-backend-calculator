package com.example.demo.presentation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        System.out.println(method + " " + path);

        byte[] greeting = getGreeting().getBytes();

        exchange.sendResponseHeaders(200, greeting.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(greeting);
        }
    }

    private String getGreeting() {
        return "Hello World!\n";
    }
}
