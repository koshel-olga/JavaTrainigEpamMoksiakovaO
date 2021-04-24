package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;

@Entity
public class Human {

    @Value(value="5")
    private int age;

    @Value
    private String name;

    private String description;

    @Value(value="Buratino")
    public void setAge(Integer age) {
        this.age = age;
    }

    @Value(value="very good boy")
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
