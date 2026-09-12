package bonbon.parser;

import bonbon.exception.BonBonDateTimeParseException;
import bonbon.exception.BonBonInvalidParameterException;
import bonbon.exception.BonBonUnknownCommandException;

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
    public static String[] readInput(String input) throws BonBonInvalidParameterException, BonBonUnknownCommandException, BonBonDateTimeParseException {
        if (input.equals("")) {
            throw new BonBonUnknownCommandException("");
        }

        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];

        switch (keyword) {
            case "list":
                return new String[] {"list"};

            case "mark":
                if (splitInput.length != 2) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                return splitInput;

            case "unmark":
                if (splitInput.length != 2) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                return splitInput;

            case "todo":
                if (input.length() <= 5) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                return new String[] {"todo", input.substring(5)};

            case "deadline" : {
                if (input.length() <= 9) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                String substrings = input.substring(9);
                String[] splitDates = substrings.split(Pattern.quote(" /by "));
                if (splitDates.length != 2) {
                    throw new BonBonDateTimeParseException();
                }
                return new String[]{"deadline", splitDates[0], splitDates[1]};
            }

            case "event": {
                if (input.length() <= 6) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                String substrings = input.substring(6);
                String[] splitDates = substrings.split(Pattern.quote(" /to ") + "|" + Pattern.quote(" /from "));
                if (splitDates.length != 3) {
                    throw new BonBonDateTimeParseException();
                }
                return new String[]{"event", splitDates[0], splitDates[1], splitDates[2]};
            }

            case "delete":
                if (splitInput.length != 2) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                return splitInput;

            case "find":
                if (input.length() <= 5) {
                    throw new BonBonInvalidParameterException(keyword);
                }
                return new String[] {"find", input.substring(5)};

            default:
                throw new BonBonUnknownCommandException(keyword);
        }
    }
}
