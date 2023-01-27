package com.wordSearch.wordSearch.services;

import com.wordSearch.wordSearch.external.ninjas.NinjasApi;
import com.wordSearch.wordSearch.models.GridResponse;
import com.wordSearch.wordSearch.models.WordResponse;
import com.wordSearch.wordSearch.util.CompletableFutures;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import static com.wordSearch.wordSearch.util.Constant.NINJAS_API_KEY;

@Service
public class RandomWordService {

    private final NinjasApi ninjasApi;
    private final WordGridService wordGridService;
    public RandomWordService(
            NinjasApi ninjasApi,
            WordGridService wordGridService) {
        this.ninjasApi = ninjasApi;
        this.wordGridService = wordGridService;
    }

    public CompletableFuture<GridResponse> getRandomWordGrid() {
        return CompletableFutures.collectRaw(
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY)),
                CompletableFuture.supplyAsync(() -> ninjasApi.getRandomWord(NINJAS_API_KEY))
        ).thenApply(response -> {
            List<String> randomWordList = new ArrayList<>();
            for (Object word :response) {
                WordResponse wordInReponse = (WordResponse) word;
                randomWordList.add(wordInReponse.getWord());
            }
            int randomNum = ThreadLocalRandom.current().nextInt(8, 20);
            return wordGridService.createWordGrid(randomNum, randomWordList);
        });
    }
}
