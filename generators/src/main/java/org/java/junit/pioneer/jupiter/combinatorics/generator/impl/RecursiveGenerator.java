package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatoricsGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecursiveGenerator implements CombinatoricsGenerator {
    @Override
    public <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements) {
        return variateWithoutRepetition(elements, elements.size());
    }

    @Override
    public <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        return new RecursivePermutator<E>().permutateWithRepetition(elementsWithFrequencies);
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> result = new HashSet<>();
        RecursiveVariator.variateWithoutRepetition(elements, length, new ArrayDeque<>(), result);
        return result;
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        Set<List<E>> result = new HashSet<>();
        RecursiveVariator.variateWithRepetition(elements, length, new ArrayDeque<>(), result);
        return result;
    }

    @Override
    public <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length) {
        Set<Set<E>> result = new HashSet<>();
        RecursiveCombiner.combineWithoutRepetition(List.copyOf(elements), length, new ArrayDeque<>(), result);
        return result;
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        Set<Map<E, Integer>> result = new HashSet<>();
        RecursiveCombiner.combineWithRepetition(List.copyOf(elements), length, new HashMap<>(), 0, result);
        return result;
    }
}
