package completablefuture.j9;

import util.QuoteUtil;

import java.util.concurrent.*;

public class MinimalCompletionStage {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> original = CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor);

        CompletionStage<String> copy =
                original.minimalCompletionStage();

        CompletableFuture<String> convertedBack
                = copy.toCompletableFuture();

        executor.shutdown();
    }
}
