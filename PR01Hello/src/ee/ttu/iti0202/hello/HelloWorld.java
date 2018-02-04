package ee.ttu.iti0202.hello;

public class HelloWorld {

    public static void main(String[] args) {

        System.out.println(helloStudent("Alfred")); // "Hello student Alfred, whose name is 6 characters long!"
        System.out.println(helloStudent("John")); // "Hello student John, whose name is 4 characters long!"
        System.out.println(helloStudent("Elizabeth")); // "Hello student Elizabeth, whose name is too long!"

        System.out.println(sumGenerator(100, 10)); // 450
        System.out.println(sumGenerator(50, 4)); // 312
        System.out.println(sumGenerator(1, 2)); // 0

        System.out.println(wordSeparator("Hello", 1, 3)); // "el"
        System.out.println(wordSeparator("MagicalWord", 5, 11)); // "alWord"
        System.out.println(wordSeparator("KuuliLennuTeeTunneliLuuk", -12, 5)); // ""

    }

    public static String helloStudent(String name) {
        int nameLen = 6;
        if (name.length() <= nameLen) {
            return "Hello student " + name + ", whose name is " + name.length() + " characters long!";
        } else {
            return "Hello student " + name + ", whose name is too long!";
        }
    }
    public static int sumGenerator(int termination, int increment) {
        int result = 0;
        for (int i = 0; i < termination; i += increment) {
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