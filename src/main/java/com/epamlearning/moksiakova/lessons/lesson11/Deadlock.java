package com.epamlearning.moksiakova.lessons.lesson11;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Deadlock {

    private final String threadName;

    public Deadlock(String threadName){
        this.threadName = threadName;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public synchronized void call(Deadlock thread){
        log.info("{} wants call to me {}", this.getThreadName(), thread.getThreadName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.callMe(this);
    }

    public synchronized void callMe(Deadlock caller){
        log.info("{}  has called me  {}",this.getThreadName(), caller.getThreadName());
    }
}