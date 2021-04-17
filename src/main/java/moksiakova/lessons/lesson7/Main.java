package moksiakova.lessons.lesson7;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class Main {
    public static void main(String[] args) {

        CustomClassLoader classLoader = new CustomClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass("TestClass");
            Object obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println(obj);
        } catch (ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            log.error(e.getMessage());
        }
        try {
            generateOutOfMemoryError();
        } catch (OutOfMemoryError error) {
            log.error(error.getMessage());
        }
        try {
            generateStackOverflowError();
        } catch (StackOverflowError error) {
            log.error("StackOverflowError");
        }
    }

    public static void generateOutOfMemoryError() {
        Integer[] badArrayForHeapSpace50MB = new Integer[100000*100000];
    }

    public static void generateStackOverflowError() {
        Human badHuman = new Human();
    }
}
