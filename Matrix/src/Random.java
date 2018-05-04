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
        return str.replaceAll("[^" + word + "]", "+");
    }


    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.xyBalance("aaxbb"));
        System.out.println(random.zipZap("zipXzap"));
        System.out.println(random.starOut("ab**cd"));
    }

}
