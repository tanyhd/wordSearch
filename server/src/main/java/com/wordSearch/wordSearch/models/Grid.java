package com.wordSearch.wordSearch.models;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class Grid {
    private int gridSize;
    private char[][] contents;
    private List<Coordinate> coordinates = new ArrayList<>();


    public Grid (int gridSize) {
        this.gridSize = gridSize;
        this.contents = new char[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                coordinates.add(new Coordinate(i, j));
                contents[i][j] = '_';
            }
        }

    }
}
