package ee.ttu.iti0202.kt1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KT4 {

    public List<Integer> zeroEnd(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        int zeroCount = 0;
        for (Integer i: nums) {
            if (i == 0) zeroCount++;
            if (i != 0) result.add(i);
        }
        for (int i = 0; i < zeroCount; i++) {
            result.add(0);
        }
        return result;
    }

    public String mixString(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            if (i < a.length()) sb.append(a.charAt(i));
            if (i < b.length()) sb.append(b.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        KT4 kt4 = new KT4();
        System.out.println(kt4.zeroEnd(Arrays.asList(1, 2, 3, 0))); // [1, 2, 3, 0]
        System.out.println(kt4.zeroEnd(Arrays.asList(1, 0, 3, 0))); // [1, 3, 0, 0]
        System.out.println(kt4.zeroEnd(Arrays.asList(0, 0))); // [0, 0]
        System.out.println(kt4.zeroEnd(Arrays.asList(4, 8))); // [4, 8]

        System.out.println(kt4.mixString("a", "b")); // ab
        System.out.println(kt4.mixString("ac", "b")); // abc
        System.out.println(kt4.mixString("ac", "b")); // abc
    }
}
