import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        String input = get_input();
        while (!input.equals("bye")) {
            echo(input);
            input = get_input();
        }
        exit();
    }

    private static void greet() {
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

    private static void exit() {
        System.out.println("Bye bye!!!!");
    }

    private static String get_input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    private static void echo(String str) {
        System.out.println(str);
        System.out.println();
    }
}