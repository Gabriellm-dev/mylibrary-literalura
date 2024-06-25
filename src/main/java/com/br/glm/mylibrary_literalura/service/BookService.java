package com.br.glm.mylibrary_literalura.service;


import com.br.glm.mylibrary_literalura.dto.BookDTO;
import com.br.glm.mylibrary_literalura.models.Book;
import com.br.glm.mylibrary_literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<BookDTO> getAllBooks() {
        return convertData(repository.findAll());
    }

    public List<BookDTO> convertData(List<Book> books) {
        return books.stream()
                .map(b -> new BookDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getLanguage(),
                        b.getDownload()
                ))
                .collect(Collectors.toList());
    }
}