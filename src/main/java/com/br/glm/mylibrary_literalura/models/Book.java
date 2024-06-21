package com.br.glm.mylibrary_literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private int id;
    private String title;
    private List<Author> authors;
    private List<String> subjects;
    private List<String> languages;
    private List<String> bookshelves;
    private int download_count;


}
