package com.wordSearch.wordSearch.controllers;

import com.wordSearch.wordSearch.models.GridRequest;
import com.wordSearch.wordSearch.models.GridResponse;
import com.wordSearch.wordSearch.services.RandomWordService;
import com.wordSearch.wordSearch.services.WordGridService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/wordgrid")
public class WordSearchController {

    private final WordGridService wordGridService;
    private final RandomWordService randomWordService;

    public WordSearchController(
            RandomWordService randomWordService,
            WordGridService wordGridService) {
        this.wordGridService = wordGridService;
        this.randomWordService = randomWordService;
    }

    @PostMapping
    public GridResponse createWordGrid(
            @RequestBody GridRequest gridRequest
            ) {
        return wordGridService.createWordGrid(gridRequest.getSize(), gridRequest.getWords());
    }

    @GetMapping("/randomWordGrid")
    public CompletableFuture<GridResponse> getRandomWordGrid() {
        return randomWordService.getRandomWordGrid();
    }
}
