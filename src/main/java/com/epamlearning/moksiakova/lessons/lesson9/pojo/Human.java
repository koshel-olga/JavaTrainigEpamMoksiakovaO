package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;

/**
 * POJO class.
 */
@Entity
public class Human {

    @Value("5")
    private int age;

    @Value
    private String name;

    private String description;

    @Value("Buratino")
    public void setAge(Integer age) {
        this.age = age;
    }

    @Value("very good boy")
    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
