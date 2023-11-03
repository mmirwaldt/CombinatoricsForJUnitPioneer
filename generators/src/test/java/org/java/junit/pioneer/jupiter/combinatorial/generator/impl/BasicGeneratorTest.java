package org.java.junit.pioneer.jupiter.combinatorial.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorial.generator.api.CombinatorialGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Remark:
 * The HashSets are used to ignore the order of the results.
 */
public class BasicGeneratorTest {
    private final static BruteForceGenerator bruteForceGenerator = new BruteForceGenerator();
    private final static IterativeGenerator ITERATIVE_GENERATOR = new IterativeGenerator();

    private final static RecursiveGenerator recursiveGenerator = new RecursiveGenerator();

    public static Stream<Arguments> generators() {
        return Stream.of(Arguments.of(bruteForceGenerator), Arguments.of(ITERATIVE_GENERATOR), Arguments.of(recursiveGenerator));
    }
    
    @ParameterizedTest
    @MethodSource("generators")
    void test_permutateWithoutRepetition_2_elements(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B"),
                List.of("B", "A"));
        Set<List<String>> actual = generator.permutateWithoutRepetition(Set.of("A", "B"));
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_permutateWithoutRepetition_3_elements(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B", "C"),
                List.of("B", "A", "C"),
                List.of("A", "C", "B"),
                List.of("B", "C", "A"),
                List.of("C", "A", "B"),
                List.of("C", "B", "A"));
        Set<List<String>> actual = generator.permutateWithoutRepetition(Set.of("A", "B", "C"));
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_permutateWithRepetition_2_elements_frequency_1_and_2(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B", "B"),
                List.of("B", "A", "B"),
                List.of("B", "B", "A"));
        Set<List<String>> actual = generator.permutateWithRepetition(Map.of("A", 1, "B", 2));
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_permutateWithRepetition_2_elements_frequency_2_and_2(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "A", "B", "B"),
                List.of("A", "B", "A", "B"),
                List.of("A", "B", "B", "A"),
                List.of("B", "B", "A", "A"),
                List.of("B", "A", "A", "B"),
                List.of("B", "A", "B", "A"));
        Set<List<String>> actual = generator.permutateWithRepetition(Map.of("A", 2, "B", 2));
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_permutateWithRepetition_2_elements_frequency_1_and_3(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B", "B", "B"),
                List.of("B", "A", "B", "B"),
                List.of("B", "B", "A", "B"),
                List.of("B", "B", "B", "A"));
        Set<List<String>> actual = generator.permutateWithRepetition(Map.of("A", 1, "B", 3));
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_permutateWithRepetition_2_elements_frequency_2_and_3(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "A", "B", "B", "B"),
                List.of("A", "B", "A", "B", "B"),
                List.of("A", "B", "B", "A", "B"),
                List.of("A", "B", "B", "B", "A"),
                List.of("B", "A", "A", "B", "B"),
                List.of("B", "A", "B", "A", "B"),
                List.of("B", "A", "B", "B", "A"),
                List.of("B", "B", "A", "A", "B"),
                List.of("B", "B", "A", "B", "A"),
                List.of("B", "B", "B", "A", "A"));
        Set<List<String>> actual = generator.permutateWithRepetition(Map.of("A", 2, "B", 3));
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithoutRepetition_with_3_elements_and_length_2(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("B", "C"),
                List.of("B", "A"),
                List.of("C", "A"),
                List.of("C", "B"));
        Set<List<String>> actual = generator.variateWithoutRepetition(Set.of("A", "B", "C"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithoutRepetition_with_4_elements_and_length_2(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("A", "D"),
                List.of("B", "C"),
                List.of("B", "D"),
                List.of("C", "D"),
                List.of("B", "A"),
                List.of("C", "A"),
                List.of("D", "A"),
                List.of("C", "B"),
                List.of("D", "B"),
                List.of("D", "C"));
        Set<List<String>> actual = generator.variateWithoutRepetition(Set.of("A", "B", "C", "D"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithoutRepetition_with_4_elements_and_length_3(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "B", "C"),
                List.of("A", "B", "D"),
                List.of("A", "C", "D"),
                List.of("A", "C", "B"),
                List.of("A", "D", "B"),
                List.of("A", "D", "C"),
                List.of("B", "A", "C"),
                List.of("B", "A", "D"),
                List.of("B", "C", "D"),
                List.of("B", "C", "A"),
                List.of("B", "D", "A"),
                List.of("B", "D", "C"),
                List.of("C", "A", "B"),
                List.of("C", "A", "D"),
                List.of("C", "B", "D"),
                List.of("C", "B", "A"),
                List.of("C", "D", "A"),
                List.of("C", "D", "B"),
                List.of("D", "A", "B"),
                List.of("D", "A", "C"),
                List.of("D", "B", "C"),
                List.of("D", "B", "A"),
                List.of("D", "C", "A"),
                List.of("D", "C", "B")
        );
        Set<List<String>> actual = generator.variateWithoutRepetition(Set.of("A", "B", "C", "D"), 3);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithRepetition_with_2_elements_and_length_2(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "A"),
                List.of("A", "B"),
                List.of("B", "A"),
                List.of("B", "B")
        );
        Set<List<String>> actual = generator.variateWithRepetition(Set.of("A", "B"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithRepetition_with_2_elements_and_length_3(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "A", "A"),
                List.of("A", "A", "B"),
                List.of("A", "B", "A"),
                List.of("A", "B", "B"),
                List.of("B", "A", "A"),
                List.of("B", "A", "B"),
                List.of("B", "B", "A"),
                List.of("B", "B", "B")
        );
        Set<List<String>> actual = generator.variateWithRepetition(Set.of("A", "B"), 3);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithRepetition_with_3_elements_and_length_2(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "A"),
                List.of("A", "B"),
                List.of("A", "C"),
                List.of("B", "A"),
                List.of("B", "B"),
                List.of("B", "C"),
                List.of("C", "A"),
                List.of("C", "B"),
                List.of("C", "C")
        );
        Set<List<String>> actual = generator.variateWithRepetition(Set.of("A", "B", "C"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_variateWithRepetition_with_3_elements_and_length_3(CombinatorialGenerator generator) {
        Set<List<String>> expected = Set.of(
                List.of("A", "A", "A"),
                List.of("A", "A", "B"),
                List.of("A", "A", "C"),
                List.of("A", "B", "A"),
                List.of("A", "B", "B"),
                List.of("A", "B", "C"),
                List.of("A", "C", "A"),
                List.of("A", "C", "B"),
                List.of("A", "C", "C"),
                List.of("B", "A", "A"),
                List.of("B", "A", "B"),
                List.of("B", "A", "C"),
                List.of("B", "B", "A"),
                List.of("B", "B", "B"),
                List.of("B", "B", "C"),
                List.of("B", "C", "A"),
                List.of("B", "C", "B"),
                List.of("B", "C", "C"),
                List.of("C", "A", "A"),
                List.of("C", "A", "B"),
                List.of("C", "A", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "B"),
                List.of("C", "B", "C"),
                List.of("C", "C", "A"),
                List.of("C", "C", "B"),
                List.of("C", "C", "C")
        );
        Set<List<String>> actual = generator.variateWithRepetition(Set.of("A", "B", "C"), 3);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_combineWithoutRepetition_pick_2_out_of_3_elements(CombinatorialGenerator generator) {
        Set<Set<String>> expected = Set.of(
                Set.of("A", "B"),
                Set.of("A", "C"),
                Set.of("B", "C"));
        Set<Set<String>> actual = generator.combineWithoutRepetition(Set.of("A", "B", "C"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_combineWithoutRepetition_pick_2_out_of_4_elements(CombinatorialGenerator generator) {
        Set<Set<String>> expected = Set.of(
                Set.of("A", "B"),
                Set.of("A", "C"),
                Set.of("A", "D"),
                Set.of("B", "C"),
                Set.of("B", "D"),
                Set.of("C", "D"));
        Set<Set<String>> actual = generator.combineWithoutRepetition(Set.of("A", "B", "C", "D"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_combineWithoutRepetition_pick_3_out_of_4_elements(CombinatorialGenerator generator) {
        Set<Set<String>> expected = Set.of(
                Set.of("A", "B", "C"),
                Set.of("A", "B", "D"),
                Set.of("A", "C", "D"),
                Set.of("B", "C", "D"));
        Set<Set<String>> actual = generator.combineWithoutRepetition(Set.of("A", "B", "C", "D"), 3);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_combineWithRepetition_pick_2_out_of_3_elements(CombinatorialGenerator generator) {
        Set<Map<String, Integer>> expected = Set.of(
                Map.of("A", 2),
                Map.of("A", 1, "B", 1),
                Map.of("A", 1,  "C", 1),
                Map.of("B", 2),
                Map.of("B", 1, "C", 1),
                Map.of("C", 2));
        Set<Map<String, Integer>> actual = generator.combineWithRepetition(Set.of("A", "B", "C"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_combineWithRepetition_pick_2_out_of_4_elements(CombinatorialGenerator generator) {
        Set<Map<String, Integer>> expected = Set.of(
                Map.of("A", 2),
                Map.of("A", 1, "B", 1),
                Map.of("A", 1, "C", 1),
                Map.of("A", 1, "D", 1),
                Map.of("B", 2),
                Map.of("B",1,  "C", 1),
                Map.of("B",1,  "D", 1),
                Map.of("C", 2),
                Map.of("C",1,  "D", 1),
                Map.of("D", 2));
        Set<Map<String, Integer>> actual = generator.combineWithRepetition(Set.of("A", "B", "C", "D"), 2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generators")
    void test_combineWithRepetition_pick_3_out_of_4_elements(CombinatorialGenerator generator) {
        Set<Map<String, Integer>> expected = Set.of(
                Map.of("A", 3),
                Map.of("A", 2, "B", 1),
                Map.of("A", 2, "C", 1),
                Map.of("A", 2, "D", 1),
                Map.of("A", 1, "B", 2),
                Map.of("A", 1, "B", 1, "C", 1),
                Map.of("A", 1, "B", 1, "D", 1),
                Map.of("A", 1, "C", 2),
                Map.of("A", 1, "C", 1, "D", 1),
                Map.of("A", 1, "D", 2),
                Map.of("B", 3),
                Map.of("B", 2, "C", 1),
                Map.of("B", 2, "D", 1),
                Map.of("B", 1, "C", 2),
                Map.of("B", 1, "C", 1, "D", 1),
                Map.of("B", 1, "D", 2),
                Map.of("C", 3),
                Map.of("C", 2, "D", 1),
                Map.of("C", 1, "D", 2),
                Map.of("D", 3));
        Set<Map<String, Integer>> actual = generator.combineWithRepetition(Set.of("A", "B", "C", "D"), 3);
        assertEquals(expected, actual);
    }
}
