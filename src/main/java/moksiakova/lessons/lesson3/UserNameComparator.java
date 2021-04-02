package main.java.moksiakova.lessons.lesson3;

import java.util.Comparator;

/** Компаратор для сортировка класса {@link User} по имени. */
public class UserNameComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getName().compareTo(o2.getName());
    }
}