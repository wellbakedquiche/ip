package bonbon.tasklist;

/**
 * ToDo is a type of task
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo.
     *
     * @param name the name of the todo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns <code>String</code> representation of todo.
     *
     * @return <code>String</code> representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return String.format("T | %s", super.toFileFormat());
    }
}
