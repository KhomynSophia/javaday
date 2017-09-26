package completablefuture.j9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DelayedExecutor {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> promise = new CompletableFuture<>();

        promise.completeAsync(() -> 12, CompletableFuture.
                delayedExecutor(5, TimeUnit.SECONDS))
                .thenAccept(System.out::println);

        promise.get();
    }
}
