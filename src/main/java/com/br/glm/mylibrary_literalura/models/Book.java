package com.br.glm.mylibrary_literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private int id;
    private String title;
    private List<Author> authors;
    private List<String> languages;
    private int download_count;

    @Override
    public String toString() {
        return  "\n  ID = " + id +
                "\n  Titulo = " + title +
                "\n  Autor = " + authors +
                "\n  Idioma = " + languages +
                "\n  Downloads = " + download_count +
                "\n";
    }
}
