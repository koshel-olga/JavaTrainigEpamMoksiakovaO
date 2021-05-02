package com.epamlearning.moksiakova.lessons.lesson10;

import com.epamlearning.moksiakova.lessons.lesson10.TaskOne.TaskOneWithFor;
import com.epamlearning.moksiakova.lessons.lesson10.TaskOne.TaskOneWithStream;
import com.epamlearning.moksiakova.lessons.lesson10.TaskTwo.TaskTwoWithFor;
import com.epamlearning.moksiakova.lessons.lesson10.TaskTwo.TaskTwoWithStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("========== Begin task1 with for ===============");
        TaskOneWithFor taskOneWithFor = new TaskOneWithFor();
        taskOneWithFor.run();
        log.info("========== End task1 with for ===============");
        log.info("========== Begin task1 with stream ===============");
        TaskOneWithStream taskOneWithStream = new TaskOneWithStream();
        taskOneWithStream.run();
        log.info("========== End task1 with stream ===============");
        log.info("========== Begin task2 with for ===============");
        TaskTwoWithFor taskTwoWithFor = new TaskTwoWithFor();
        taskTwoWithFor.run();
        log.info("========== End task2 with for ===============");
        log.info("========== Begin task2 with stream ===============");
        TaskTwoWithStream taskTwoWithStream = new TaskTwoWithStream();
        taskTwoWithStream.run();
        log.info("========== End task2 with stream ===============");
    }
}
