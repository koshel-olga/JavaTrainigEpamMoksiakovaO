package moksiakova.lessons.lesson5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FilterCommand filterCommand = new FilterCommand();
        String input = "";
        while (true) {
            System.out.print("Enter something (Exit to quite): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {break;}
            filterCommand.validateCommandString(input);
        }
    }
}
