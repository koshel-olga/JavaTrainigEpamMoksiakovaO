package moksiakova.lessons.lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterCommand {
    List<CommandHandlerInterface> commandHandlerList;

    public FilterCommand() {
        commandHandlerList = new ArrayList<>();
        commandHandlerList.add(new AddCommandHandler());
        commandHandlerList.add(new DeleteCommandHandler());
        commandHandlerList.add(new PrintCommandHandler());
    }

    /**Check valid command from console.
     * @param inputStringCommand command from console. */
    void validateCommandString(String inputStringCommand) {
        String regex = "^(?<command>add|delete|print)\\s(?<stringNumber>[0-9]*)\\s*(?<fileName>[a-zA-Z0-9^\\.]+\\.[a-z]+)(?<addText>.*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputStringCommand);
        if (matcher.find()) {
            String command = matcher.group("command");
            Integer stringNumber = null;
            if (!matcher.group("stringNumber").isEmpty()) {
                stringNumber = Integer.valueOf(matcher.group("stringNumber"));
            }
            String fileName = matcher.group("fileName");
            String addText = matcher.group("addText").trim();

            for (CommandHandlerInterface commandHandler: this.commandHandlerList ){
                if (commandHandler.matches(command)){
                    commandHandler.handle(stringNumber, fileName, addText);
                    break;
                }
            }
        }
        else {
            System.out.println("Format command is illegal. Try again.");
        }
    }
}
