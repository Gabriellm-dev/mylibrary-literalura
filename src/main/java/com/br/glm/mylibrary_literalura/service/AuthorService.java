package com.br.glm.mylibrary_literalura.service;


import com.br.glm.mylibrary_literalura.dto.AuthorDTO;
import com.br.glm.mylibrary_literalura.models.Author;
import com.br.glm.mylibrary_literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> getAllAuthors() {
        return convertToDTOs(authorRepository.findAll());
    }

    public List<AuthorDTO> convertToDTOs(List<Author> authors) {
        return authors.stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getBirthYear(),
                        author.getDeathYear()
                ))
                .collect(Collectors.toList());
    }
}