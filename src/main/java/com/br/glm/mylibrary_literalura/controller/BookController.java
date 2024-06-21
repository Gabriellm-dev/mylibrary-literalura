package com.br.glm.mylibrary_literalura.controller;

import com.br.glm.mylibrary_literalura.models.BookResponse;
import com.br.glm.mylibrary_literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public BookResponse searchBooks(@RequestParam String query) {
        BookResponse response = bookService.getBooks(query);
        bookService.printBooks(response.getResults());
        return response;
    }
}
