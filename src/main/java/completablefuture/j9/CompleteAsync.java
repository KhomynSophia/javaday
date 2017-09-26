package completablefuture.j9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteAsync {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        CompletableFuture<Integer> promise = new CompletableFuture<>();

        promise.completeAsync(() -> {
            System.out.println("Action ran in: " + Thread.currentThread().getName());
            return 12;
        }, executor)
                .thenAccept(System.out::println);

        executor.shutdown();
    }
}
