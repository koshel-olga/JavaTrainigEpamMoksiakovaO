package com.epamlearning.moksiakova.lessons.lesson11;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        final Deadlock deadlock1 = new Deadlock("Deadlock1");
        final Deadlock deadlock2 = new Deadlock("Deadlock2");
        new Thread(() -> deadlock1.call(deadlock2)).start();
        new Thread(() -> deadlock2.call(deadlock1)).start();
    }
}
