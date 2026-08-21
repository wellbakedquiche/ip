import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    public void main(String[] args) {
        TaskList tasks = new TaskList(100);
        greet();
        String input = get_input();
        while (!input.equals("bye")) {
            String[] readInput = readInput(input);
            if (readInput[0].equals("list")) {
                System.out.println(tasks);
            } else if (readInput[0].equals("mark")) {
                tasks.mark(Integer.parseInt(readInput[1]) - 1);
            } else if (readInput[0].equals("unmark")) {
                tasks.unmark(Integer.parseInt(readInput[1]) - 1);
            } else if (readInput[0].equals("todo")) {
                ToDo t = new ToDo(readInput[1]);
                tasks.addTask(t);
            } else if (readInput[0].equals("deadline")) {
                Deadline d = new Deadline(readInput[1], readInput[2]);
                tasks.addTask(d);
            } else if (readInput[0].equals("event")) {
                System.out.println(readInput[2] + readInput[3]);
                Event e = new Event(readInput[1], readInput[2], readInput[3]);
                tasks.addTask(e);
            } else if (readInput[0].equals("delete")) {
                tasks.removeTask(Integer.parseInt(readInput[1]) - 1);
            } else if (readInput[0].equals("error")) {
                System.out.println("Don't know what that means :(");
                System.out.println("Supported commands: ");
                System.out.println("list -> Shows all tasks");
                System.out.println("mark <index> -> Marks index as done");
                System.out.println("unmark <index> -> Marks index as not done");
                System.out.println("todo <name> -> Creates to-do with name");
                System.out.println("deadline <name> /by <due_date> -> Creates deadline with name and due date");
                System.out.println("event <name> /from <start> /to <end> -> Creates event with name, start and end date");
                System.out.println("bye -> exits chatbot");
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

    private static String[] readInput(String input) {
        if (input.equals("")) {
            return new String[] {""};
        }
        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];
        if (keyword.equals("list")) {
            return new String[] {"list"};
        } else if (keyword.equals("mark")) {
            if (splitInput.length == 2) {
                return new String[]{"mark", splitInput[1]};
            }
            System.out.println("mark needs to be followed by one argument, its index!");
            return new String[] {""};
        } else if (keyword.equals("unmark")) {
            if (splitInput.length == 2) {
                return new String[]{"unmark", splitInput[1]};
            }
            System.out.println("unmark needs to be followed by one argument, its index!");
            return new String[] {""};
        } else if (keyword.equals("todo")) {
            if (input.length() > 5) {
                return new String[]{"todo", input.substring(5)};
            }
            System.out.println("todo needs to be followed by one argument, its description!");
            return new String[] {""};
        } else if (keyword.equals("deadline")) {
            if (input.length() > 9) {
                String substrings = input.substring(9);
                String[] splitDates = substrings.split(Pattern.quote(" /by "));
                if (splitDates.length == 2) {
                    return new String[]{"deadline", splitDates[0], splitDates[1]};
                }
            }
            System.out.println("deadline has the format 'deadline <description> /by <duedate>'!");
            return new String[] {""};
        } else if (keyword.equals("event")) {
            if (input.length() > 6) {
                String substrings = input.substring(6);
                String[] splitDates = substrings.split(Pattern.quote(" /from ") + "|" + Pattern.quote(" /to "));
                if (splitDates.length == 3) {
                    return new String[]{"event", splitDates[0], splitDates[1], splitDates[2]};
                }
            }
            System.out.println("event has the format 'event <description> /from <start> /to <end>'!");
            return new String[] {""};
        } else if (keyword.equals("delete")) {
            if (splitInput.length == 2) {
                return new String[]{"delete", splitInput[1]};
            }
            System.out.println("delete needs to be followed by one argument, its index!");
            return new String[] {""};
        }
        return new String[] {"error"};
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

    private class ToDo extends Task {
        private ToDo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private class Deadline extends Task {
        private String date;
        private Deadline(String name, String date) {
            super(name);
            this.date = date;
        }

        @Override
        public String toString() {
            return String.format("[D]%s (due by: %s)", super.toString(), date);
        }
    }

    private class Event extends Task {
        private String start;
        private String end;
        private Event(String name, String start, String end) {
            super(name);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
        }
    }

    private class TaskList {
        private List<Task> tasks;
        private int size;
        private int currSize;


        private TaskList(int size) {
            tasks = new ArrayList<Task>(size);
            this.size = size;
            currSize = 0;
        }

        private int addTask(Task t) {
            if (currSize == size) {
                return -1;
            }
            tasks.add(t);
            currSize++;
            System.out.println("Task added: " + t.toString());
            return currSize - 1;
        }


        private int removeTask(int i) {
            if (i >= currSize || i < 0) {
                System.out.println("Index out of bounds");
                return -1;
            }
            System.out.println("Task removed: " + tasks.get(i).toString());
            tasks.remove(i);
            currSize--;
            return 1;
        }
        
        private int mark(int i) {
            if (i >= currSize || i < 0) {
                System.out.println("Index out of bounds.");
                return -1;
            }
            if (tasks.get(i).markDone()) {
                System.out.println("Task marked as done:");
            } else {
                System.out.println("Task already marked as done:");
            }
            System.out.println(tasks.get(i).toString());
            return 1;
        }

        private int unmark(int i) {
            if (i >= currSize || i < 0) {
                System.out.println("Index out of bounds.");
                return -1;
            }
            if (tasks.get(i).markUndone()) {
                System.out.println("Task marked as undone:");
            } else {
                System.out.println("Task already marked as undone:");
            }
            System.out.println(tasks.get(i).toString());
            return 1;
        }

        @Override
        public String toString() {
            if (currSize == 0) {
                return "No tasks currently! :)";
            }
            String finalString = "Your current tasks are:";
            for (int i = 0; i < currSize; i++) {
                finalString += String.format("\n%d. %s", i + 1, tasks.get(i).toString());
            }
            return finalString;
        }
    }
}