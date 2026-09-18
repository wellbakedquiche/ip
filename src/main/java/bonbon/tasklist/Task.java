package bonbon.tasklist;

import java.time.format.DateTimeFormatter;

/**
 * Task represents a task. It represents the name of the task as a <code>String</code>,
 * and its completion status as a <code>boolean</code>.
 */
public class Task {

    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private String name;
    public boolean isDone;

    /**
     * Creates a Task.
     *
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     *
     * @return true if successfully marked as done, and false if task is already marked as done.
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        }
        isDone = true;
        return true;
    }

    /**
     * Marks task as undone.
     *
     * @return true if successfully marked as undone, and false if task is already marked as undone.
     */
    public boolean markUndone() {
        if (!isDone) {
            return false;
        }
        isDone = false;
        return true;
    }

    public String getName() {
        return name;
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        }
        return "[ ] " + name;
    }

    public boolean isDone() {
        return isDone;
    }
}