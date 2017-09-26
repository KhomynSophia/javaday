package completablefuture.j8.creation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EmptyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> promise = new CompletableFuture<>();

        promise.complete(12);

        int completedFuture = promise.get();

        System.out.println("Manually completed CompletableFuture: " + completedFuture);
    }
}
