package main.java.moksiakova.lessons.lesson3;

import java.util.Comparator;

/** Компаратор для сортировка класса {@link Human} по ФИО. */
public class HumanNameComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
