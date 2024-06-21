package com.br.glm.mylibrary_literalura.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

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


}
