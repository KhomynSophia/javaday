package completablefuture.j8.exception;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exceptionally {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture
                .supplyAsync(quoteUtil::emptyQuote, executor)
                .thenApply(String::length)
                .thenApply(Object::toString)
                .exceptionally(exception -> "No quote available")
                .thenAccept(System.out::println);

        executor.shutdown();
    }
}
