package org.java.junit.pioneer.jupiter.combinatorial.samples.countingsort.impl;

import org.java.junit.pioneer.jupiter.combinatorial.generator.api.CombinatorialGenerator;
import org.java.junit.pioneer.jupiter.combinatorial.generator.impl.IterativeGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.nCopies;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteArrayCountingSorterTest {
    private final static ByteArrayCountingSorter byteArrayCountingSorter = new ByteArrayCountingSorter();

    private final static CombinatorialGenerator generator = new IterativeGenerator();

    /**
     * Keys are the elements and values are the frequencies.
     */
    private final static Map<Integer, Integer> FREQUENCIES_FOR_4_ELEMENTS = Map.of(1, 2, 3, 2, 5, 1);

    private final static List<Integer> EXPECTED_4_ELEMENTS = FREQUENCIES_FOR_4_ELEMENTS.entrySet().stream()
            .flatMap(entry -> nCopies(entry.getValue(), entry.getKey()).stream())
            .sorted()
            .toList();

    private final static Set<Integer> LIST_OF_7_ELEMENTS = Set.of(0, 1, 2, 4, 5, 6, 8);

    public static Stream<Arguments> permutationsWithRepetitionsWith4Elements() {
        return generator.permutateWithRepetition(FREQUENCIES_FOR_4_ELEMENTS).stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("permutationsWithRepetitionsWith4Elements")
    void test_byteArrayCountingSorter_permutation_4elements(List<Integer> unsortedIntList) {
        byteArrayCountingSorter.sort(unsortedIntList);
        assertEquals(EXPECTED_4_ELEMENTS, unsortedIntList);
    }

    public static Stream<Arguments> variationsWithRepetitionOf5elementsOutOf7() {
        return generator.variateWithRepetition(LIST_OF_7_ELEMENTS, 5).stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("variationsWithRepetitionOf5elementsOutOf7")
    void test_byteArrayCountingSorter_variation_5elementsOutOf7(List<Integer> unsortedIntList) {
        List<Integer> expected = new ArrayList<>(unsortedIntList);
        Collections.sort(expected);
        byteArrayCountingSorter.sort(unsortedIntList);
        assertEquals(expected, unsortedIntList);
    }
}
