package com.epamlearning.moksiakova.lessons.lesson10.TaskTwo;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * Class for task 2 with for.
 */
@Slf4j
public class TaskTwoWithFor extends TaskTwoBase{

    /**
     * Init method.
     */
    public void run() {
        super.run();
        List<String> objects = this.readAllStringsFromFile();
        for (String obj : objects ) {
            String stringToObj = new String(Base64.getDecoder().decode(obj));
            Optional<Ham> ham = this.createObjectFromString(stringToObj);
            ham.ifPresentOrElse(
                    hamObj -> log.info("Create object Ham : {}",hamObj.toString()),
                    () -> log.info("can not create object ham from string : {}", stringToObj)
            );
        }
    }
}
