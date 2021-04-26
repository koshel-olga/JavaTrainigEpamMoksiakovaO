package com.epamlearning.moksiakova.lessons.lesson10.TaskTwo;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskTwoWithFor {

    private FileHandler fileHandler;
    private final String pathToFile = "File.txt";

    public void run() {
        this.fileHandler = new FileHandler();
        List<String> objects = this.readAllStringsFromFile();
        for (String obj : objects ) {
            Ham ham = this.createObjectFromString(new String(Base64.getDecoder().decode(obj)));
        }
    }

    private List<String> readAllStringsFromFile() {
        Optional<List<String>> readedLines = this.fileHandler.readAllFile(pathToFile);
        if (readedLines.isPresent()) {
            return readedLines.get();
        }
        return Collections.emptyList();
    }

    private Ham createObjectFromString(String decodeString) {
        String regex = "^type=\\'(?<typeValue>.*)\\',\\s*weight=(?<weightValue>\\d*),\\s*cost=(?<costValue>\\d*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(decodeString);
        if (matcher.find()) {
            String typeValue = matcher.group("typeValue");
            int weightValue = Integer.parseInt(matcher.group("weightValue"));
            long costValue = Long.parseLong(matcher.group("weightValue"));
            return new Ham(typeValue,weightValue,costValue);
        }
        return null;
    }
}
