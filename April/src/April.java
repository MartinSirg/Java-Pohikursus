public class April {
    private static final int SIX = 6, CONSTX = 7, CONSTY = 32, PUSH_COUNT = 14;

    public static String answer(int question) {
        switch (question) {
            case 0: return "10";
            case 2: return "git stash";
            case 3: return "20";
            case 4: return "15";
            case 5: return Integer.toString(Integer.MAX_VALUE);
            case SIX: return "01.01.1970";
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
        String text = "Look in thy glass and tell the face thou viewest "
                + "Now is the time that face should form another; "
                + "Whose fresh repair if now thou not renewest, "
                + "Thou dost beguile the world, unbless some mother. "
                + "For where is she so fair whose unear'd womb "
                + "Disdains the tillage of thy husbandry? "
                + "Or who is he so fond will be the tomb, "
                + "Of his self-love to stop posterity? "
                + "Thou art thy mother's glass and she in thee "
                + "Calls back the lovely April of her prime; "
                + "So thou through windows of thine age shalt see, "
                + "Despite of wrinkles this thy golden time. "
                + "But if thou live, remember'd not to be, "
                + "Die single and thine image dies with thee. ";
        String[] textArray = text.split(" ");
        System.out.println(textArray.length);
        return textArray[nr];
    }

    public static int guess(int n) {
        return n * CONSTX - CONSTY + PUSH_COUNT;
    }

    public static void main(String[] args) {
    }
}
