package flow.demandcontrol;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class DemandControlTest {

    @Test
    public void consumeOneElementTest() throws InterruptedException {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        SingleItemSubscriber<String> subscriber = new SingleItemSubscriber<>(1);
        publisher.subscribe(subscriber);

        List<String> items = List.of("1", "2", "3");
        List<String> expectedResult = List.of("1");

        items.forEach(publisher::submit);
        publisher.close();

        Thread.sleep(1000);
        assertEquals(expectedResult.size(), subscriber.getConsumedElements().size());
    }
}
