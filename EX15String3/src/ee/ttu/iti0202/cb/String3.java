package ee.ttu.iti0202.cb;

import java.util.ArrayList;
import java.util.List;

public class String3 {
    public int countYZ(String str) {
        List<String> list = new ArrayList<>();
        int endOfLast = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                list.add(str.substring(endOfLast, i));
                endOfLast = i + 1;
            }
        }
        list.add(str.substring(endOfLast));
        int count = 0;
        for (String word: list) {
            if (word.length() != 0) {
                char lastChar = word.toLowerCase().charAt(word.length() - 1);
                if (lastChar == 'y' || lastChar == 'z') count++;
            }
        }
        return count;
    }

    public String withoutString(String base, String remove) {
        String allLower = remove.toLowerCase();
        String allUpper = remove.toUpperCase();
        return base.replace(remove, "").replace(allLower, "").replace(allUpper, "");
    }

    public boolean equalIsNot(String str) {
        int isCount = 0, notCount = 0;
        for (int i = 0; i < str.length() - 2; i++) {
            if (str.substring(i, i + 3).equals("not")) notCount++;
            if (str.substring(i, i + 2).equals("is")) isCount++;
        }
        if (str.length() != 0 && str.substring(str.length() - 2).equals("is")) isCount++;
        return isCount == notCount;
    }

    public boolean gHappy(String str) {
        str = str.replace("ggg", "");
        str = str.replace("gg", "");
        return !str.contains("g");
    }

    public int countTriple(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 2; i++) {
            if (str.charAt(i) == str.charAt(i + 1)
                    && str.charAt(i) == str.charAt(i + 2)) {
                count++;
            }
        }
        return count;
    }

    public int sumDigits(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                sum += Integer.parseInt(str.substring(i, i + 1));
            }
        }
        return sum;
    }

    public String sameEnds(String string) {
        String result = "";
        for (int i = 1; i < (string.length() / 2) + 1; i++) {
            if (string.substring(0, i).equals(string.substring(string.length() - i))) {
                result = string.substring(0, i);
            }
        }
        return result;
    }

    public String mirrorEnds(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == string.charAt(string.length() - i - 1)) {
                sb.append(string.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public int maxBlock(String str) {
        if (str.length() == 0) return 0;
        int currentMax = 1;
        for (char c: str.toCharArray()) {
            for (int i = 1; i < str.length(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
                if (str.contains(sb.toString()) && sb.toString().length() > currentMax) {
                    currentMax = sb.toString().length();
                }
            }
        }
        return currentMax;
    }

    public int sumNumbers(String str) {
        List<Integer> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean wasLastInt = false;
        for (char c: str.toCharArray()) {
            if (!wasLastInt) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                    wasLastInt = true;
                }
            } else {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    if (!sb.toString().equals("")) nums.add(Integer.parseInt(sb.toString()));
                    wasLastInt = false;
                    sb = new StringBuilder();
                }
            }
        }
        if (wasLastInt) nums.add(Integer.parseInt(sb.toString()));
        int sum = 0;
        for (Integer i: nums) {
            sum += i;
        }
        return sum;
    }

    public String notReplace(String str) {
        return str.replaceAll("(?<![a-zA-Z])is(?![a-zA-Z])", "is not");
        // (?<![a-zA-Z])is - match if [a-zA-Z]  is not behind;
        // is(?![a-zA-Z]) - match if [a-zA-Z] is not after;
        //(?<![a-zA-Z]) ja (?![a-zA-Z]) dont use up a char;
    }


    public static void main(String[] args) {
        String3 string3 = new String3();
//        System.out.println(string3.countYZ("DAY abc XYZ"));
        string3.sameEnds("abXYab");
    }
}
