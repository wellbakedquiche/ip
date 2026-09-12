package bonbon.exception;

/**
 * Thrown when unknown command is parsed.
 */
public class BonBonUnknownCommand extends BonBonException{

    /**
     * Constructs a BonBonUnkownCommand with a default message.
     *
     * @param command the unkown command parsed.
     */
    public BonBonUnknownCommand(String command) {
        super(String.format("'%s' is not a command!", command));
    }
}
