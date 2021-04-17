package moksiakova.lessons.lesson7;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class CustomClassLoader extends ClassLoader {

    private final String pathToLoadClass = "/home/olga/myClasses/out/production/myClasses";

    protected Class<?> loadClass(String className, boolean resolve)
            throws ClassNotFoundException
    {
        Class<?> result = findClass(className);
        if (resolve)
            resolveClass(result);
        return result;
    }

    protected Class<?> findClass(String className)
            throws ClassNotFoundException
    {
        Class<?> result;

        File f = findFile(className,".class");
        if (f == null) {
            try {
                return findSystemClass(className);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage());
                return null;
            }
        }
        try {
            byte[] classBytes= loadFileAsBytes(f);
            result = defineClass(className, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            String exceptionMessage = String.format("Cannot load class %s : %s", className, e.getMessage());
            log.error(exceptionMessage);
            throw new ClassNotFoundException(exceptionMessage);
        } catch (ClassFormatError e) {
            String exceptionMessage = String.format(
                    "Format of class file incorrect for class %s : %s"
                    , className, e.getMessage());
            log.error(exceptionMessage);
            throw new ClassNotFoundException(exceptionMessage);
        }
        return result;
    }

    private File findFile(String className, String extension)
    {
        String fileForSearch = String.format("%s/%s%s",pathToLoadClass,className, extension);
        File fileFound = new File(fileForSearch);
        if (fileFound.exists()) {
            return fileFound;
        }
        return null;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[ (int) file.length() ];

        try (FileInputStream f = new FileInputStream(file)) {
            f.read(result,0,result.length);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
