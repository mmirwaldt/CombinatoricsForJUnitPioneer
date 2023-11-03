package org.java.junit.pioneer.jupiter.combinatorial.samples.combinednumbers;

import org.java.junit.pioneer.jupiter.combinatorial.generators.api.CombinatorialGenerator;
import org.java.junit.pioneer.jupiter.combinatorial.generators.impl.IterativeGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestNumberCombinerTest {
    private static final CombinatorialGenerator generator = new IterativeGenerator();

    private final static Set<String> NUMBERS = Set.of(0, 1, 2, 10, 11, 20, 21, 22, 101, 110, 111, 121)
            .stream()
            .map(String::valueOf)
            .collect(Collectors.toSet());

    public static Stream<Arguments> pairCombinationsWithoutRepetition() {
        return generator.combineWithoutRepetition(NUMBERS, 2)
                .stream()
                .map(Set::iterator)
                .map(iterator -> Arguments.arguments(iterator.next(), iterator.next()));
    }

    public static Stream<Arguments> tripleCombinationsWithoutRepetition() {
        return generator.combineWithoutRepetition(NUMBERS, 3)
                .stream()
                .map(Set::iterator)
                .map(iterator -> Arguments.arguments(iterator.next(), iterator.next(), iterator.next()));
    }

    @ParameterizedTest
    @MethodSource("pairCombinationsWithoutRepetition")
    void test_2_strings(String left, String right) {
        BigInteger expected = findLargest(Set.of(left, right));
        BigInteger actual = LargestNumberCombiner.largestNumber(left, right);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("tripleCombinationsWithoutRepetition")
    void test_3_strings(String left, String middle, String right) {
        BigInteger expected = findLargest(Set.of(left, middle, right));

        BigInteger actual = LargestNumberCombiner.largestNumber(left, middle, right);
        assertEquals(expected, actual);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static BigInteger findLargest(Set<String> left) {
        Set<List<String>> permutations = generator.permutateWithoutRepetition(left);
        return permutations.stream()
                .map(list -> String.join("", list))
                .map(BigInteger::new)
                .reduce(BigInteger::max)
                .get();
    }
}
