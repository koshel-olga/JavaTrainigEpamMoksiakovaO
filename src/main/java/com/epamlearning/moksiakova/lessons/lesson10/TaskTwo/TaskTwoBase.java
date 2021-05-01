package com.epamlearning.moksiakova.lessons.lesson10.TaskTwo;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class for task2.
 */
@Slf4j
@NoArgsConstructor
public class TaskTwoBase {

    /**
     * Class for work with files.
     */
    protected FileHandler fileHandler;

    /**
     * Path to File with objects as string.
     */
    protected final String pathToFile = "File.txt";

    /**
     * Init method.
     */
    public void run() {
        this.fileHandler = new FileHandler();
    }

    /**
     * Read all string from file to collection.
     * @return collection of string.
     */
    protected List<String> readAllStringsFromFile() {
        Optional<List<String>> readedLines = this.fileHandler.readAllFile(pathToFile);
        return readedLines.orElse(Collections.emptyList());
    }

    /**
     * Create object from string form file.
     * @param decodeString string from file.
     * @return {@link Optional<Ham>}.
     */
    protected Optional<Ham> createObjectFromString(String decodeString) {
        //decodeString: type='Докторская', weight=1134, cost=9934
        String regex = "^type=\\'(?<typeValue>.*)\\',\\s*weight=(?<weightValue>\\d*),\\s*cost=(?<costValue>\\d*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(decodeString);
        if (matcher.find()) {
            String typeValue = matcher.group("typeValue");
            int weightValue = Integer.parseInt(matcher.group("weightValue"));
            long costValue = Long.parseLong(matcher.group("costValue"));
            return Optional.of(new Ham(typeValue,weightValue,costValue));
        }
        return Optional.empty();
    }
}
