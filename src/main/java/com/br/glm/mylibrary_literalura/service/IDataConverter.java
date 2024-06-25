package com.br.glm.mylibrary_literalura.service;

import java.util.List;

public interface IDataConverter {
    <T> T fetchData(String json, Class<T> clazz);

    <T> List<T> fetchDataArray(String json, Class<T> clazz);
}
