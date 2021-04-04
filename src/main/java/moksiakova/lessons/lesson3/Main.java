package main.java.moksiakova.lessons.lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Address addressTlt = new Address("Тольятти","Южное шоссе", "27",135);
        Address addressMoscow = new Address("Москва","Южное шоссе", "27",135);
        Address addressHouse = new Address("Ягодное","Заветная", "27", null);
        Address addressTltOther = new Address("Тольятти","улица Юбилейная", "27",135);

        Human human1 = new Human();
        human1.setName("Иванов Иван Иванович");
        human1.setAge(35);
        human1.setAddress(addressTlt);
        Human human2 = human1;
        Human human3 = new Human();
        human3.setName("Петров Петр Петрович");
        human3.setAge(12);
        human3.setAddress(addressMoscow);
        Human human4 = human3;
        Human human5 = new Human();
        human5.setName("Калугина Мария Степановна");
        human5.setAge(67);
        human5.setAddress(addressHouse);
        Human human6 = human5;
        Human human7 = new Human();
        human7.setName("Попова Ксения Сергеевна");
        human7.setAge(8);
        human7.setAddress(addressTltOther);
        Human human8 = new Human();
        human8.setName("Петрова Мария Константиновна");
        human8.setAge(44);
        human8.setAddress(addressMoscow);
        Human human9 = new Human();
        human9.setName("Петров Петр Андреевич");
        human9.setAge(46);
        human9.setAddress(addressMoscow);
        Human human10 = new Human();
        human10.setName("Калугин Сергей Алексеевич");
        human10.setAge(60);
        human10.setAddress(addressHouse);

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

        Set<Human> humanSet = new HashSet<>(humanArray);
        System.out.println(humanSet);

        Comparator<Human> humanNameComparator = new HumanNameComparator();
        humanArray.sort(humanNameComparator);
        System.out.println(humanArray);
        Comparator<Human> humanAddressComparator = new HumanAddressComparator();
        humanArray.sort(humanAddressComparator);
        System.out.println(humanArray);
        Comparator<Human> humanAgeComparator = new HumanAgeComparator();
        humanArray.sort(humanAgeComparator);
        System.out.println(humanArray);

        User user = new User("Moksiakova Olga Yurievna","ADMIN");
        lesson3.hiUser(user);

        HashMap<Integer,User> collection = new HashMap<>();
        collection.put(5, new User("Roman", "USER"));
        collection.put(105, new User("Stepa", "MODERATOR"));
        collection.put(89, new User("Alex", "USER"));
        collection.put(1, new User("Denis", "ADMIN"));

        TreeMap<Integer,User> sortedByKeys = lesson3.sortByKey(collection);

        LinkedHashMap<Integer,User> sortByValues = lesson3.sortByValue(collection);

        Human errorAge = new Human();
        errorAge.setAge(-23);
        User errorUser = new User("Oleg","sdfs");
    }
}
