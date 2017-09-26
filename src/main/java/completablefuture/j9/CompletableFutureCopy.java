package completablefuture.j9;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureCopy {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> original =
                CompletableFuture
                        .supplyAsync(quoteUtil::getQuote, executor);

        System.out.println("Created original CF: " + original.get());

        CompletableFuture<String> copy = original.copy();
        System.out.println("Created copy: " + copy.get());

        System.out.print("Modified the copy: ");
        copy
                .thenApply(result -> result.concat("\nBack to the future"))
                .thenAccept(System.out::println);

        System.out.println("Original is preserved: " + original.get());

        executor.shutdown();
    }
}
