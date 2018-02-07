package ee.ttu.iti0202.string;

public class StringOperations {

    public static void main(String[] args) {
        System.out.println(removeDuplicates("aaabbbc")); // abc
        System.out.println(removeDuplicates("")); //
        System.out.println(removeDuplicates("tere")); // tere
        System.out.println(removeDuplicates("t")); // tere

        System.out.println(countDigits("12ab3")); // 3
        System.out.println(countDigits("abc")); // 0
        System.out.println(countDigits("1111222")); // 7

        System.out.println(xyBalance("aaxbby")); // true
        System.out.println(xyBalance("aaxbb")); // false
        System.out.println(xyBalance("yaaxbb")); // false
    }

    private static String removeDuplicates(String input) {
        if (input.length() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(input.charAt(0));
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) != input.charAt(i)) {
                result.append(input.charAt(i));
            }
        }
        return result.toString();
    }

    private static int countDigits(String input) {
        int counter = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                counter += 1;
            }
        }
        return counter;
    }

    private static Boolean xyBalance(String input) {
        Boolean balanced = true;
        for (char c : input.toCharArray()) {
            if (balanced && c == 'x') {
                balanced = false;
            } else if (!balanced && c == 'y') {
                balanced = true;
            }
        }
        return balanced;
    }
    private static void test(String name) {
        for (char c : name.toCharArray()) {
            System.out.println(c);
        }
    }
}
