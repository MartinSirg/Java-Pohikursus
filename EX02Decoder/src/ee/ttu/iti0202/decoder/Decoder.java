package ee.ttu.iti0202.decoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


public class Decoder {
    private enum State { LONG_INDEX, SHORT_INDEX, OTHER }

    /**
     * message : 1) + , $, %d, other
     * 1+1
     *Muutujad: suur täht ($), suur number(+), lihtsalt täht
     * 4.lower case small
     * 3.lower case big
     * 1. upper case big
     * 2. upper case small
     * Method deciphers given message based on the dictionary.
     *
     * The message consists of a series of numbers, which point to a letter's
     * index location in the dictionary. e.g. with a dictionary of "abcd" and
     * message of "3021" the deciphered message would be "dacb".
     *
     * If the index of the letter is comprised of 2 or more numbers, the message will
     * read as those numbers separated by plus signs. e.g. 1+3 => 13 ; 1+2+3 => 123.
     *
     * For every "$" symbol that appears in the message, the next
     * letter must be converted to upper case.
     *
     * Any other symbols appearing in the message should be added to the
     * deciphered message untouched.
     *
     * @param dictionary - dictionary to be used.
     * @param message - message to be deciphered.
     * @return - deciphered message.
     */
    private static String decodeMessage(String dictionary, String message) {
        StringBuilder result = new StringBuilder();
        Boolean upperCase = false;
        message = message + "#"; // extra room for loop: index error prevention

        if (message.length() == 0) { return ""; }

        for (int i = 0; i < message.length() - 1; i++) {
            State current;

            if (message.charAt(i) == '$') {
                 upperCase = true; continue;
             } else if (message.charAt(i + 1) == '+') {
                current = State.LONG_INDEX;
            } else if (!Character.isDigit(message.charAt(i))) {
                current = State.OTHER;
            } else {
                current = State.SHORT_INDEX;
            }

            switch (current) {
                case LONG_INDEX:    int regexInt = regexer("^\\d[+]\\d([+]\\d)*", message.substring(i)); // "1+2+354+1+2" => 123

                                    result.append(caseDecider(upperCase, dictionary.charAt(regexInt))); // append dict char to result
                                    i =  i + (String.valueOf(regexInt).length() - 1)  * 2;  // (len - 1) * 2
                    break;
                case SHORT_INDEX:   int dictIndex = Integer.parseInt(String.valueOf(message.charAt(i)));
                                    result.append(caseDecider(upperCase, dictionary.charAt(dictIndex)));
                    break;
                default:    if (upperCase) { result.append(Character.toUpperCase(message.charAt(i)));
                            } else { result.append(message.charAt(i));
                }

            }
            if (upperCase) { upperCase = false;};
        }

        return result.toString();
    }

    /**
     * Outputs a number: 1+2+3+4+541342454243 ==> 12345
     * @param pattern beginning of the string pattern
     * @param str   message to be searched
     * @return an int
     */
    private static int regexer(String pattern, String str) {
        Pattern checkRegex = Pattern.compile(pattern);
        Matcher regexMatcher = checkRegex.matcher(str);

        if (regexMatcher.find()) {
            String bigNumStr = regexMatcher.group().replaceAll("\\+","");
            return Integer.parseInt(bigNumStr);
        }
        return 0; //mdea miks siin nii väga nõuab returni
    }

    private static char caseDecider(Boolean upperCase, char c) {
        if (upperCase) { return Character.toUpperCase(c);
        } else { return c;}
    }

    public static void main(String[] args) {
        System.out.print("Enter dictionary: ");
        Scanner scanner = new Scanner(System.in);
        String dictionary = scanner.nextLine(), message;
        while (true) {
            System.out.print("Enter message: ");
            message = scanner.nextLine();
            System.out.println("Decoded message: " + decodeMessage(dictionary, message));
        }
    }

}

