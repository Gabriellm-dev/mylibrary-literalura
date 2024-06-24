package com.br.glm.mylibrary_literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}