package completablefuture.j8.thenapply;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThenApplyAsync {

    private static final String MOVIE_NAME = "\nBack to the future";

    private static ExecutorService juniorExecutor = Executors.newCachedThreadPool();
    private static ExecutorService seniorExecutor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("CF created in: " + Thread.currentThread().getName());
                    return quoteUtil.getQuote();
                }, juniorExecutor)
                .thenApply(result -> {
                    System.out.println("Action on CF ran in: " + Thread.currentThread().getName());
                    return result.concat(MOVIE_NAME);
                })
                .thenApplyAsync(result -> {
                    System.out.println("Another action ran in: " + Thread.currentThread().getName());
                    return result.length();
                }, seniorExecutor)
                .thenAccept(result -> {
                    System.out.println(result);
                    System.out.println("Output action ran in: " + Thread.currentThread().getName());
                });

        long timeout = 2000;
        juniorExecutor.shutdown();
        juniorExecutor.awaitTermination(timeout, TimeUnit.MILLISECONDS);
        seniorExecutor.shutdown();
        seniorExecutor.awaitTermination(timeout, TimeUnit.MILLISECONDS);

    }
}
