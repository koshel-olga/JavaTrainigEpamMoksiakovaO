package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;

@Entity
public class Human {

    @Value(value="5")
    private int age;


    private String name;

    @Value(value="3")
    private boolean isChild;

    @Value(value="Buratino")
    public void setName(String name) {
        this.name = name;
    }

    @Value(value="Buratino")
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", isChild='" + isChild + '\'' +
                '}';
    }
}
