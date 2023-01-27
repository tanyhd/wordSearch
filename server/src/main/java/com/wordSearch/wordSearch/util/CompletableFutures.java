package com.wordSearch.wordSearch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutures {

    public static <T> CompletableFuture<List<T>> collect(List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]))
                .thenApply(ignored -> {
                    List<T> result = new ArrayList<>(futures.size());
                    futures.forEach(future -> result.add(future.join()));
                    return result;
                });
    }

    public static CompletableFuture<List<Object>> collectRaw(CompletableFuture... futures) {
        return collect(Arrays.asList(futures));
    }
}
