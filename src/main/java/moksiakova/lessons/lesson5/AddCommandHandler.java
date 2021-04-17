package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class AddCommandHandler implements CommandHandlerInterface {

    private final String commandString = "add";

    @Override
    public boolean matches(String cmdString) {
        if (cmdString != null && cmdString.toLowerCase().startsWith(this.commandString)) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(Integer stringNumber, String fileName, String addText) {
        if (addText.isEmpty()) {
            System.out.println("Nothing to add.");
            return;
        }
        if (stringNumber != null) {
            this.addToFileInLine(stringNumber, fileName, addText);
        } else {
            this.addToEndFile(fileName, addText);
        }
    }

    private void addToEndFile(String fileName, String addText) {
        FileWork.fileWriteToEnd(fileName, addText);
    }

    private void addToFileInLine(Integer stringNumber, String fileName, String addText) {
        Long numLinesInFile = FileWork.getNumOfLinesInFile(fileName)+1;
        if (numLinesInFile <= stringNumber) {
            try (BufferedWriter fileToWrite = FileWork.fileOpenToWrite(fileName)) {
                while (numLinesInFile < stringNumber) {
                    fileToWrite.newLine();
                    numLinesInFile += 1;
                }
                fileToWrite.write(addText);
                fileToWrite.write(System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String line;
            Integer numOfLine = 1;
            if (!FileWork.checkExistFile(fileName)) {
                FileWork.fileWriteToEnd(fileName, addText);
                return;
            }
            try (BufferedReader fileToRead = FileWork.fileOpenToRead(fileName);
                     BufferedWriter tmpFile = FileWork.fileOpenToWrite("tmp.txt")) {
                    while ((line = fileToRead.readLine()) != null) {
                        if (numOfLine.equals(stringNumber)) {
                            tmpFile.write(addText);
                            tmpFile.write(System.lineSeparator());
                            numOfLine += 1;
                        }
                        tmpFile.write(line);
                        tmpFile.write(System.lineSeparator());
                        numOfLine += 1;
                    }
                FileWork.copy("tmp.txt", fileName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
