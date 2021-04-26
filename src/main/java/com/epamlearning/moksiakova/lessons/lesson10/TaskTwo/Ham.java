package com.epamlearning.moksiakova.lessons.lesson10.TaskTwo;

public class Ham {
    private String type;
    private int weight;
    private long cost;

    public Ham(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ham{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
