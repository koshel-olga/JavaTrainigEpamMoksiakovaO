package com.epamlearning.moksiakova.lessons.lesson10.TaskOne;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


/**
 * Base class for Task1.
 */
public class TaskOneBase {

    /**
     * Class for work with files.
     */
    protected FileHandler fileHandler;

    /**
     * Path to File with generated UUID.
     */
    protected final String pathToFile = "UUID.txt";

    /**
     * Init method.
     */
    public void run() {
        this.fileHandler = new FileHandler();
        this.fileHandler.clearFile(pathToFile);
    }

    /**
     * Check if string can convert to numeric
     * @param strNum string for convert.
     * @return boolean.
     */
    protected boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("\\d");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    /**
     * Count day off from parameter.
     * @param param param to count.
     * @return String.
     */
    protected String dayOff(int param) {
        String paramToCount = Integer.toString(param);
        while (paramToCount.length() < 4 ) {
            paramToCount = "0"+paramToCount;
        }
        int paramMonth = Integer.parseInt(paramToCount.substring(0,2));
        int paramDay = Integer.parseInt(paramToCount.substring(2,4));

        LocalDateTime dayOff = LocalDateTime.now().plusMonths(paramMonth).plusDays(paramDay);
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dayOff, zoneId);
        String output = zonedDateTime.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        return output;
    }

    /**
     * Create collection of UUID from strings.
     * @return collection of uuids.
     */
    protected List<String> readAllUuidFromFiles() {
        Optional<List<String>> readedLines = this.fileHandler.readAllFile(pathToFile);
        return readedLines.orElse(Collections.emptyList());
    }
}
