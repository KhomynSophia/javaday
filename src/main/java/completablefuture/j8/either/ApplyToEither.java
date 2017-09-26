package completablefuture.j8.either;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplyToEither {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> quote = CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor);

        CompletableFuture<String> releaseYear = CompletableFuture
                .supplyAsync(() -> "\n1985");

        quote
                .applyToEither(releaseYear, String::length)
                .thenAccept(System.out::println);

        executor.shutdown();
    }
}
