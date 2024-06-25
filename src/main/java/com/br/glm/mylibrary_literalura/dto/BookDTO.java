package com.br.glm.mylibrary_literalura.dto;

import com.br.glm.mylibrary_literalura.models.Author;

public record BookDTO(
        Long id,
        String title,
        Author author,
        String language,
        Double downloads
) {
}
