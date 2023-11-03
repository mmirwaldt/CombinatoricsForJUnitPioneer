package org.java.junit.pioneer.jupiter.combinatorial.generators.impl;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test compares both generators to check that the IterativePermutator also works with more elements than
 * used in @{@link BasicGeneratorTest}.
 */
public class ComparingGeneratorTest {
    private final static BruteForceGenerator BRUTE_FORCE_GENERATOR = new BruteForceGenerator();
    private final static IterativeGenerator ITERATIVE_GENERATOR = new IterativeGenerator();

    @Test
    void test_permutateWithoutRepetition_6_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F");
        assertEquals(BRUTE_FORCE_GENERATOR.permutateWithoutRepetition(elements),
                ITERATIVE_GENERATOR.permutateWithoutRepetition(elements));
    }

    @Test
    void test_permutateWithRepetition_4_elements() {
        Map<String, Integer> frequencies = Map.of("A", 2,"B", 3, "C", 1, "D", 2);
        assertEquals(BRUTE_FORCE_GENERATOR.permutateWithRepetition(frequencies),
                ITERATIVE_GENERATOR.permutateWithRepetition(frequencies));
    }

    @Test
    void test_variateWithoutRepetition_6_out_of_8_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F", "G", "H");
        assertEquals(BRUTE_FORCE_GENERATOR.variateWithoutRepetition(elements, 6),
                ITERATIVE_GENERATOR.variateWithoutRepetition(elements, 6));
    }

    @Test
    void test_variateWithRepetition_4_out_of_6_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F");
        assertEquals(BRUTE_FORCE_GENERATOR.variateWithRepetition(elements, 4),
                ITERATIVE_GENERATOR.variateWithRepetition(elements, 4));
    }

    @Test
    void test_combineWithoutRepetition_6_out_of_8_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F", "G", "H");
        assertEquals(BRUTE_FORCE_GENERATOR.combineWithoutRepetition(elements, 6),
                ITERATIVE_GENERATOR.combineWithoutRepetition(elements, 6));
    }

    @Test
    void test_combineWithRepetition_4_out_of_6_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F");
        assertEquals(BRUTE_FORCE_GENERATOR.combineWithRepetition(elements, 4),
                ITERATIVE_GENERATOR.combineWithRepetition(elements, 4));
    }
}
