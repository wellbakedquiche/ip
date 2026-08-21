import java.util.Scanner;

public class Duke {
    public void main(String[] args) {
        TaskList tasks = new TaskList(100);
        greet();
        String input = get_input();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(tasks);
            } else if (input.substring(0, 4).equals("mark") && input.charAt(4) == ' ') {
                tasks.mark(Integer.parseInt(input.substring(5)));
            } else if (input.substring(0, 6).equals("unmark") && input.charAt(6) == ' ') {
                tasks.unmark(Integer.parseInt(input.substring(7)));
            } else {
                    Task t = new Task(input);
                    tasks.addTask(t);
                }
                System.out.println();
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

    private class Task {
        private String name;
        private boolean done;
        private Task(String name) {
            this.name = name;
            this.done = false;
        }

        private boolean markDone() {
            if (done) {
                return false;
            }
            done = true;
            return true;
        }

        private boolean markUndone() {
            if (!done) {
                return false;
            }
            done = false;
            return true;
        }

        @Override
        public String toString() {
            if (done) {
                return "[X] " + name;
            }
            return "[ ] " + name;
        }
    }

    private class TaskList {
        private Task[] tasks;
        private int size;
        private int currSize;


        private TaskList(int size) {
            tasks = new Task[size];
            this.size = size;
            currSize = 0;
        }

        private int addTask(Task t) {
            if (currSize == size) {
                return -1;
            }
            tasks[currSize] = t;
            currSize++;
            System.out.println("Task added: " + t.toString());
            return currSize - 1;
        }

        private int mark(int i) {
            if (i >= currSize || i < 0) {
                System.out.println("Index out of bounds.");
                return -1;
            }
            if (tasks[i].markDone()) {
                System.out.println("Task marked as done:");
            } else {
                System.out.println("Task already marked as done:");
            }
            System.out.println(tasks[i].toString());
            return 1;
        }

        private int unmark(int i) {
            if (i >= currSize || i < 0) {
                System.out.println("Index out of bounds.");
                return -1;
            }
            if (tasks[i].markUndone()) {
                System.out.println("Task marked as undone:");
            } else {
                System.out.println("Task already marked as undone:");
            }
            System.out.println(tasks[i].toString());
            return 1;
        }

        @Override
        public String toString() {
            if (currSize == 0) {
                return "No tasks currently! :)";
            }
            String finalString = "Your current tasks are:";
            for (int i = 0; i < currSize; i++) {
                finalString += String.format("\n%d. %s", i + 1, tasks[i].toString());
            }
            return finalString;
        }
    }
}