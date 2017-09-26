package completablefuture.j9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class OrTimeout {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long timeout = 5;

        CompletableFuture<String> incompleteFuture = new CompletableFuture<>();

        CompletableFuture<String> releaseYear = CompletableFuture
                .supplyAsync(() -> "\n1985");

        //incompleteFuture.complete("completed");

        CompletableFuture<String> promise = incompleteFuture
                .thenCombine(releaseYear,
                        String::concat)
                .orTimeout(timeout, TimeUnit.SECONDS)
                .whenComplete((result, error) -> {
                    if (error == null) {
                        System.out.println("The result is: " + result);
                    } else {
                        System.out.println("Sorry, timeout is " + timeout + " seconds");
                    }
                });

        promise.get();
    }
}
