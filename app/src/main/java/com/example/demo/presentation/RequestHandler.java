package com.example.demo.presentation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements HttpHandler {
    private final Map<String, ResourceMethodHandler> handlers = new HashMap<>();

    public RequestHandler() {
        handlers.put(HomeGetResource.KEY, new HomeGetResource());
        handlers.put(CalculationCreateResource.KEY, new CalculationCreateResource());
        handlers.put(CalculationListResource.KEY, new CalculationListResource());
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
        String resultKey = getRequestKey(exchange);
        System.out.println(resultKey);

        if (!handlers.containsKey(resultKey)) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }

        ResourceMethodHandler handler = handlers.get(resultKey);

        final String requestContent = getRequestContent(exchange);
        final String responseContent = handler.handle(requestContent);


        sendResponseContent(exchange, responseContent);
    }
}
