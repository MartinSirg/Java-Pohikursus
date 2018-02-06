package ee.ttu.iti0202.decoder;

import java.util.regex.*;

public class Decoder {
    String ALLOWED_CHARS = "0123456789";
    /**
     * message : 1) + , $, %d, other
     * 1+1
     *
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

        for (int i = 0; i < message.length() - 1; i++) {
            System.out.println(i + ".");
            if (message.charAt(i + 1) == '+') {                 // when next char is '+'
                int regexInt = regexer("^\\d[+]\\d([+]\\d)*", message.substring(i)); // "1+2+354+1+2" => 123
                result.append(dictionary.charAt(regexInt));     // append dict char to result
                 i = i + String.valueOf(regexInt).length() - 1; // increase i in according to number length
            } else if (message.charAt(i) == '$') {
                char msgChar = message.charAt(i + 1);    //append upper dict char to result
                int dictIndex = Integer.parseInt(String.valueOf(msgChar)); // char -> String -> int XD hax
                result.append(Character.toUpperCase(dictionary.charAt(dictIndex)));
                ++i;
            } else if (Character.isDigit(message.charAt(i))) {
                int dictIndex = Integer.parseInt(String.valueOf(message.charAt(i)));
                result.append(Character.toUpperCase(dictionary.charAt(dictIndex)));
            } else /// normal case | upper + long digit

        }
        result.append("LASTLETTER");
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

    public static void main(String[] args) {
        System.out.println(regexer("^\\d[+]\\d([+]\\d)*", "1+2+3+4+536481+1+4"));
    }

}
