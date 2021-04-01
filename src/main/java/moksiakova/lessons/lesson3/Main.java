package main.java.moksiakova.lessons.lesson3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Address addressTlt = new Address("Тольятти","Южное шоссе", "27",135);
        Address addressMoscow = new Address("Москва","Южное шоссе", "27",135);
        Address addressHouse = new Address("Ягодное","Заветная", "27", null);
        Address addressTltOther = new Address("Тольятти","улица Юбилейная", "27",135);

        Human human1 = new Human("Иванов Иван Иванович", 35, addressTlt);
        Human human2 = human1;
        Human human3 = new Human("Петров Петр Петрович", 12, addressMoscow);
        Human human4 = human3;
        Human human5 = new Human("Калугина Мария Степановна", 67, addressHouse);
        Human human6 = human5;
        Human human7 = new Human("Попова Ксения Сергеевна", 8, addressTltOther);
        Human human8 = new Human("Петрова Мария Константиновна", 44, addressMoscow);
        Human human9 = new Human("Петров Петр Андреевич", 46, addressMoscow);
        Human human10 = new Human("Калугин Сергей Алексеевич", 60, addressHouse);

        ArrayList<Human> humanArray = new ArrayList<>();
        humanArray.add(human1);
        humanArray.add(human2);
        humanArray.add(human3);
        humanArray.add(human4);
        humanArray.add(human5);
        humanArray.add(human6);
        humanArray.add(human7);
        humanArray.add(human8);
        humanArray.add(human9);
        humanArray.add(human10);

        Lesson3 lesson3 = new Lesson3();

        Set<Human> duplicateHuman = lesson3.findDuplicate(humanArray);
        System.out.println(duplicateHuman);

        Set<Human> humanSet = new HashSet<>();
        humanSet.addAll(humanArray);
        System.out.println(humanSet);

        Comparator humanNameComparator = new HumanNameComparator();
        humanArray.sort(humanNameComparator);
        System.out.println(humanArray);
        Comparator humanAddressComparator = new HumanAddressComparator();
        humanArray.sort(humanAddressComparator);
        System.out.println(humanArray);
        Comparator humanAgeComparator = new HumanAgeComparator();
        humanArray.sort(humanAgeComparator);
        System.out.println(humanArray);


        User user = new User("Moksiakova Olga Yurievna","ADMIN");
        lesson3.hiUser(user);
    }
}
