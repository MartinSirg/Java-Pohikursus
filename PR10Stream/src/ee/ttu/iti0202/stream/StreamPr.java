package ee.ttu.iti0202.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPr {

    public List<Integer> numbersList(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    public List<Integer> evenNumbers(int n) {
        return IntStream.rangeClosed(0, n).filter(num -> num % 2 == 0).boxed().collect(Collectors.toList());
    }

    public int smallestPositiveNumber(List<Integer> numbers) {
        return 1;
    }
}
