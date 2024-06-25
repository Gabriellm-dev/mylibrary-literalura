package com.br.glm.mylibrary_literalura.controller;

import com.br.glm.mylibrary_literalura.dto.BookDTO;
import com.br.glm.mylibrary_literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping()
    public List<BookDTO> getAllBooks(){
        return service.getAllBooks();
    }
}