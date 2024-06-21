package com.br.glm.mylibrary_literalura.controller;

import com.br.glm.mylibrary_literalura.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public JsonNode searchBooks(@RequestParam String query) {
        return bookService.getBooks(query);
    }
}
