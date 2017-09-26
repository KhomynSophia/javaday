package completablefuture.j8.any;

import util.QuoteUtil;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnyOf {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> quote = CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor);

        CompletableFuture<List<String>> quotes = CompletableFuture
                .supplyAsync(quoteUtil::getQuotes, executor);

        CompletableFuture<String> releaseYear = CompletableFuture
                .supplyAsync(() -> "1985", executor);

        CompletableFuture
                .anyOf(quote, quotes, releaseYear)
                .thenAccept(System.out::println);

        executor.shutdown();
    }
}
