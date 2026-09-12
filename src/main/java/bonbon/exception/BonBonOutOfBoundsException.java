package bonbon.exception;

public class BonBonOutOfBoundsException extends BonBonException {
    public BonBonOutOfBoundsException(int i) {
        super(i == 0
            ? "There are no tasks in the list right now!"
            : i == 1
            ? "There is only 1 task in the list right now!"
            : "There are only " + " tasks in the list right now!");
    }
}
