package bonbon.exception;

/**
 * Thrown when attempting to add a task to a full TaskList.
 */
public class BonBonTaskListFull extends BonBonException {

    /**
     * Constructs a BonBonFullListException with a default or custom max capacity message.
     *
     * @param maxCapacity The maximum number of tasks allowed in the list.
     */
    public BonBonTaskListFull(int maxCapacity) {
        super("Your task list is full! You cannot add more than " + maxCapacity + " tasks.");
    }
}