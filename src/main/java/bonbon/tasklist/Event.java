package bonbon.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task with a start and end date.
 * The start and end dates are represented in <code>datetime</code>.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private String place;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Creates an Event.
     *
     * @param name the name of the task.
     * @param start the date and time of the start, written in format 'yyyy-MM-dd HHmm'.
     * @param end the date and time of the end, written in format 'yyyy-MM-dd HHmm'.
     */
    public Event(String name, String start, String end, String place) {
        super(name);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
        this.place = place;
    }

    /**
     * Returns <code>String</code> representation of event.
     *
     * @return <code>String</code> representation of event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (FROM: %s TO: %s AT: %s)", super.toString(),
                start.format(OUTPUT_FORMATTER), end.format(OUTPUT_FORMATTER), place);
    }
}
