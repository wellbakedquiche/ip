package bonbon.exception;

/**
 * Thrown when a valid command is followed by invalid inputs.
 */
public class BonBonInvalidInput extends BonBonException{

    private String command;

    /**
     * Constructs a BonBonInvalidInput with a custom message.
     *
     * @param command The valid command
     */
    public BonBonInvalidInput(String command) {
        super(String.format("%s is in incorrect format!", command));
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
