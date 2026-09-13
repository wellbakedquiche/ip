package bonbon.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of task with a due date.
 * The due date is represented in <code>datetime</code>.
 */
public class Deadline extends Task {
    private LocalDateTime date;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Creates a Deadline.
     *
     * @param name the name of the deadline.
     * @param date the date of the deadline, written in format 'yyyy-MM-dd HHmm'.
     */
    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    /**
     * Returns <code>String</code> representation of the deadline.
     *
     * @return <code>String</code> representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (DUE BY: %s)", super.toString(), date.format(OUTPUT_FORMATTER));
    }
}