package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class DeleteCommand {

    Integer stringNumber;
    String fileName;

    public DeleteCommand(Integer stringNumber, String fileName) {
        this.stringNumber = stringNumber;
        this.fileName = fileName;
    }

    public void deleteFromFile() {
        if (this.stringNumber == null) {
            this.stringNumber = FileWork.getNumOfLinesInFile(this.fileName).intValue();
        }
        try {
            String line;
            Integer numOfLine = 1;
            BufferedReader fileToRead = FileWork.fileOpenToRead(this.fileName);
            BufferedWriter tmpFile = FileWork.fileOpenToWrite("tmp.txt");
            while ((line = fileToRead.readLine()) != null) {
                if (!numOfLine.equals(this.stringNumber)) {
                    tmpFile.write(line);
                }
                numOfLine +=1;
            }
            fileToRead.close();
            tmpFile.close();
            FileWork.copy("tmp.txt", this.fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
