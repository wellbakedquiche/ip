package bonbon.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
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