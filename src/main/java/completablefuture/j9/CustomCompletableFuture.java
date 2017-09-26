package completablefuture.j9;

import java.util.concurrent.*;

public class CustomCompletableFuture<T> extends CompletableFuture<T> {

    private static ExecutorService customExecutor = Executors.newFixedThreadPool(10);

    @Override
    public Executor defaultExecutor() {
        return customExecutor;
    }

    @Override
    public <U> CompletableFuture<U> newIncompleteFuture() {
        return new CustomCompletableFuture<>();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> promise = new CustomCompletableFuture<>();

        promise.completeAsync(() -> {
            System.out.println("Action ran in: " + Thread.currentThread().getName());
            return 12;
        })
                .thenAccept(System.out::println);

        customExecutor.shutdown();
        customExecutor.awaitTermination(2000, TimeUnit.SECONDS);
    }
}
