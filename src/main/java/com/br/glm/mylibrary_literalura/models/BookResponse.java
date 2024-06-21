package com.br.glm.mylibrary_literalura.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {
    private int count;
    private String next;
    private String previous;
    private List<Book> results;

}