package bonbon.tasklist;

/**
 * Task represents a task. It represents the name of the task as a <code>String</code>,
 * and its completion status as a <code>boolean</code>.
 */
public class Task {
    private String name;
    private boolean done;

    /**
     * Creates a Task.
     *
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks task as done.
     *
     * @return true if successfully marked as done, and false if task is already marked as done.
     */
    public boolean markDone() {
        if (done) {
            return false;
        }
        done = true;
        return true;
    }

    /**
     * Marks task as undone.
     *
     * @return true if successfully marked as undone, and false if task is already marked as undone.
     */
    public boolean markUndone() {
        if (!done) {
            return false;
        }
        done = false;
        return true;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        }
        return "[ ] " + name;
    }
}