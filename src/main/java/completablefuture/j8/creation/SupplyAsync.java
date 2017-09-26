package completablefuture.j8.creation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> promise = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Action ran in: " + Thread.currentThread().getName());
                    return 12;
                });

        int completedFuture = promise.get();

        System.out.println("The new CompletableFuture: " + completedFuture);
    }
}
