package completablefuture.j8.all;

import util.QuoteUtil;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AllOf {

    public static void main(String[] args) {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> quote = CompletableFuture
                .supplyAsync(quoteUtil::getQuote);

        CompletableFuture<List<String>> quotes = CompletableFuture
                .supplyAsync(quoteUtil::getQuotes);

        CompletableFuture<String> releaseYear = CompletableFuture
                .supplyAsync(() -> "1985");

        CompletableFuture.
                allOf(quote, quotes, releaseYear);
    }
}
