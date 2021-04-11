package moksiakova.lessons.lesson5;

public interface CommandHandlerInterface {
    boolean matches(String commandString);
    void handle(Integer stringNumber, String fileName, String addText);
}
