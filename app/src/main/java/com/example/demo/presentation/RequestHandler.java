package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements HttpHandler {
    private final Map<String, ResourceMethodHandler> handlers = new HashMap<>();

    public RequestHandler() {
        final Calculator calculator = new Calculator();

        handlers.put(HomeGetResource.KEY, new HomeGetResource());
        handlers.put(CalculationCreateResource.KEY, new CalculationCreateResource(calculator));
        handlers.put(CalculationListResource.KEY, new CalculationListResource(calculator));
    }

    private String getRequestContent(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes());
    }

    private void sendResponseContent(HttpExchange exchange, String responseContent) throws IOException {
        exchange.sendResponseHeaders(200, responseContent.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseContent.getBytes());
        }
    }

    private String getRequestKey(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        return method + " " + path;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String resultKey = getRequestKey(exchange);

            if (!handlers.containsKey(resultKey)) {
                sendErrorResponse(exchange, 404, "요청한 리소스를 찾을 수 없습니다");
                return;
            }

            ResourceMethodHandler handler = handlers.get(resultKey);
            final String requestContent = getRequestContent(exchange);

            try {
                final String responseContent = handler.handle(requestContent);
                sendSuccessResponse(exchange, responseContent);
            } catch (Exception e) {
                sendErrorResponse(exchange, 400, "요청 처리 중 오류가 발생했습니다: " + e.getMessage());
            }
        } finally {
            exchange.close();
        }
    }

    private void sendSuccessResponse(HttpExchange exchange, String content) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, content.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(content.getBytes());
        }
    }

    private void sendErrorResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        String errorJson = String.format("{\"error\": \"%s\"}", message);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, errorJson.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorJson.getBytes());
        }
    }

}
