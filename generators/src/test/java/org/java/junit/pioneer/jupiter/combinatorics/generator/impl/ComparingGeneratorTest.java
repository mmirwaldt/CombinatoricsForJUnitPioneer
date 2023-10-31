package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test compares both generators to check that the LoopingGenerator also works with more elements than
 * used in @{@link BasicGeneratorTest}.
 */
public class ComparingGeneratorTest {
    private final static BruteForceGenerator bruteForceGenerator = new BruteForceGenerator();
    private final static LoopingGenerator loopingGenerator = new LoopingGenerator();

    @Test
    void test_permutateWithoutRepetition_6_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F");
        assertEquals(bruteForceGenerator.permutateWithoutRepetition(elements),
                loopingGenerator.permutateWithoutRepetition(elements));
    }

    @Test
    void test_permutateWithRepetition_4_elements() {
        Map<String, Integer> frequencies = Map.of("A", 2,"B", 3, "C", 1, "D", 2);
        assertEquals(bruteForceGenerator.permutateWithRepetition(frequencies),
                loopingGenerator.permutateWithRepetition(frequencies));
    }

    @Test
    void test_variateWithoutRepetition_6_out_of_8_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F", "G", "H");
        assertEquals(bruteForceGenerator.variateWithoutRepetition(elements, 6),
                loopingGenerator.variateWithoutRepetition(elements, 6));
    }

    @Test
    void test_variateWithRepetition_4_out_of_6_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F");
        assertEquals(bruteForceGenerator.variateWithRepetition(elements, 4),
                loopingGenerator.variateWithRepetition(elements, 4));
    }

    @Test
    void test_combineWithoutRepetition_6_out_of_8_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F", "G", "H");
        assertEquals(bruteForceGenerator.combineWithoutRepetition(elements, 6),
                loopingGenerator.combineWithoutRepetition(elements, 6));
    }

    @Test
    void test_combineWithRepetition_4_out_of_6_elements() {
        Set<String> elements = Set.of("A", "B", "C", "D", "E", "F");
        assertEquals(bruteForceGenerator.combineWithRepetition(elements, 4),
                loopingGenerator.combineWithRepetition(elements, 4));
    }
}
