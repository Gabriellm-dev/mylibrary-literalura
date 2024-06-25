package com.br.glm.mylibrary_literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class ConvertData implements IDataConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T fetchData(String json, Class<T> clazz) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsArray = rootNode.get("results");

            if (resultsArray != null && resultsArray.size() > 0) {
                JsonNode firstResult = resultsArray.get(0);
                return objectMapper.treeToValue(firstResult, clazz);
            } else {
                throw new RuntimeException("No results found in JSON.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> fetchDataArray(String json, Class<T> clazz) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsArray = rootNode.get("results");

            if (resultsArray != null && resultsArray.size() > 0) {
                List<T> resultList = new ArrayList<>();
                for (JsonNode node : resultsArray) {
                    T result = objectMapper.treeToValue(node, clazz);
                    resultList.add(result);
                }
                return resultList;
            } else {
                throw new RuntimeException("No results found in JSON.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}