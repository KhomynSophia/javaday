package flow.transformation;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class TransformProcessorTest {

    @Test
    public void transformAndConsumeAllElementsTest() throws InterruptedException {

        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        Function<Integer, String> convertToString = Object::toString;
        Function<String, String> addQuoteMarks = quote -> "'" + quote + "'";
        Function<Integer, String> quote = addQuoteMarks.compose(convertToString);

        TransformProcessor<Integer, String> transformProcessor = new TransformProcessor<>(quote);
        ItemSubscriber<String> subscriber = new ItemSubscriber<>();

        List<Integer> items = List.of(1, 2, 3);
        List<String> expectedResult = List.of("'1'", "'2'", "'3'");

        publisher.subscribe(transformProcessor);
        transformProcessor.subscribe(subscriber);
        items.forEach(publisher::submit);
        publisher.close();

        Thread.sleep(1000);
        assertEquals(expectedResult.size(), subscriber.getConsumedElements().size());
    }
}
