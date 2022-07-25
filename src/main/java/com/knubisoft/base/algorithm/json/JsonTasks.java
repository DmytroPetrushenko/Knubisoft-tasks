package com.knubisoft.base.algorithm.json;

import com.knubisoft.base.algorithm.ModelRoot;

import java.util.List;
import java.util.Map;

public interface JsonTasks {

    ModelRoot parseJson(String json);

    List<Integer> getAllIds(String json);

    List<String> getNameWithIdMoreThan1(String json);

    List<Map<String, String>> getAllItems(String json);

}
