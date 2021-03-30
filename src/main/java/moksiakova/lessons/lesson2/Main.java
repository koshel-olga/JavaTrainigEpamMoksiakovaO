package main.java.moksiakova.lessons.lesson2;

public class Main {
    public static void main(String[] args) throws Exception {
        Cache<String> cacheString = new Cache<>(3);

        cacheString.add("first",3);
        cacheString.add("second",1);
        cacheString.add("third",4);
        cacheString.add("fourth",76);
        cacheString.isPresent("first");
        cacheString.isPresent(24);
        cacheString.delete("fourth");
        cacheString.get(1);
        cacheString.clear();

        Cache<Boolean> booleanCache = new Cache<>(3);
        booleanCache.add(true,3);
        booleanCache.add(false,1);
        booleanCache.add(true,4);
        booleanCache.isPresent(99);
        booleanCache.isPresent(false);
        booleanCache.delete(true);
        booleanCache.get(78);
        booleanCache.get(1);
        booleanCache.clear();

        Storage<String> storageWithDefaultConstructor = new Storage<>();
        String[] stringArray = {"string1","string2","string3"};
        Storage<String> storage = new Storage<>(stringArray);
        storage.add("string4");
        storage.add("string5");
        storage.add("string6");
        storage.add("string7");
        storage.add("string8");
        storage.add("string9");
        storage.add("string10");
        storage.add("string11");

        storage.delete();

        String elementLast = storage.getLast();
        System.out.println("storage.getLast() = " + elementLast.toString());

        String elementByIndex = storage.get(0);
        System.out.println("storage.get(0) = " + elementByIndex.toString());

        storage.clear();

        System.out.println("String to set breakpoint");
    }
}
