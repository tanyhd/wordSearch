package com.wordSearch.wordSearch.services;

import com.wordSearch.wordSearch.models.Coordinate;
import com.wordSearch.wordSearch.models.Direction;
import com.wordSearch.wordSearch.models.Grid;
import com.wordSearch.wordSearch.models.GridResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WordGridService {

    public GridResponse createWordGrid(int size, List<String> words) {
        List<String> wordInPuzzle = new ArrayList<>();
        Grid grid = new Grid(size);
        fillGrid(grid, words, wordInPuzzle);
        return GridResponse.builder()
                .wordInPuzzle(wordInPuzzle)
                .contents(grid.getContents())
                .gridSize(size)
                .build();
    }

    public void fillGrid (Grid grid, List<String> words, List<String> wordInPuzzle) {
        int gridSize = grid.getGridSize();
        char[][] contents = grid.getContents();
        List<Coordinate> coordinates = grid.getCoordinates();

        for (String word : words) {
            Collections.shuffle(coordinates);
            word = word.toUpperCase();
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.getX();
                int y = coordinate.getY();
                Direction selectedDirection = getDirection(word, coordinate, gridSize, contents);
                if (selectedDirection !=  null) {
                    switch (selectedDirection) {
                        case HORIZONTAL:
                            for (char c : word.toCharArray()) {
                                contents[x][y++] = c;
                            }
                            wordInPuzzle.add(word);
                            break;
                        case VERTICAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y] = c;
                            }
                            wordInPuzzle.add(word);
                            break;
                        case DIAGONAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y++] = c;
                            }
                            wordInPuzzle.add(word);
                            break;
                        case HORIZONTAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x][y--] = c;
                            }
                            wordInPuzzle.add(word);
                            break;
                        case VERTICAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x--][y] = c;
                            }
                            wordInPuzzle.add(word);
                            break;
                        case DIAGONAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x--][y--] = c;
                            }
                            wordInPuzzle.add(word);
                            break;
                    }
                } else {
                    continue;
                }
                break;
            }
        }
        randomFillGrid(gridSize, contents);
        grid.setContents(contents);
    }

    private void randomFillGrid(int gridSize, char[][] contents) {
        String allCapLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (contents[i][j] == '_') {
                    int randomIndex = ThreadLocalRandom.current().nextInt(0, allCapLetters.length());
                    contents[i][j] = allCapLetters.charAt(randomIndex);
                }
            }
        }
    }

    private Direction getDirection(String word, Coordinate coordinate, int gridSize, char[][] contents) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        for (Direction direction : directions) {
            if (doesFit(word, coordinate, direction, gridSize, contents)) {
                return direction;
            }
        }
        return null;
    }

    private boolean doesFit(String word, Coordinate coordinate, Direction direction, int gridSize, char[][] contents) {
        int wordLength = word.length();

        switch (direction) {
            case HORIZONTAL:
                if (coordinate.getY() + wordLength < gridSize) {
                    for(int i = 0; i < wordLength; i++) {
                        char letter = contents[coordinate.getX()][coordinate.getY() + i];
                        if (letter != '_' && letter != word.charAt(i)) return false;
                    }
                    return true;
                }
                break;
            case VERTICAL:
                if (coordinate.getX() + wordLength < gridSize) {
                    for(int i = 0; i < wordLength; i++) {
                        char letter = contents[coordinate.getX() + i][coordinate.getY()];
                        if (letter != '_' && letter != word.charAt(i)) return false;
                    }
                    return true;
                }
                break;
            case DIAGONAL:
                if (coordinate.getX() + wordLength < gridSize && coordinate.getY() + word.length() < gridSize) {
                    for(int i = 0; i < wordLength; i++) {
                        char letter = contents[coordinate.getX() + i][coordinate.getY() + i];
                        if (letter != '_' && letter != word.charAt(i)) return false;
                    }
                    return true;
                }
                break;
            case HORIZONTAL_INVERSE:
                if (coordinate.getY() - wordLength >= 0) {
                    for(int i = 0; i < wordLength; i++) {
                        char letter = contents[coordinate.getX()][coordinate.getY() - i];
                        if (letter != '_' && letter != word.charAt(i)) return false;
                    }
                    return true;
                }
                break;
            case VERTICAL_INVERSE:
                if (coordinate.getX() - wordLength >= 0) {
                    for(int i = 0; i < wordLength; i++) {
                        char letter = contents[coordinate.getX() - i][coordinate.getY()];
                        if (letter != '_' && letter != word.charAt(i)) return false;
                    }
                    return true;
                }
                break;
            case DIAGONAL_INVERSE:
                if (coordinate.getX() - wordLength >= 0 && coordinate.getY() - wordLength >= 0) {
                    for(int i = 0; i < wordLength; i++) {
                        char letter = contents[coordinate.getX() - i][coordinate.getY() - i];
                        if (letter != '_' && letter != word.charAt(i)) return false;
                    }
                    return true;
                }
                break;
        }
        return false;
    }
}
