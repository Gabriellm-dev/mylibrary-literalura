package com.br.glm.mylibrary_literalura.service;

import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Service
public class BookService {
    private final HttpClient httpClient;

    public BookService() {
        this.httpClient = HttpClient.newHttpClient();
    }
}
