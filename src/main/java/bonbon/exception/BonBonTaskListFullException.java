package bonbon.exception;

public class BonBonTaskListFullException extends BonBonException{
    public BonBonTaskListFullException (int i) {
        super(String.format("There's a max limit of %d tasks in the list! :(", i));
    }
}
