package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;

public class RecursiveCombiner {
    public static <E> void combineWithRepetition(List<E> elements, int k, Map<E, Integer> combination, int size, Set<Map<E, Integer>> results) {
        if (size == k) {
            results.add(new HashMap<>(combination));
        } else {
            int start = 0;
            for (E element : elements) {
                List<E> remainingElements = elements.subList(start, elements.size());
                combination.compute(element, (key, value) -> (value == null) ? 1 : value + 1);
                combineWithRepetition(remainingElements, k, combination, size + 1, results);
                combination.compute(element, (key, value) -> (value == null || value == 1) ? null : value - 1);
                start++;
            }
        }
    }

    public static <E> void combineWithoutRepetition(List<E> elements, int k, Deque<E> variation, Set<Set<E>> results) {
        if (variation.size() == k) {
            results.add(new HashSet<>(variation));
        } else {
            int start = 0;
            for (E element : elements) {
                List<E> remainingElements = elements.subList(start + 1, elements.size());
                variation.push(element);
                combineWithoutRepetition(remainingElements, k, variation, results);
                variation.pop();
                start++;
            }
        }
    }
}
