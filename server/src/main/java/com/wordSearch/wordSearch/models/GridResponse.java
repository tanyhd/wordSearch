package com.wordSearch.wordSearch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GridResponse {

    private char[][] contents;
    private List<String> wordInPuzzle;
}
