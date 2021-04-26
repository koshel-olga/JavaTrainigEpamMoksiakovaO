package com.epamlearning.moksiakova.lessons.lesson10;

import com.epamlearning.moksiakova.lessons.lesson10.TaskOne.TaskOneWithFor;
import com.epamlearning.moksiakova.lessons.lesson10.TaskTwo.TaskTwoWithFor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        //TaskOneWithFor taskOneWithFor = new TaskOneWithFor();
        //taskOneWithFor.run();
        TaskTwoWithFor taskTwoWithFor = new TaskTwoWithFor();
        taskTwoWithFor.run();
    }
}
