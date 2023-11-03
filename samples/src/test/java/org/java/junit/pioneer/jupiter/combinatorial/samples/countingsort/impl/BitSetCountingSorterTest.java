package org.java.junit.pioneer.jupiter.combinatorial.samples.countingsort.impl;

import org.java.junit.pioneer.jupiter.combinatorial.generators.api.CombinatorialGenerator;
import org.java.junit.pioneer.jupiter.combinatorial.generators.impl.IterativeGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitSetCountingSorterTest {
    private final static BitSetCountingSorter bitSetCountingSorter = new BitSetCountingSorter();
    private final static CombinatorialGenerator generator = new IterativeGenerator();
    private final static List<Integer> EXPECTED_6_ELEMENTS = List.of(1, 2, 4, 5, 6, 8);
    private final static Set<Integer> LIST_OF_7_ELEMENTS = Set.of(0, 1, 2, 4, 5, 6, 8);

    public static Stream<Arguments> permutationsWithoutRepetitionsWith6Elements() {
        return generator.permutateWithoutRepetition(new LinkedHashSet<>(EXPECTED_6_ELEMENTS)).stream()
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("permutationsWithoutRepetitionsWith6Elements")
    void test_bitSetCountingSorter_permutation_6elements(List<Integer> unsortedIntList) {
        bitSetCountingSorter.sort(unsortedIntList);
        assertEquals(EXPECTED_6_ELEMENTS, unsortedIntList);
    }

    public static Stream<Arguments> variationsWithoutRepetitionOf5elementsOutOf7() {
        return generator.variateWithoutRepetition(LIST_OF_7_ELEMENTS, 5).stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("variationsWithoutRepetitionOf5elementsOutOf7")
    void test_bitSetCountingSorter_variation_5elementsOutOf7(List<Integer> unsortedIntList) {
        List<Integer> expected = new ArrayList<>(unsortedIntList);
        Collections.sort(expected);
        bitSetCountingSorter.sort(unsortedIntList);
        assertEquals(expected, unsortedIntList);
    }
}
