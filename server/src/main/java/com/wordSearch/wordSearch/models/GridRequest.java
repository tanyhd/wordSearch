package com.wordSearch.wordSearch.models;

import lombok.Data;

import java.util.List;

@Data
public class GridRequest {

    private int size;
    private List<String> words;
}
