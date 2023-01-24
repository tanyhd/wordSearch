package com.wordSearch.wordSearch.controllers;

import com.wordSearch.wordSearch.models.GridRequest;
import com.wordSearch.wordSearch.models.GridResponse;
import com.wordSearch.wordSearch.services.WordGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/wordgrid")
public class WordSearchController {

    @Autowired
    WordGridService wordGridService;

    @PostMapping
    public GridResponse createWordGrid(
            @RequestBody GridRequest gridRequest
            ) {
        return wordGridService.createWordGrid(gridRequest.getSize(), gridRequest.getWords());
    }
}
