package com.br.glm.mylibrary_literalura.service;

import com.br.glm.mylibrary_literalura.models.Book;
import com.br.glm.mylibrary_literalura.models.BookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {
    private final HttpClient httpClient;
    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    public BookService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    private HttpRequest buildRequest(String query) {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=" + query.replace(" ", "%20")))
                .GET()
                .build();
    }

    public BookResponse getBooks(String query) {
        HttpRequest request = buildRequest(query);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Status Code: " + response.statusCode());
            logger.info("Response Body: " + response.body());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.body(), BookResponse.class);
            } else {
                throw new RuntimeException("Failed to fetch data. HTTP Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error fetching data from API: " + e.getMessage(), e);
        }
    }

    public void printBooks(List<Book> books) {
        books.forEach(book -> {
            System.out.println(book.toString());
        });
    }
}
