package ee.ttu.iti0202.rec;

public class Sum {
    public static void main(String[] args) {
        System.out.println(recSum(-1, 1));
    }

    public static int recSum(int n) {
        if (n == 0) {
            return 0;
        } else if (n < 0){
            return n + recSum(n + 1);
        } else {
            return n + recSum(n -1);
        }
    }

    public static int recSum(int a, int b) {
        if (a == b) {
            return b;
        } if (b > a) {
            return a + recSum(a + 1, b);
        } else {
            return a + recSum(a - 1, b);
        }
    }
}
