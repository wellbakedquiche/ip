public class Duke {
    public static void main(String[] args) {
        greet();
        exit();
    }

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
        System.out.println("What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}