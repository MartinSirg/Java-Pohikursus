public class April {

    public static String answer(int question) {
        switch (question) {
            case 0: return "10";
            case 2: return "revert";
            default: return "";
        }
    }

    public static int x(int a) {
        int result = 0;
        if (a < 0) {
            System.out.println("-");
            for (int i = 0; i > a - 1; i--) {
                System.out.println(result);
                result += i;
            }
        } else {
            System.out.println("+");
            for (int i = 0; i < a + 1; i++) {
                result += i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
