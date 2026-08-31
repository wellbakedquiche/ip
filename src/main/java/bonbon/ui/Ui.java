package bonbon.ui;

import java.util.Scanner;

public class Ui {
    public static void greet() {
        String banner =
                " ______                ______               \n" +
                        " | ___ \\               | ___ \\              \n" +
                        " | |_/ / ___  _ __     | |_/ / ___  _ __    \n" +
                        " | ___ \\/ _ \\| '_ \\    | ___ \\/ _ \\| '_ \\   \n" +
                        " | |_/ / (_) | | | |   | |_/ / (_) | | | |  \n" +
                        " \\____/ \\___/|_| |_|   \\____/ \\___/|_| |_|  \n";
        System.out.println(banner);
        System.out.println("Hi! I'm BonBon");
        System.out.println("What're we doing today?\n");
    }

    public static void exit() {
        System.out.println("Bye bye!!!!");
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static void printCommand() {
        System.out.println("Supported commands: ");
        System.out.println("list -> Shows all tasks");
        System.out.println("mark <index> -> Marks index as done");
        System.out.println("unmark <index> -> Marks index as not done");
        System.out.println("todo <name> -> Creates to-do with name");
        System.out.println("deadline <name> /by <due_date> -> Creates deadline with name and due date");
        System.out.println("event <name> /from <start> /to <end> -> Creates event with name, start and end date");
        System.out.println("bye -> exits chatbot");
    }
}