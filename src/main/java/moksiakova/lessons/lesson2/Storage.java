package main.java.moksiakova.lessons.lesson2;

/**
 */

import java.util.Objects;

public class Storage<T> {
    private Object[] storage;
    private int capacity = 10;
    private Cache<T> cache;

    public Storage() {
        this.cache = new Cache<T>(this.capacity);
        this.storage = new Objects[this.capacity];
    }

    public Storage(T[] storageElements) {
        this.capacity = storageElements.length;
        this.storage = new Objects[this.capacity];
        this.cache = new Cache<>(this.capacity);

        for(int i = 0; i < this.capacity; i++) {
            this.storage[i] = storageElements[i];
        }
    }

    //Если мы достигли предела длины массива, то мы должны увеличить емкость нашего хранилища в 1.5 раза.
    public void add(T element) {}

    /**
     * Реализовать метод удаления последнего элемента из массива
     *     Тут мы будем использовать наш класс Cache.
     *     Сначала проверяем есть ли наш объект в кэше.
     *     Если есть, то удаляем его оттуда.
     *     После удаляем объект из массива.*/
    public void delete() {}

    /**    Реализовать метод удаления всех элементов из массива.
     Тут мы будем использовать наш класс Cache.
     Удаляем все из кэша.
     */
    public void clear() {}

    /**
     * Реализовать метод получения последнего элемента из массива
     *     Нужно учитывать, что размер массива может быть к примеру 15, а заполнено всего 7 элементов,
     *     следовательно должен вернуться элемент под индексом 6.*/
    public T getLast() {}

    /**Реализовать метод получения элемента из массива по индексу
     Тут мы будем использовать наш класс Cache.
     Сначала мы должны проверить есть ли в кеше такой объект.
     Если есть, то возвращаем его, не идем в наш массив, давайте представим,
     что это очень долгая и сложная операция и будет логичнее использовать кэш.
     Если объекта не оказалось в кэше, то мы берем объект из нашего массива, добавляем его в кэш и возвращаем.
     Создать примеры использования полученного класса.*/
    public T get(int index) {}
}
