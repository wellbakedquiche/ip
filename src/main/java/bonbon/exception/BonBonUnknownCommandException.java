package bonbon.exception;

public class BonBonUnknownCommandException extends BonBonException {
    public BonBonUnknownCommandException(String c) {
        super(String.format("'%s' is not a valid command!", c));
    }
}
