package bonbon.command;

public enum Command {
    LIST("list","list", "Shows all tasks"),
    MARK("mark","mark <int>", "Marks index as done"),
    UNMARK("unmark","unmark <int>", "Marks index as not done"),
    TODO("todo","todo <string>", "Creates to-do with name"),
    DEADLINE("deadline","deadline <string> /by <due_date>", "Creates deadline with name and due date"),
    EVENT("event","event <string> /from <start> /to <end>", "Creates event with name, start and end date"),
    FIND("find", "find <string>", "Returns all tasks with substring in description"),
    BYE("bye", "bye", "Exits chatbot"),
    DELETE("delete", "delete <int>", "Deletes task");

    private final String keyword;
    private final String syntax;
    private final String description;

    Command(String keyword, String syntax, String description) {
        this.keyword = keyword;
        this.syntax = syntax;
        this.description = description;
    }

    public static String toStringCommands() {
        String result = "List of valid commands:\n";
        for (Command cmd : Command.values()) {
            result += String.format("%s -> %s\n", cmd.syntax, cmd.description);
        }
        return result;
    }

    public String getSyntax() {
        return syntax;
    }

    public String getKeyword() {
        return keyword;
    }
}
