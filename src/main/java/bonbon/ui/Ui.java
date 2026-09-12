package bonbon.ui;

import bonbon.tasklist.Task;
import bonbon.tasklist.TaskList;
import bonbon.command.Command;

import java.util.Scanner;

/**
 * Manages user interface interactions, including reading inputs and formatting console outputs.
 */
public class Ui {
    /**
     * Displays standard message banner to users.
     */
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

    /**
     * Displays standard exit message to users.
     */
    public static void exit() {
        System.out.println("Bye bye!!!!");
    }

    /**
     * Reads the next line of text entered by the user in the console.
     *
     * @return the input line as a string.
     */
    public static String getInput() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static void printTaskList(TaskList t) {
        System.out.println(t.toString());
    }

    /**
     * Displays list of commands and formats to users.
     */
    public static void printCommands() {
        System.out.println(Command.toStringCommands());
    }

    public static void printSyntax(Command cmd) {
        String s = String.format("Correct syntax for %s is:\n%s", cmd.getKeyword(), cmd.getSyntax());
        System.out.println(s);
    }

    public static void printTaskAdd(Task t) {
        String s = String.format("Task '%s' has been added!", t.toString());
        System.out.println(s);
    }
}