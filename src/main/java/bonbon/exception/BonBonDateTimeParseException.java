package bonbon.exception;

public class BonBonDateTimeParseException extends BonBonException {
    /**
     * Creates a BonBonDateTimeParseException.
     */
    public BonBonDateTimeParseException() {
        super("Date is not in the correct format!\nCorrect format is yyyy-MM-dd HHmm.");
    }
}
