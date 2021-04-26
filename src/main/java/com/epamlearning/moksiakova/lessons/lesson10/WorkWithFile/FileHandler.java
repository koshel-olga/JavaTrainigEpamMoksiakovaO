package com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FileHandler {

    public BufferedWriter fileOpenToWrite(String fileName) throws IOException {
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

    public void fileWriteToEnd(String fileName, String text) {
        try (BufferedWriter writer = this.fileOpenToWrite(fileName)) {
            writer.write(text);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            log.error("Can not write to end of file {} : {}",fileName, e.getMessage());
        }
    }

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
}
