package com.br.glm.mylibrary_literalura.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.OptionalDouble;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    private String language;
    private Double download;

    public Book() {
    }

    public Book(String title, Author author, List<String> languages, Double download) {
        this.title = title;
        this.author = author;
        this.language = languages != null && !languages.isEmpty() ? String.join(",", languages) : null;
        this.download = OptionalDouble.of(download).orElse(0);
    }

    @Override
    public String toString() {
        return "------ Book ------" + "\n" +
                "Title: " + title + "\n" +
                "Author: " + author.getName() + "\n" +
                "Language: " + language + "\n" +
                "Number of Downloads: " + download + "\n" +
                "-------------------";
    }
}