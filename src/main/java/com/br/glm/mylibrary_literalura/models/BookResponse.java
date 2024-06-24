package com.br.glm.mylibrary_literalura.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {
    private List<Result> results;

    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        @Getter
        private int id;
        @Getter
        private String title;
        @Getter
        private List<Author> authors;
        @Getter
        private List<String> languages;
        private int downloadCount;

        @JsonProperty("download_count")
        public int getDownloadCount() {
            return downloadCount;
        }

    }
}