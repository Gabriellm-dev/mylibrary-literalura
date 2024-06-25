package com.br.glm.mylibrary_literalura.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birthYear;
    private int deathYear;

    @Setter
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

    @Override
    public String toString() {
        return "Author: " + name + '\n' +
                "Birth Year: " + birthYear + '\n' +
                "Death Year: " + deathYear + '\n' +
                "Books: " + books.stream()
                .map(Book::getTitle)
                .toList() + '\n';
    }
}