package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCommand {

    Integer stringNumber;
    String fileName;
    String addText;
    FileWork fileWork;

    public AddCommand(Integer stringNumber, String fileName, String addText) {
        this.stringNumber = stringNumber;
        this.fileName = fileName;
        this.addText = addText;
        this.fileWork = new FileWork();
    }

    public void addToFile() {
        if (this.addText.isEmpty()) {
            System.out.println("Nothing to add.");
            return;
        }
        if (this.stringNumber != null) {
            this.addToFileInLine();
        } else {
            try {
                this.fileWork.fileWriteToEnd(this.fileName, this.addText);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addToFileInLine() {
        Long numLinesInFile = this.fileWork.getNumOfLinesInFile(this.fileName);
        if (numLinesInFile <= this.stringNumber) {
            try {
                BufferedWriter fileToWrite = this.fileWork.fileOpenToWrite(this.fileName);
                while (numLinesInFile < this.stringNumber) {
                    fileToWrite.newLine();
                    numLinesInFile += 1;
                }
                fileToWrite.write(this.addText);
                fileToWrite.write(System.lineSeparator());
                fileToWrite.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                String line;
                Integer numOfLine = 1;
                if (!this.fileWork.checkExistFile(this.fileName)) {
                    this.fileWork.fileWriteToEnd(this.fileName, this.addText);
                    return;
                }
                BufferedReader fileToRead = this.fileWork.fileOpenToRead(this.fileName);
                BufferedWriter tmpFile = this.fileWork.fileOpenToWrite("tmp.txt");
                try {
                    while ((line = fileToRead.readLine()) != null) {
                        if (numOfLine.equals(this.stringNumber)) {
                            tmpFile.write(this.addText);
                            tmpFile.write(System.lineSeparator());
                            numOfLine += 1;
                        }
                        tmpFile.write(line);
                        numOfLine += 1;
                    }
                } finally {
                    fileToRead.close();
                    tmpFile.close();
                }
                this.fileWork.copy("tmp.txt", this.fileName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
