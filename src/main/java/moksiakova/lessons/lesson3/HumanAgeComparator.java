package main.java.moksiakova.lessons.lesson3;

import java.util.Comparator;

public class HumanAgeComparator implements Comparator<Human> {

    @Override
    public int compare(Human o1, Human o2) {
        return o1.getAge() - o2.getAge();
    }
}
