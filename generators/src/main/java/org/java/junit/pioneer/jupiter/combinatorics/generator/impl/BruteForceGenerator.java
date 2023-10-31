package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatoricsGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.multiplyExact;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * This implementation is used to compare other implementations regarding correctness of the result and performance.
 */
public class BruteForceGenerator implements CombinatoricsGenerator {
    @Override
    public <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements) {
        return variateWithoutRepetition(elements, elements.size());
    }

    @Override
    public <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        Set<E> elements = elementsWithFrequencies.keySet();
        int size = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        Set<List<E>> permutations = variate(elements, size);
        return permutations.stream()
                .filter(permutation -> BruteForceGenerator.toFrequencyMap(permutation).equals(elementsWithFrequencies))
                .collect(Collectors.toSet());
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> results = variateWithRepetition(elements, length);
        results.removeIf(BruteForceGenerator::hasRepetition);
        return results;
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        return variate(elements, length);
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
                .map(BruteForceGenerator::toFrequencyMap)
                .collect(Collectors.toSet());
    }

    private static <E> Set<List<E>> variate(Collection<E> elements, int length) {
        List<E> elementsAsList = List.copyOf(elements);
        int n = elementsAsList.size();
        int totalSize = n;
        for (int i = 1; i < length; i++) {
            totalSize = multiplyExact(totalSize, n);
        }
        Set<List<E>> result = new HashSet<>();
        for (int i = 0; i < totalSize; i++) {
            List<E> variation = new ArrayList<>(nCopies(length, null));
            int remaining = i;
            for (int j = 0; j < length; j++) {
                int remainder = remaining % n;
                variation.set(j, elementsAsList.get(remainder));
                remaining = (remaining - remainder) / n;
            }
            result.add(variation);
        }
        return result;
    }

    private static <E> boolean hasRepetition(List<E> variation) {
        return variation.size() != new HashSet<>(variation).size();
    }

    private static <E> Map<E, Integer> toFrequencyMap(List<E> list) {
        return list.stream().collect(groupingBy(e -> e, summingInt(first -> 1)));
    }
}
