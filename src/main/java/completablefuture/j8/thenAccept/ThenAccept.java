package completablefuture.j8.thenAccept;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThenAccept {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor)
                .thenAccept(result -> {
                    System.out.println(result);
                    System.out.println("Output action ran in: " + Thread.currentThread().getName());
                });

        executor.shutdown();
    }
}
