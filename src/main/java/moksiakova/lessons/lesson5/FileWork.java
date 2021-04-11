package moksiakova.lessons.lesson5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileWork {

    public static boolean checkExistFile(String fileName) {
        return Files.exists(Path.of(fileName));
    }

    public static void fileWriteToEnd(String fileName, String text) throws IOException {
        BufferedWriter writer = FileWork.fileOpenToWrite(fileName);
        writer.write(text);
        writer.write(System.lineSeparator());
        writer.close();
    }

    public static BufferedWriter fileOpenToWrite(String fileName) throws IOException {
        BufferedWriter writer;
        if (!Files.exists(Path.of(fileName))) {
            writer = new BufferedWriter(
                    new FileWriter(fileName));
        } else {
            writer = new BufferedWriter(
                    new FileWriter(fileName, true));
        }
        return writer;
    }

    public static BufferedReader fileOpenToRead(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(fileName)
                );
        return reader;
    }

    public static Long getNumOfLinesInFile(String fileName) {
        Long numOfLineInFile = 1L;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)
                )
        )) {
            try {
                Stream<String> linesArray = reader.lines();
                numOfLineInFile = linesArray.count();
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return numOfLineInFile;
    }

    public static void copy(String fromFileName, String toFileName) {
        try {
            Files.copy(Path.of(fromFileName), Path.of(toFileName), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Path.of(fromFileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
