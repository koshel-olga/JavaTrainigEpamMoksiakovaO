package moksiakova.lessons.lesson5;

import java.io.BufferedReader;
import java.io.IOException;

/** Class for print command. */
public class PrintCommandHandler implements CommandHandlerInterface {

    private final String commandString = "print";

    @Override
    public boolean matches(String cmdString) {
        if (cmdString != null && cmdString.toLowerCase().startsWith(this.commandString)) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(Integer stringNumber, String fileName, String addText) {
        String line;
        int i = 1;
        try (BufferedReader reader = FileWork.fileOpenToRead(fileName)) {
            while ((line = reader.readLine()) != null) {
                if (stringNumber != null) {
                    if (stringNumber == i) {
                        System.out.println(line);
                        break;
                    }
                } else {
                    System.out.println(line);
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
