package com.example.demo;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException {
        new App().run();
    }

    public void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        HttpServer httpServer = HttpServer.create(address, 0);

        httpServer.createContext("/", exchange -> {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            System.out.println(method + " " + path);

            byte[] greeting = getGreeting().getBytes();

            exchange.sendResponseHeaders(200, greeting.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(greeting);
            }
        });
        httpServer.start();

        System.out.println("Listening on " + address);
    }
}
