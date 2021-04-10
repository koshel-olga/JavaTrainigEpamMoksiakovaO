package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class DeleteCommand {

    Integer stringNumber;
    String fileName;
    FileWork fileWork;

    public DeleteCommand(Integer stringNumber, String fileName) {
        this.stringNumber = stringNumber;
        this.fileName = fileName;
        this.fileWork = new FileWork();
    }

    public void deleteFromFile() {
        if (this.stringNumber == null) {
            this.stringNumber = this.fileWork.getNumOfLinesInFile(this.fileName).intValue();
        }
        try {
            String line;
            Integer numOfLine = 1;
            BufferedReader fileRead = this.fileWork.fileOpenToRead(this.fileName);
            BufferedWriter tmpFileDel = this.fileWork.fileOpenToWrite("tmpForDelete.txt");
            while ((line = fileRead.readLine()) != null) {
                if (!numOfLine.equals(this.stringNumber)) {
                    tmpFileDel.write(line);
                }
                numOfLine +=1;
            }
            fileRead.close();
            tmpFileDel.close();
            this.fileWork.copy("tmpForDelete.txt", this.fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
