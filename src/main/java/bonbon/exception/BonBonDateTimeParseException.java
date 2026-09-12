package bonbon.exception;

/**
 * Exception thrown when a date/time string provided by the user
 * cannot be parsed into a valid date/time object.
 */
public class BonBonDateTimeParseException extends Exception {

    private final String invalidInput;

    /**
     * Constructs a BonBonDateTimeParseException with a default user-friendly message.
     *
     * @param invalidInput the raw input string that failed to parse.
     */
    public BonBonDateTimeParseException(String invalidInput) {
        super(String.format("Invalid date/time format: '%s'. Expected format: 'yyyy-MM-dd HHmm' (e.g., 2026-09-30 1800).", invalidInput));
        this.invalidInput = invalidInput;
    }

    /**
     * Returns the raw input string that triggered the parsing failure.
     *
     * @return invalid input string.
     */
    public String getInvalidInput() {
        return invalidInput;
    }
}