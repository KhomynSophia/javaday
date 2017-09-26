package completablefuture.j8.thenapply;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackHell {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor)
                .thenApply(result ->
                        CompletableFuture.supplyAsync(() ->
                                quoteUtil.appendQuote(result)));

        executor.shutdown();
    }
}
