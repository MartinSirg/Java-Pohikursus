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
}