package ee.ttu.iti0202.hello;

public class HelloWorld {

    public static void main(String[] args) {
    }

    public static String helloStudent(String name) {
        int nameLen = 6 ;
        if (name.length() <= nameLen) {
            return "Hello student " + name + ", whose name is " + name.length() + " characters long!";
        } else {
            return "Hello student " + name + ", whose name is too long!";
        }
    }
    public static int sumGenerator(int termination, int increment) {
        int result = 0;
        for (int i = 0; i != termination; i += increment) {
            result += i;
        }
        return result;
    }
    public static String wordSeparator(String str, int start, int end) {
        if (start < 0 || end > str.length()) {
            return "";
        } else {
            return str.substring(start, end);
        }
    }
}