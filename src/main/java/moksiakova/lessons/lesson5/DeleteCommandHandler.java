package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class DeleteCommandHandler implements CommandHandlerInterface {

    private final String commandString = "delete";

    @Override
    public boolean matches(String cmdString) {
        if (cmdString != null && cmdString.toLowerCase().startsWith(this.commandString)) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(Integer stringNumber, String fileName, String addText) {
        if (stringNumber == null) {
            stringNumber = FileWork.getNumOfLinesInFile(fileName).intValue();
        }
        String line;
        Integer numOfLine = 1;
        try (BufferedReader fileRead = FileWork.fileOpenToRead(fileName); BufferedWriter tmpFileDel = FileWork.fileOpenToWrite("tmpForDelete.txt")) {
            while ((line = fileRead.readLine()) != null) {
                if (!numOfLine.equals(stringNumber)) {
                    tmpFileDel.write(line);
                }
                numOfLine += 1;
            }
            FileWork.copy("tmpForDelete.txt", fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
