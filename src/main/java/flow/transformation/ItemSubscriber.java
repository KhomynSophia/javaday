package flow.transformation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ItemSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;
    private List<T> consumedElements = new LinkedList<>();

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("Received : " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }

    List<T> getConsumedElements() {
        return consumedElements;
    }
}