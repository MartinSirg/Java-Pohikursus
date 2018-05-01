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
        String text = "Look in thy glass and tell the face thou viewest\n" +
                "Now is the time that face should form another;\n" +
                "Whose fresh repair if now thou not renewest,\n" +
                "Thou dost beguile the world, unbless some mother.\n" +
                "For where is she so fair whose unear'd womb\n" +
                "Disdains the tillage of thy husbandry?\n" +
                "Or who is he so fond will be the tomb,\n" +
                "Of his self-love to stop posterity? \n" +
                "Thou art thy mother's glass and she in thee\n" +
                "Calls back the lovely April of her prime;\n" +
                "So thou through windows of thine age shalt see,\n" +
                "Despite of wrinkles this thy golden time.\n" +
                "  But if thou live, remember'd not to be,\n" +
                "  Die single and thine image dies with thee.\n";
        String[] textArray = text.split(" ");
        return textArray[nr];
    }

    public static int guess(int n) {
        return 0;
    }

    public static void main(String[] args) {
    }
}
