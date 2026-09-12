package bonbon.command;

public enum Command {
    LIST("list", "Shows all tasks"),
    MARK("mark <index>", "Marks index as done"),
    UNMARK("unmark <index>", "Marks index as not done"),
    TODO("todo <name>", "Creates to-do with name"),
    DEADLINE("deadline <name> /by <due_date>", "Creates deadline with name and due date"),
    EVENT("event <name> /from <start> /to <end>", "Creates event with name, start and end date"),
    BYE("bye", "Exits chatbot"),
    FIND("find <substring>", "Returns all the tasks with given description"),
    DELETE("delete <index>", "Deletes task at given index");

    private final String syntax;
    private final String description;

    Command(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    public String getSyntax() { return syntax; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return syntax + " -> " + description;
    }
}
