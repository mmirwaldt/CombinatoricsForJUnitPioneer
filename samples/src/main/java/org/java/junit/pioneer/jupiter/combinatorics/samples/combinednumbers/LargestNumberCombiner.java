package org.java.junit.pioneer.jupiter.combinatorics.samples.combinednumbers;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargestNumberCombiner {
    /**
     * finds the largest number by concatenating all strings of numbers.
     * <p/>
     * Inspired by the cyber dojo exercise "Combined Numbers":
     * <p/>
     * "Write a function accepting a list of non negative integers,
     * <p/>
     * and returning their largest possible combined number as a string. For example
     * given [50, 2, 1, 9]  it returns "95021"    (9 + 50 + 2 + 1)
     * given [5, 50, 56]    it returns "56550"    (56 + 5 + 50)
     * given [420, 42, 423] it returns "42423420" (42 + 423 + 420)
     * <p/>
     * Source [https://blog.svpino.com/about]"
     * <p/>
     * @param numbers
     * @return
     */
    public static BigInteger largestNumber(String... numbers) {
         String result = Stream.of(numbers)
                .sorted(stringConcatComparator())
                .collect(Collectors.joining());
        return new BigInteger(result);
    }

    private static Comparator<String> stringConcatComparator() {
        return (left, right) -> -new BigInteger(left + right).compareTo(new BigInteger(right + left));
    }
}
