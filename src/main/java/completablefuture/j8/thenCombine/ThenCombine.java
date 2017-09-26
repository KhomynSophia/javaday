package completablefuture.j8.thenCombine;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThenCombine {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> quote = CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor);

        CompletableFuture<String> releaseYear = CompletableFuture
                .supplyAsync(() -> "\n1985");

        quote
                .thenCombine(releaseYear,
                        String::concat)
                .thenAccept(System.out::println);

        long timeout = 2000;
        executor.shutdown();
        executor.awaitTermination(timeout, TimeUnit.MILLISECONDS);
    }
}
