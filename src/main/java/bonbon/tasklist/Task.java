package bonbon.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean done;
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean markDone() {
        if (done) {
            return false;
        }
        done = true;
        return true;
    }

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