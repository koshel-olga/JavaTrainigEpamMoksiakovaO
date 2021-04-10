package moksiakova.lessons.lesson5;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class FilterCommand {
    /** Command from console. */
    String command;

    Integer stringNumber;

    String fileName;

    String addText;


    /**Check valid command from console.
     * @param inputStringCommand command from console. */
    void validateCommandString(String inputStringCommand) {
        String regex = "^(?<command>add|delete|print)\\s(?<stringNumber>[0-9]*)\\s*(?<fileName>[a-zA-Z0-9^\\.]+\\.[a-z]+)(?<addText>.*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputStringCommand);
        if (matcher.find()) {
            this.command = matcher.group("command");
            if (!matcher.group("stringNumber").isEmpty()) {
                this.stringNumber = Integer.valueOf(matcher.group("stringNumber"));
            } else {
                this.stringNumber = null;
            }
            this.fileName = matcher.group("fileName");
            this.addText = matcher.group("addText");

            switch (this.command) {
                case("add"):
                    this.addCommand();
                case("delete"):
                    this.deleteCommand();
                case("print"):
                    this.printCommand();
            }
        }
        else {
            System.out.println("Format command is illegal. Try again.");
        }
    }

    public void addCommand() {
        AddCommand addCommand = new AddCommand(this.stringNumber, this.fileName, this.addText);
        addCommand.addToFile();
    }

    public void deleteCommand() {
        DeleteCommand deleteCommand = new DeleteCommand(this.stringNumber, this.fileName);
        deleteCommand.deleteFromFile();
    }

    public void printCommand() {
        PrintCommand printCommand = new PrintCommand(this.stringNumber, this.fileName);
        printCommand.printFromFile();
    }
}
