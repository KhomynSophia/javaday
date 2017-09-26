package flow.demandcontrol;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleItemSubscriber<T> implements Subscriber<T> {
    private final AtomicInteger amountOfMessagesToConsume;
    private Subscription subscription;
    private List<T> consumedElements = new LinkedList<>();

    SingleItemSubscriber(Integer amountOfMessagesToConsume) {
        this.amountOfMessagesToConsume = new AtomicInteger(amountOfMessagesToConsume);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("Received : " + item);
        consumedElements.add(item);
        amountOfMessagesToConsume.decrementAndGet();
        if (amountOfMessagesToConsume.get() > 0) {
            subscription.request(1);
        }
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