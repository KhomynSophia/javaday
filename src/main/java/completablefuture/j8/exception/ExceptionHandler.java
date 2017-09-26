package completablefuture.j8.exception;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionHandler {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture
                .supplyAsync(quoteUtil::emptyQuote, executor)
                .thenApply(String::length)
                .handle((result, throwable) -> {
                    if (throwable != null) {
                        return "No quote available: "
                                + throwable;
                    } else {
                        return result.toString();
                    }
                })
                .thenAccept(System.out::println);

        executor.shutdown();
    }
}
