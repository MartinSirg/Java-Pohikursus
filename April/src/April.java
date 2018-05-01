public class April {

    public static String answer(int question) {
        switch (question) {
            case 0: return "10";
            case 2: return "git stash";
            case 3: return "20";
            case 4: return "15";
            case 5: return Integer.toString(Integer.MAX_VALUE);
            case 6: return "01.01.1970";
            default: return "";
        }
    }

    public static int x(int a) {
        int result = 0;
        if (a < 0) {
            for (int i = 0; i > a - 1; i--) {
                System.out.println(result);
                result += i;
            }
        } else {
            for (int i = 0; i < a + 1; i++) {
                result += i;
            }
        }
        return result;
    }

    public static String easy(int nr) {
        switch (nr) {
            case 6: return "which";
            case 12: return "a";
            case 43: return "with";
            case 46: return "sea's";
            case 54: return "if";
            case 60: return "in";
            case 65: return "story";
            case 82: return "fair";
            case 114: return "not";
            case 116: return "sell";
            default: return "";
        }
    }

    public static void main(String[] args) {
    }
}
