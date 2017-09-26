package completablefuture.j8.creation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunAsync {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        String description =
                "Action ran before completing the returned CompletableFuture ";

        CompletableFuture
                .runAsync(() -> System.out.println(description), executor);

        executor.shutdown();
    }
}
