package bonbon.tasklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of tasks and handles task-related operations such as adding and deleting entries.
 */
public class TaskList {
    private List<Task> tasks;
    private int size;
    private int currSize;

    /**
     * Initializes the task list.
     *
     * @param size the maximum size of the task list.
     */
    public TaskList(int size) {
        tasks = new ArrayList<Task>(size);
        this.size = size;
        currSize = 0;
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param s the details or name of the task to add.
     * @return the zero-based index of the newly added task, or -1 if the list is full.
     */
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

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param desc the details or name of the task to add.
     * @param date the due date in format 'yyyy-MM-dd HHmm'.
     * @return the zero-based index of the newly added task, or -1 if the list is full.
     */
    public int addDeadline(String desc, String date) {
        if (currSize == size) {
            return -1;
        }
        Deadline deadline = new Deadline(desc, date);
        tasks.add(deadline);
        currSize++;
        System.out.println("Task added: " + deadline);
        return currSize - 1;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param desc the details or name of the task to add.
     * @param start the start date in format 'yyyy-MM-dd HHmm'.
     * @param end the end date in format 'yyyy-MM-dd HHmm'
     * @return the zero-based index of the newly added task, or -1 if the list is full.
     */
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

    /**
     * Removes task from the task list, and displays the task removed.
     *
     * @param i the zero-based index of the task to be removed.
     * @return 1 if successfully deleted, and -1 if the index is out of range.
     */
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

    /**
     * Marks task as done in the task list.
     *
     * @param i the zero-based index of the task to be marked as done
     * @return 1 if successfully deleted, and -1 if the index is out of range.
     */
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

    /**
     * Marks task as undone in the task list.
     *
     * @param i the zero-based index of the task to be marked as undone
     * @return 1 if successfully deleted, and -1 if the index is out of range.
     */
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

    /**
     * Returns formatted string displaying all tasks in task list.
     *
     * @return the formatted string displaying all tasks in task list.
     */
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