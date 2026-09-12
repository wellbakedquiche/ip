package bonbon.parser;

import bonbon.exception.BonBonException;
import bonbon.exception.BonBonInvalidInput;
import bonbon.exception.BonBonUnknownCommand;

import java.util.regex.Pattern;

/**
 * Parses input string into an array of inputs.
 */
public class Parser {

    /**
     * Returns an array of arguments based on the format of the input.
     *
     * @param input the input to be parsed.
     * @return array of arguments.
     */
    public static String[] readInput(String input) throws BonBonUnknownCommand {
        if (input.equals("")) {
            throw new BonBonUnknownCommand("");
        }

        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];


        switch (keyword) {
            case "list":
                return new String[] {"list"};
            case "mark":
                return parseSingleIndex(input);
            case "unmark":
                return parseSingleIndex(input);
            case "todo":
                return parseToDo(input);
            case "deadline":
                return parseDeadline(input);
            case "event":
                return parseEvent(input);
            case "delete":
                return parseSingleIndex(input);
            case "find":
                return parseFind(input);
            default:
                throw new BonBonUnknownCommand(keyword);
        }
    }

    /**
     * Parses mark and unmark commands.
     *
     * @param input The input command
     * @return Array of argument
     */
    private static String[] parseSingleIndex(String input) throws BonBonInvalidInput {
        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];

        if (splitInput.length != 2) {
            throw new BonBonInvalidInput(keyword);
        }

        try {
            Integer.parseInt(splitInput[1]);
            return splitInput;
        } catch (NumberFormatException e) {
            throw new BonBonInvalidInput(keyword);
        }
    }

    private static String[] parseToDo(String input) throws BonBonInvalidInput {
        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];

        if (splitInput.length < 2) {
            throw new BonBonInvalidInput(keyword);
        }

        return new String[] {"todo", input.substring(5)};
    }

    private static String[] parseDeadline(String input) throws BonBonInvalidInput {
        if (input.length() <= 9) {
            throw new BonBonInvalidInput("deadline");
        }

        String substrings = input.substring(9);
        String[] splitDates = substrings.split(Pattern.quote(" /by "));

        if (splitDates.length != 2) {
            throw new BonBonInvalidInput("deadline");
        }

        return new String[]{"deadline", splitDates[0], splitDates[1]};
    }
    private static String[] parseEvent(String input) throws BonBonInvalidInput {
        if (input.length() <= 6) {
            throw new BonBonInvalidInput("event");
        }

        String substrings = input.substring(9);
        String[] splitDates = substrings.split(Pattern.quote(" /from ") + "|" + Pattern.quote(" /to "));

        if (splitDates.length != 3) {
            throw new BonBonInvalidInput("event");
        }

        return new String[]{"event", splitDates[0], splitDates[1], splitDates[2]};
    }

    private static String[] parseFind(String input) throws BonBonInvalidInput {
        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];

        if (splitInput.length < 2) {
            throw new BonBonInvalidInput(keyword);
        }

        return new String[] {"find", input.substring(5)};
    }
}
