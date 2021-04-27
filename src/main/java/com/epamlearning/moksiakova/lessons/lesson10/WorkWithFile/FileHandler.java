package com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;


/**
 * Class for work with files.
 */
@Slf4j
public class FileHandler {

    /**
     * Open file in pathToFile to write.
     * @param pathToFile path to file.
     * @return {@link BufferedWriter}
     * @throws IOException
     */
    public BufferedWriter fileOpenToWrite(String pathToFile) throws IOException {
        BufferedWriter writer;
        if (!Files.exists(Path.of(pathToFile))) {
            writer = new BufferedWriter(
                    new FileWriter(pathToFile));
        } else {
            writer = new BufferedWriter(
                    new FileWriter(pathToFile, true));
        }
        return writer;
    }

    /**
     * Add string to end file.
     * @param pathToFile path to file.
     * @param text string for add to file.
     */
    public void fileWriteToEnd(String pathToFile, String text) {
        try (BufferedWriter writer = this.fileOpenToWrite(pathToFile)) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            log.error("Can not write to end of file {} : {}",pathToFile, e.getMessage());
        }
    }

    /**
     * Read all file in collection.
     * @param pathToFile path to file.
     * @return {@link Optional<List>}
     */
    public Optional<List<String>> readAllFile(String pathToFile) {
        Path path = Paths.get(pathToFile);
        try {
            Optional<List<String>> readedLines = Optional.of(Files.readAllLines(path));
            return readedLines;
        } catch (IOException e) {
            log.error("Can not read the file {} : {}",pathToFile, e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Clear file.
     * @param pathToFile path to file.
     */
    public void clearFile(String pathToFile) {
        try {
            Files.newBufferedWriter(Path.of(pathToFile), new StandardOpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});
        } catch (IOException e) {
            log.error("Can not clear the file {} : {}",pathToFile, e.getMessage());
        }
    }
}
