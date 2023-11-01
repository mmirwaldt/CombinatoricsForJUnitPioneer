package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatorialGenerator;

import java.util.*;

public class RecursiveGenerator implements CombinatorialGenerator {
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
        return RecursiveCombiner.combineWithoutRepetition(elements, length);
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        return RecursiveCombiner.combineWithRepetition(elements, length);
    }
}
