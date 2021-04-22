package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;

@Entity
public class Human {

    @Value(value="5")
    private int age;

    @Value(value="Buratino")
    private String name;

    public Human() {}
}
