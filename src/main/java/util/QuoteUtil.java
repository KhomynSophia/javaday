package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteUtil {

    private String folderPath;
    private FileReader fileReader;
    private String quote;

    public QuoteUtil() {
        ClassLoader classLoader = getClass().getClassLoader();
        folderPath = new File(classLoader.getResource("quotes")
                .getFile()).getPath();
        fileReader = new FileReader();
        String path = initPath();
        quote = fileReader.readFile(path);
    }

    public String getQuote() {
        return quote;
    }

    public String appendQuote(String resultQuote) {
        String path = initPath();
        return resultQuote
                .concat(fileReader.readFile(path));
    }

    public List<String> getQuotes() {
        List<String> quotes = new ArrayList<>();
        quotes.add(quote);
        String path = initPath();
        quotes.add(fileReader.readFile(path));
        return quotes;
    }

    public String emptyQuote() {
        return null;
    }

    private String initPath() {
        int quoteNumber = new Random()
                .ints(1, 5)
                .findFirst()
                .getAsInt();

        return folderPath
                .concat("\\" + String.valueOf(quoteNumber));
    }
}
