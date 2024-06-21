package com.br.glm.mylibrary_literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class BookService {
    private final HttpClient httpClient;

    public BookService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public HttpRequest buildRequest(String query){
        return HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=\" + query"))
                .GET()
                .build();
    }

    public CompletableFuture<HttpResponse<String>> sendRequest(HttpRequest request) {
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public JsonNode getBooks(String query) {
        HttpRequest request = buildRequest(query);
        CompletableFuture<HttpResponse<String>> responseFuture = sendRequest(request);

        try {
            HttpResponse<String> response = responseFuture.get();
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(responseBody);
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            return null;
        }

}
