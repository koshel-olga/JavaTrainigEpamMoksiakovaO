package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.IOException;

/** Class for print command. */
public class PrintCommand {
    Integer stringNumber;
    String fileName;

    public PrintCommand(Integer stringNumber, String fileName) {
        this.stringNumber = stringNumber;
        this.fileName = fileName;
    }

    public void printFromFile() {
        String line;
        try {
            int i = 1;
            BufferedReader reader = FileWork.fileOpenToRead(this.fileName);
            while ((line = reader.readLine()) != null) {
                if (this.stringNumber != null && this.stringNumber == i) {
                    System.out.println(line);
                    break;
                } else {
                    System.out.println(line);
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
