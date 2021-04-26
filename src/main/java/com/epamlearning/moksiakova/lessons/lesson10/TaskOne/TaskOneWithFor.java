package com.epamlearning.moksiakova.lessons.lesson10.TaskOne;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Task 1:
 *
 * Одним стримом сгенерировать коллекцию с 10000 рандомными элементами UUID.
 *
 * Записать в файл.
 *
 * Одним стримом считать этот файл и посчитать количество элементов UUID,
 * в которых сумма цифр > 100
 *
 * Найти дату конца света по формуле: сегодня + N месяцев + M дней,
 *
 * где N – первые два числа от полученного значения, а М – вторые.
 *
 * Значение с ведущими нулями, если цифр меньше 4.
 * По тихоокеанской временной зоне.
 * Дату вывести в формате ISO с датой и временем (см DateTimeFormatter)
 */

/**
 * Class for demonstration the task1 with for.
 */
@Slf4j
public class TaskOneWithFor {

    private FileHandler fileHandler;
    private final String pathToFile = "UUID.txt";

    public void run() {
        //todo add clear file
        this.fileHandler = new FileHandler();
        List<UUID> uuidList = this.generateUUID();
        this.writeToFileCollection(uuidList);
        List<String> uuidsFromFile = this.readAllUuidFromFiles();
        int countUUID = 0;
        for (String uuidFromFile : uuidsFromFile) {
            if (this.sumAllNumberInUuid(uuidFromFile) > 100) {
                countUUID++;
            }
        }
        log.info("{}",countUUID);
        String dayOff = this.dayOff(countUUID);
        log.info("day off is : {}",dayOff);
    }

    private List<UUID> generateUUID() {
        ArrayList<UUID> uuidList = new ArrayList<>();
        for(int i=0; i<10000; i++) {
            uuidList.add(UUID.randomUUID());
        }
        return uuidList;
    }

    private void writeToFileCollection(Collection<UUID> uuids) {
        for (UUID uuid: uuids) {
            this.fileHandler.fileWriteToEnd(pathToFile, uuid.toString());
        }
    }

    private List<String> readAllUuidFromFiles() {
        Optional<List<String>> readedLines = this.fileHandler.readAllFile(pathToFile);
        if (readedLines.isPresent()) {
            return readedLines.get();
        }
        return Collections.emptyList();
    }

    private int sumAllNumberInUuid(String uuid) {
        int sum = 0;
        String[] charsUUID = uuid.split("");
        for (String charUUID : charsUUID) {
            if (this.isNumeric(charUUID)) {
                sum += Integer.valueOf(charUUID);
            }
        }
        return sum;
    }

    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("\\d");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private String dayOff(int param) {
        String paramToCount = Integer.toString(param);
        while (paramToCount.length() < 4 ) {
            paramToCount = "0"+paramToCount;
        }
        Integer paramMonth = Integer.valueOf(paramToCount.substring(0,1));
        Integer paramDay = Integer.valueOf(paramToCount.substring(2,3));

        LocalDateTime dayOff = LocalDateTime.now().plusMonths(paramMonth).plusDays(paramDay);
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dayOff, zoneId);
        String output = zonedDateTime.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        return output;
    }
}
