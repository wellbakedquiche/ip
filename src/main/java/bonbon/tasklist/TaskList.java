package bonbon.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private int size;
    private int currSize;

    public TaskList(int size) {
        tasks = new ArrayList<Task>(size);
        this.size = size;
        currSize = 0;
    }

    public int addToDo(String s) {
        if (currSize == size) {
            return -1;
        }
        ToDo td = new ToDo(s);
        tasks.add(td);
        currSize++;
        System.out.println("Task added: " + s);
        return currSize - 1;
    }

    public int addDeadline(String desc, String d) {
        if (currSize == size) {
            return -1;
        }
        Deadline deadline = new Deadline(desc, d);
        tasks.add(deadline);
        currSize++;
        System.out.println("Task added: " + deadline);
        return currSize - 1;
    }

    public int addEvent(String desc, String start, String end) {
        if (currSize == size) {
            return -1;
        }
        Event event = new Event(desc, start, end);
        tasks.add(event);
        currSize++;
        System.out.println("Task added: " + event);
        return currSize - 1;
    }

    public int removeTask(int i) {
        if (i >= currSize || i < 0) {
            System.out.println("Index out of bounds");
            return -1;
        }
        System.out.println("Task removed: " + tasks.get(i).toString());
        tasks.remove(i);
        currSize--;
        return 1;
    }

    public int mark(int i) {
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

    public int unmark(int i) {
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

    public String find(String s) {
        boolean foundTask = false;
        String finalString = "Matching tasks found: ";
        for (int i = 0; i < currSize; i++) {
            Task task = tasks.get(i);
            if (task.getName().contains(s)) {
                finalString += String.format("\n%d. %s", i + 1, tasks.get(i).toString());
                foundTask = true;
            }
        }
        if (foundTask) {
            return finalString;
        }
        return "No tasks found!";
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