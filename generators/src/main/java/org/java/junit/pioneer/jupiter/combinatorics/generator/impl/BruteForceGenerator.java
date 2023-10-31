package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatoricsGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.multiplyExact;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.*;

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
        // See the comment in permutateWithRepetition() in LoopingPermutator for the basic idea
        Map<E, Long> elementsWithFrequenciesAsLongs = elementsWithFrequencies.entrySet()
                .stream().collect(toMap(Map.Entry::getKey, entry -> (long) entry.getValue()));
        List<E> elements = elementsWithFrequencies.entrySet().stream()
                .flatMap(entry -> nCopies(entry.getValue(), entry.getKey()).stream())
                .collect(Collectors.toList());
        List<List<E>> result = new ArrayList<>(variateWithRepetition(elements, elements.size()));
        Set<List<E>> resultAsSet = new HashSet<>(result);
        resultAsSet.removeIf(list -> !list.stream().collect(groupingBy(e -> e, counting()))
                .equals(elementsWithFrequenciesAsLongs));
        return resultAsSet;
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> result = variateWithRepetition(elements, length);
        result.removeIf(variation -> variation.size() != new TreeSet<>(variation).size());
        return result;
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        return variateWithRepetition(new ArrayList<>(elements), length);
    }

    private static <E> Set<List<E>> variateWithRepetition(List<E> elements, int length) {
        int n = elements.size();
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
                variation.set(j, elements.get(remainder));
                remaining = (remaining - remainder) / n;
            }
            result.add(variation);
        }
        return result;
    }

    @Override
    public <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> result = variateWithoutRepetition(new HashSet<>(elements), length);
        return result.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        Set<List<E>> result = variateWithRepetition(new HashSet<>(elements), length);
        return result.stream()
                .map(list -> list.stream().collect(groupingBy(e -> e, summingInt(first -> 1))))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
