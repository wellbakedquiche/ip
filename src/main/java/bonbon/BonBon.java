package bonbon;

import bonbon.command.Command;
import bonbon.exception.BonBonInvalidInput;
import bonbon.exception.BonBonOutOfBoundsException;
import bonbon.exception.BonBonUnknownCommand;
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
                String[] parsedInput = Parser.readInput(input);

                assert parsedInput != null : "Parser output array should never be null";
                assert parsedInput.length >= 1 : "Parser output should contain at least the command keyword";

                String keyword = parsedInput[0];

                switch (keyword) {
                    case "list":
                        Ui.printTaskList(tasks);
                        break;
                    case "mark":
                        tasks.mark(Integer.parseInt(parsedInput[1]) - 1);
                        System.out.println("Task has been marked!");
                        break;
                    case "unmark":
                        tasks.unmark(Integer.parseInt(parsedInput[1]) - 1);
                        System.out.println("Task has been unmarked!");
                        break;
                    case "todo":
                        tasks.addToDo(parsedInput[1]);
                        Ui.printTaskAdd(tasks.getLastTask());
                        break;
                    case "deadline":
                        tasks.addDeadline(parsedInput[1], parsedInput[2]);
                        break;
                    case "event":
                        tasks.addEvent(parsedInput[1], parsedInput[2], parsedInput[3]);
                        break;
                    case "delete":
                        tasks.removeTask(Integer.parseInt(parsedInput[1]) - 1);
                        break;
                    case "find":
                        System.out.println(tasks.find(parsedInput[1]));
                        break;
                }

                // Save only after successful command execution
                if (!keyword.equals("list") && !keyword.equals("find")) {
                    Storage.writeFile(filePath, input, tasks);
                }

            } catch (BonBonUnknownCommand e) {
                System.out.println(e.getMessage());
                Ui.printCommands();
            } catch (BonBonInvalidInput e) {
                System.out.println(e.getMessage());

                String keyword = input.split(" ")[0];
                Ui.printSyntax(Command.valueOf(keyword.toUpperCase()));
            } catch (BonBonOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }

            input = Ui.getInput();
        }
        Ui.exit();
    }

    public void loadTasks() {
        tasks = new TaskList(100);
        Path filePath = Path.of("./src/main/java/data/bonbon.txt");
        Storage.loadFile(filePath, tasks);
    }

    public static String getResponse(String input) {
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
            tasks.addEvent(readInput[1], readInput[2], readInput[3]);
            return "Added event!";
        } else if (readInput[0].equals("find")) {
            return tasks.find(readInput[1]);
        } else if (readInput[0].equals("delete")) {
            tasks.removeTask(Integer.parseInt(readInput[1]) - 1);
            return "Deleted task!";
        } else {
            return "Don't know what that means :(";
        }
    }
}