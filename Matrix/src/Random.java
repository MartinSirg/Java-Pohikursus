public class Random {
    private boolean xyBalance(String str) {
        System.out.println(str.length() - 1);
        for (int i = 4; i > 0; i--) {
            System.out.println(str.charAt(i));
            if (str.charAt(i) == 'y') return true;
            if (str.charAt(i) == 'x') return false;
        }
        return true;
    }

    public String zipZap(String str) {
        return str.replaceAll("zp", "zp");
    }

    public String starOut(String str) {
        return str.replaceAll(".\\*+.", "").replaceFirst("\\*.", "").replaceAll(".\\*", "").replace("", "");
    }

    public String plusOut(String str, String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() - word.length() + 1; i++) {
            System.out.println(i);
            if (str.substring(i,i + word.length()).equals(word)) {
                i = i + word.length();
                sb.append(word);
            } else {
                sb.append("+");
            }
        }
        return sb.toString();
    }

    public boolean evenlySpaced(int a, int b, int c) {
        int big = Math.max(a, Math.max(b, c));
        int small = Math.min(a, Math.min(b, c));
        int medium;
        if (a != big || a != small) {
            medium = a;
        } else if (b != big || b != small) {
            medium = b;
        } else {
            medium = c;
        }
        return Math.abs(small - medium) == Math.abs(big - medium);
    }


    public static void main(String[] args) {
        Random random = new Random();
//        System.out.println(random.xyBalance("aaxbb"));
//        System.out.println(random.zipZap("zipXzap"));
//        System.out.println(random.starOut("ab**cd"));
        System.out.println(random.plusOut("1x2xy34xyabcxy", "xy"));
    }

}
