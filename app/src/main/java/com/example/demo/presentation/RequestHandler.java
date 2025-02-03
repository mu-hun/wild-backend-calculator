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

        if (path.equals("/calculations") && method.equals("POST")) {
            final String input = new String(exchange.getRequestBody().readAllBytes());
            String[] values = input.split(" ");

            int a = Integer.parseInt(values[0]);
            int b = Integer.parseInt(values[2]);

            byte[] result = String.valueOf(a + b).getBytes();

            exchange.sendResponseHeaders(200, result.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(result);
            }
            return;
        }

        if (path.equals("/calculations") && method.equals("GET")) {
//            TODO: 계산 이력 출력 구현하기
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        if (path.equals("/calculations")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

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
