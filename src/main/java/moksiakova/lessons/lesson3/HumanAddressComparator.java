package main.java.moksiakova.lessons.lesson3;
import java.util.Comparator;

/** Компаратор для сортировка класса {@link Human} по адресу. */
public class HumanAddressComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o1.getAddress().toString().compareTo(o2.getAddress().toString());
    }
}

