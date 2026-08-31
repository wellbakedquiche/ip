package bonbon.storage;

import bonbon.parser.Parser;
import bonbon.tasklist.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
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

    public static void loadFile(Path filePath, TaskList tasks) {
        if (!Files.exists(filePath)) {
            System.out.println("No saved data found!");
            return;
        }
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String input : lines) {
                String[] readInput = Parser.readInput(input);
                if (readInput[0].equals("list")) {
                    System.out.println(tasks);
                } else if (readInput[0].equals("mark")) {
                    tasks.mark(Integer.parseInt(readInput[1]) - 1);
                } else if (readInput[0].equals("unmark")) {
                    tasks.unmark(Integer.parseInt(readInput[1]) - 1);
                } else if (readInput[0].equals("todo")) {
                    tasks.addToDo(readInput[1]);
                } else if (readInput[0].equals("deadline")) {
                    tasks.addDeadline(readInput[1], readInput[2]);
                } else if (readInput[0].equals("event")) {
                    tasks.addEvent(readInput[1], readInput[2], readInput[3]);
                } else if (readInput[0].equals("delete")) {
                    tasks.removeTask(Integer.parseInt(readInput[1]) - 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}