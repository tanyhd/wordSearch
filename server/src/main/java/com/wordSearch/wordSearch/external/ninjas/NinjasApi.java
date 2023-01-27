package com.wordSearch.wordSearch.external.ninjas;

import com.wordSearch.wordSearch.models.WordResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Component;

@Component
public interface NinjasApi {

    @RequestLine("GET /v1/randomword")
    @Headers("X-Api-Key: {apiKey}")
    WordResponse getRandomWord(
            @Param("apiKey") String apiKey
    );
}
