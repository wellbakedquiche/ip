package bonbon.tasklist;

import bonbon.exception.BonBonException;
import bonbon.exception.BonBonOutOfBoundsException;

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
     * Adds
     *
     * @param t
     * @return the index of the added task;
     * @throws BonBonException when list is full.
     */
    public int addTask(Task t) throws BonBonException {
        if (currSize == size) {
            throw new BonBonException("Task list is full!");
        }
        tasks.add(t);
        currSize++;
        return currSize - 1;
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param s the details or name of the task to add.
     * @return the zero-based index of the newly added task, or -1 if the list is full.
     */
    public int addToDo(String s) throws BonBonException{
        ToDo td = new ToDo(s);
        return addTask(td);
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param desc the details or name of the task to add.
     * @param date the due date in format 'yyyy-MM-dd HHmm'.
     * @return the zero-based index of the newly added task, or -1 if the list is full.
     */
    public int addDeadline(String desc, String date) throws BonBonException{
        Deadline deadline = new Deadline(desc, date);
        return addTask(deadline);
    }


     /**
     * Adds a new Event task to the task list.
     *
     * @param desc the details or name of the task to add.
     * @param start the start date in format 'yyyy-MM-dd HHmm'.
     * @param end the end date in format 'yyyy-MM-dd HHmm'
     * @return the zero-based index of the newly added task
     * @throws BonBonException
     */
    public int addEvent(String desc, String start, String end) throws BonBonException{
        Event event = new Event(desc, start, end);
        return addTask(event);
    }

    /**
     * Removes task from the task list, and displays the task removed.
     *
     * @param i the zero-based index of the task to be removed.
     * @return 1 if successfully deleted, and -1 if the index is out of range.
     */
    public int removeTask(int i) throws BonBonOutOfBoundsException {
        if (i >= currSize || i < 0) {
            throw new BonBonOutOfBoundsException(currSize);
        }
        tasks.remove(i);
        currSize--;
        return 1;
    }

    /**
     * Marks task as done in the task list.
     *
     * @param i
     */
    public void mark(int i) throws BonBonOutOfBoundsException {
        if (i >= currSize || i < 0) {
            throw new BonBonOutOfBoundsException(currSize);
        }
        tasks.get(i).markDone();
    }

    /**
     * Marks task as undone in the task list.
     *
     * @param i
     */
    public void unmark(int i) throws BonBonOutOfBoundsException {
        if (i >= currSize || i < 0) {
            throw new BonBonOutOfBoundsException(currSize);
        }
        tasks.get(i).markUndone();
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