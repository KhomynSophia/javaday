package flow.transformation;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;

public class TransformProcessor<T, R> extends SubmissionPublisher<R>
        implements Processor<T, R> {

    private Subscription subscription;
    private Function<T, R> function;

    TransformProcessor(Function<T, R> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit(function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}