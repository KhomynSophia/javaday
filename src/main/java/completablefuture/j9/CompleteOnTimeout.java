package completablefuture.j9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompleteOnTimeout {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> incompleteFuture = new CompletableFuture<>();

        CompletableFuture<String> completedFuture =
                incompleteFuture
                        .completeOnTimeout
                                ("default value", 5, TimeUnit.SECONDS);

        System.out.println(completedFuture.get());
    }
}
