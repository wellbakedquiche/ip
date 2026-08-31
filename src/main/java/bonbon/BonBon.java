package bonbon;

import bonbon.parser.Parser;
import bonbon.ui.Ui;
import bonbon.storage.Storage;
import bonbon.tasklist.TaskList;

import java.nio.file.Path;

public class BonBon {
    TaskList tasks;

    public void main(String[] args) {
        run();
    }

    private void run() {
        tasks = new TaskList(100);
        Path filePath = Path.of("./src/main/java/data/bonbon.txt");
        Storage.loadFile(filePath, tasks);
        Ui.greet();
        String input = Ui.getInput();
        while (!input.equals("bye")) {
            String[] readInput = Parser.readInput(input);
            if (!readInput[0].equals("list") && !readInput[0].equals("error") && readInput[0].equals("find")) {
                Storage.writeFile(filePath, input, tasks);
            }
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
            } else if (readInput[0].equals("find")) {
                System.out.println(tasks.find(readInput[1]));
            } else if (readInput[0].equals("error")) {
                System.out.println("Don't know what that means!! :(");
                Ui.printCommand();
            }
            System.out.println();
            input = Ui.getInput();
        }
        Ui.exit();
    }
}