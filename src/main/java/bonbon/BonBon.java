package bonbon;

import bonbon.exception.BonBonException;
import bonbon.exception.BonBonUnknownCommandException;
import bonbon.parser.Parser;
import bonbon.ui.Ui;
import bonbon.storage.Storage;
import bonbon.tasklist.TaskList;

import java.nio.file.Path;

/**
 * BonBon implements a chatbot that manages user's task list.
 */
public class BonBon {

    static TaskList tasks;

    /**
     * Runs the main application loop
     */
    public void main(String[] args) {
        run();
    }

    private void run() {

        tasks = new TaskList(100);
        assert tasks != null : "TaskList should be successfully instantiated";

        Path filePath = Path.of("./src/main/java/data/bonbon.txt");
        assert filePath != null : "Storage file path must be successfully resolved";

        Storage.loadFile(filePath, tasks);

        Ui.greet();
        String input = Ui.getInput();

        while (!input.equals("bye")) {
            try {
                String[] readInput = Parser.readInput(input);
                handleInput(readInput);

                if (!readInput[0].equals("list") && !readInput[0].equals("error") && !readInput[0].equals("find")) {
                    Storage.writeFile(filePath, input, tasks);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
            input = Ui.getInput();
        }
        Ui.exit();
    }

    public void handleInput(String[] readInput) {
        String keyword = readInput[0];

        try {
            switch (keyword) {
                case "list":
                    System.out.println(tasks);
                    break;
                case "mark":
                    tasks.mark(Integer.parseInt(readInput[1]) - 1);
                    System.out.println("Task marked!");
                    break;
                case "unmark":
                    tasks.unmark(Integer.parseInt(readInput[1]) - 1);
                    System.out.println("Task unmarked!");
                    break;
                case "todo":
                    tasks.addToDo(readInput[1]);
                    System.out.println("ToDo added!");
                    break;
                case "deadline":
                    tasks.addDeadline(readInput[1], readInput[2]);
                    System.out.println("Deadline added!");
                    break;
                case "event":
                    tasks.addEvent(readInput[1], readInput[2], readInput[3], readInput[4]);
                    System.out.println("Event added!");
                    break;
                case "delete":
                    tasks.removeTask(Integer.parseInt(readInput[1]) - 1);
                    System.out.println("Task removed!");
                    break;
                case "find":
                    System.out.println(tasks.find(readInput[1]));
                    break;
                default:
                    throw new BonBonUnknownCommandException(keyword);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTasks() {
        tasks = new TaskList(100);
        Path filePath = Path.of("./src/main/java/data/bonbon.txt");
        Storage.loadFile(filePath, tasks);

    }

    public static String getResponse(String input) {
        try {
            Path filePath = Path.of("./src/main/java/data/bonbon.txt");
            String[] readInput = Parser.readInput(input);
            if (!readInput[0].equals("list") && !readInput[0].equals("error")) {
                Storage.writeFile(filePath, input, tasks);
            }
            if (readInput[0].equals("list")) {
                return tasks.toString();
            } else if (readInput[0].equals("mark")) {
                tasks.mark(Integer.parseInt(readInput[1]) - 1);
                return "Marked task!";
            } else if (readInput[0].equals("unmark")) {
                tasks.unmark(Integer.parseInt(readInput[1]) - 1);
                return "Unmark task!";
            } else if (readInput[0].equals("todo")) {
                tasks.addToDo(readInput[1]);
                return "Added todo!";
            } else if (readInput[0].equals("deadline")) {
                tasks.addDeadline(readInput[1], readInput[2]);
                return "Added deadline!";
            } else if (readInput[0].equals("event")) {
                tasks.addEvent(readInput[1], readInput[2], readInput[3], readInput[4]);
                return "Added event!";
            } else if (readInput[0].equals("delete")) {
                tasks.removeTask(Integer.parseInt(readInput[1]) - 1);
                return "Deleted task!";
            } else {
                return "Don't know what that means :(";
            }
        } catch (BonBonException e) {
            return e.getMessage();
        }
    }
}