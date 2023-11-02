package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatorialGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.multiplyExact;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * This implementation is used to compare other implementations regarding correctness of the result and performance.
 */
public class BruteForceGenerator implements CombinatorialGenerator {
    @Override
    public <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements) {
        return variateWithoutRepetition(elements, elements.size());
    }

    @Override
    public <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        Set<E> elements = elementsWithFrequencies.keySet();
        int size = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        Set<List<E>> permutations = BruteForceVariator.variate(elements, size);
        return permutations.stream()
                .filter(permutation -> BruteForceVariator.toFrequencyMap(permutation).equals(elementsWithFrequencies))
                .collect(Collectors.toSet());
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> results = variateWithRepetition(elements, length);
        results.removeIf(BruteForceVariator::hasRepetition);
        return results;
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        return BruteForceVariator.variate(elements, length);
    }

    @Override
    public <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> result = variateWithoutRepetition(elements, length);
        return result.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        Set<List<E>> result = variateWithRepetition(new HashSet<>(elements), length);
        return result.stream()
                .map(BruteForceVariator::toFrequencyMap)
                .collect(Collectors.toSet());
    }
}
