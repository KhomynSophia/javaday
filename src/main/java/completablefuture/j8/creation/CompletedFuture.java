package completablefuture.j8.creation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletedFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> promise = CompletableFuture.completedFuture(12);

        int completedFuture = promise.get();

        System.out.println("The completed CompletableFuture: " + completedFuture);
    }
}
