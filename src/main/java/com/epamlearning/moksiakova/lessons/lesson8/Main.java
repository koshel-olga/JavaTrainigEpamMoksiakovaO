package com.epamlearning.moksiakova.lessons.lesson8;

import com.epamlearning.moksiakova.lessons.lesson8.exception.ElementNotFoundInCacheException;
import com.epamlearning.moksiakova.lessons.lesson8.exception.IllegalCacheArgumentException;
import com.epamlearning.moksiakova.lessons.lesson8.exception.StorageIndexOutOfRange;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Cache<String> cacheString;
        try {
            cacheString = new Cache<>(-3);
        } catch (IllegalCacheArgumentException exception) {
            cacheString = new Cache<>(Math.abs(-3));
        }
        cacheString.addElementToCache("first",3);
        cacheString.addElementToCache("fourth",76);
        cacheString.isPresentElement("first");
        cacheString.isPresentIndex(24);
        cacheString.deleteElementFromCache("fourth");
        cacheString.getElementByIndex(3);
        cacheString.clearCache();

        Cache<Boolean> booleanCache = new Cache<>(3);
        booleanCache.addElementToCache(true,3);
        booleanCache.addElementToCache(false,1);
        booleanCache.addElementToCache(true,4);
        booleanCache.isPresentIndex(99);
        booleanCache.isPresentElement(false);
        booleanCache.deleteElementFromCache(true);
        try {
            booleanCache.getElementByIndex(78);
        } catch (ElementNotFoundInCacheException ignored) {}
        booleanCache.getElementByIndex(1);
        booleanCache.clearCache();

        String[] stringArray = {"string1","string2","string3"};
        Storage<String> storage = new Storage<>(stringArray);
        storage.addElement("string4");
        storage.deleteLastElement();
        String elementLast = storage.getLastElement();
        try {
            String elementByIndex = storage.getElementByIndex(345);
        } catch (StorageIndexOutOfRange e) {
            log.error(e.getMessage());
        }
        storage.clearStorage();
    }
}
