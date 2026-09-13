package bonbon.storage;

import bonbon.BonBon;
import bonbon.exception.BonBonException;
import bonbon.parser.Parser;
import bonbon.tasklist.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.List;

/**
 * Storage writes and loads information to the file.
 */
public class Storage {

    /**
     * Writes input into the file path given.
     *
     * @param filePath the file path to write into.
     * @param input the input to be written into filepath.
     * @param tasks the related task list.
     */
    public static void writeFile(Path filePath, String input, TaskList tasks) {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            Files.writeString(filePath, input + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads file path information into tasks.
     *
     * @param filePath the file path to read from.
     * @param tasks the task list to write into.
     */
    public static void loadFile(Path filePath, TaskList tasks) {
        if (!Files.exists(filePath)) {
            System.out.println("No saved data found!");
            return;
        }
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String input : lines) {
                String[] readInput = Parser.readInput(input);
                if (readInput[0].equals("mark")) {
                    tasks.mark(Integer.parseInt(readInput[1]) - 1);
                } else if (readInput[0].equals("unmark")) {
                    tasks.unmark(Integer.parseInt(readInput[1]) - 1);
                } else if (readInput[0].equals("todo")) {
                    tasks.addToDo(readInput[1]);
                } else if (readInput[0].equals("deadline")) {
                    tasks.addDeadline(readInput[1], readInput[2]);
                } else if (readInput[0].equals("event")) {
                    tasks.addEvent(readInput[1], readInput[2], readInput[3], readInput[4]);
                } else if (readInput[0].equals("delete")) {
                    tasks.removeTask(Integer.parseInt(readInput[1]) - 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}