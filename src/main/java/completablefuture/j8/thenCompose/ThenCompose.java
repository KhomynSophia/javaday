package completablefuture.j8.thenCompose;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThenCompose {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> promise = CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor)
                .thenCompose(result ->
                        CompletableFuture.supplyAsync(() ->
                                quoteUtil.appendQuote(result)));

        promise.thenAccept(System.out::print);

        long timeout = 2000;
        executor.shutdown();
        executor.awaitTermination(timeout, TimeUnit.MILLISECONDS);
    }
}
