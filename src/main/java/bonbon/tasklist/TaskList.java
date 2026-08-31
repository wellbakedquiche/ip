package bonbon.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
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

        @Override
        public String toString() {
            if (done) {
                return "[X] " + name;
            }
            return "[ ] " + name;
        }
    }

    private class ToDo extends Task {
        public ToDo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private class Deadline extends Task {
        private LocalDateTime date;
        private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

        public Deadline(String name, String date) {
            super(name);
            this.date = LocalDateTime.parse(date, INPUT_FORMATTER);
        }

        @Override
        public String toString() {
            return String.format("[D]%s (due by: %s)", super.toString(), date.format(OUTPUT_FORMATTER));
        }
    }

    public class Event extends Task {
        private LocalDateTime start;
        private LocalDateTime end;
        private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        public Event(String name, String start, String end) {
            super(name);
            this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
            this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from: %s to: %s)", super.toString(),
                    start.format(OUTPUT_FORMATTER), end.format(OUTPUT_FORMATTER));
        }
    }

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