package completablefuture.j8.creation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupplyAsyncWithCustomExecutor {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> promise = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Action ran in: " + Thread.currentThread().getName());
                    return 12;
                }, executor);

        int completedFuture = promise.get();

        System.out.println("The new CompletableFuture: " + completedFuture);

        executor.shutdown();
    }
}
