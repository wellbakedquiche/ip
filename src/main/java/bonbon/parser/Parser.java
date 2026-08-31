package bonbon.parser;

import java.util.regex.Pattern;

public class Parser {
    public static String[] readInput(String input) {
        if (input.equals("")) {
            return new String[] {""};
        }
        String[] splitInput = input.split(" ");
        String keyword = splitInput[0];
        if (keyword.equals("list")) {
            return new String[] {"list"};
        } else if (keyword.equals("mark")) {
            if (splitInput.length == 2) {
                return new String[]{"mark", splitInput[1]};
            }
            System.out.println("mark needs to be followed by one argument, its index!");
            return new String[] {""};
        } else if (keyword.equals("unmark")) {
            if (splitInput.length == 2) {
                return new String[]{"unmark", splitInput[1]};
            }
            System.out.println("unmark needs to be followed by one argument, its index!");
            return new String[] {""};
        } else if (keyword.equals("todo")) {
            if (input.length() > 5) {
                return new String[]{"todo", input.substring(5)};
            }
            System.out.println("todo needs to be followed by one argument, its description!");
            return new String[] {""};
        } else if (keyword.equals("deadline")) {
            if (input.length() > 9) {
                String substrings = input.substring(9);
                String[] splitDates = substrings.split(Pattern.quote(" /by "));
                if (splitDates.length == 2) {
                    return new String[]{"deadline", splitDates[0], splitDates[1]};
                }
            }
            System.out.println("deadline has the format 'deadline <description> /by <duedate>'!");
            return new String[] {""};
        } else if (keyword.equals("event")) {
            if (input.length() > 6) {
                String substrings = input.substring(6);
                String[] splitDates = substrings.split(Pattern.quote(" /from ") + "|" + Pattern.quote(" /to "));
                if (splitDates.length == 3) {
                    return new String[]{"event", splitDates[0], splitDates[1], splitDates[2]};
                }
            }
            System.out.println("event has the format 'event <description> /from <start> /to <end>'!");
            return new String[] {""};
        } else if (keyword.equals("delete")) {
            if (splitInput.length == 2) {
                return new String[]{"delete", splitInput[1]};
            }
            System.out.println("delete needs to be followed by one argument, its index!");
            return new String[] {""};
        }
        return new String[] {"error"};
    }
}
