package util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileReader {

    private static final Logger LOGGER =
            Logger.getLogger(FileReader.class);

    String readFile(String path) {

        List<String> content = new ArrayList<>();

        try (Stream<String> stream = Files
                .lines(Paths.get(path))) {
            content = stream
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("The following error has occured" +
                    " during processing the file: ", e);
        }

        return content.toString();
    }
}
