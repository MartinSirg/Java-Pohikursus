package ee.ttu.iti0202.kt1.KT1;

import java.util.ArrayList;
import java.util.List;

public class KT1 {

    public static void main(String[] args) {
        System.out.println(starOut("ab*cd"));
        System.out.println(starOut("ab**cd"));
        System.out.println(starOut("sm*eilly"));
        System.out.println(starOut("*ba"));
    }

    public static String starOut(String str) {
        String result = str;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equals("*")) {
                if (i == 0) {
                    result = result.replace(result.charAt(i + 1), '*');
                } else if (i == str.length() - 1) {
                    result = result.replace(result.charAt(i - 1), '*');
                } else {
                    result = result.replace(result.charAt(i + 1), '*');
                    result = result.replace(result.charAt(i - 1), '*');
                }
            }
        }
        result = result.replace("*", "");
        return result;
    }
}
