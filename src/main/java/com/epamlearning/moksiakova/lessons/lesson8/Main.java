package com.epamlearning.moksiakova.lessons.lesson8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Cache<String> cacheString;
        try {
            cacheString = new Cache<>(-3);
        } catch (IllegalCacheArgumentException exception) {
            log.error(exception.getMessage());
            cacheString = new Cache<>(Math.abs(-3));
        }
        cacheString.addElementToCache("first",3);
        cacheString.addElementToCache("second",1);
        cacheString.addElementToCache("third",4);
        cacheString.addElementToCache("fourth",76);
        cacheString.isPresentElement("first");
        cacheString.isPresentIndex(24);
        cacheString.deleteElementFromCache("fourth");
        cacheString.getElementByIndex(1);
        cacheString.clearCache();

        Cache<Boolean> booleanCache = new Cache<>(3);
        booleanCache.addElementToCache(true,3);
        booleanCache.addElementToCache(false,1);
        booleanCache.addElementToCache(true,4);
        booleanCache.isPresentIndex(99);
        booleanCache.isPresentElement(false);
        booleanCache.deleteElementFromCache(true);
        booleanCache.getElementByIndex(78);
        booleanCache.getElementByIndex(1);
        booleanCache.clearCache();

        String[] stringArray = {"string1","string2","string3"};
        Storage<String> storage = new Storage<>(stringArray);
        storage.addElement("string4");
        storage.addElement("string5");
        storage.addElement("string6");
        storage.addElement("string7");
        storage.addElement("string8");
        storage.addElement("string9");
        storage.addElement("string10");
        storage.addElement("string11");

        storage.deleteLastElement();

        String elementLast = storage.getLastElement();
        System.out.println("storage.getLast() = " + elementLast);

        try {
            String elementByIndex = storage.getElementByIndex(345);
            System.out.println("storage.get(0) = " + elementByIndex);
        } catch (StorageIndexOutOfRange e) {
            log.error(e.getMessage());
        }
        storage.clearStorage();
    }
}
