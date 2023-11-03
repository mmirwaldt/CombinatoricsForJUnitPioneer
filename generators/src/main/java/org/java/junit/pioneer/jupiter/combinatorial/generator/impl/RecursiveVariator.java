package org.java.junit.pioneer.jupiter.combinatorial.generator.impl;

import java.util.*;

class RecursiveVariator {
    public static <E> void variateWithRepetition(Set<E> elements, int k, Deque<E> variation, Set<List<E>> results) {
        if (variation.size() == k) {
            results.add(new ArrayList<>(variation));
        } else {
            for (E element : elements) {
                variation.push(element);
                variateWithRepetition(elements, k, variation, results);
                variation.pop();
            }
        }
    }

    public static <E> void variateWithoutRepetition(Set<E> elements, int k, Deque<E> variation, Set<List<E>> results) {
        if (variation.size() == k) {
            results.add(new ArrayList<>(variation));
        } else {
            Set<E> remainingElements = new HashSet<>(elements);
            for (E element : elements) {
                remainingElements.remove(element);
                variation.push(element);
                variateWithoutRepetition(remainingElements, k, variation, results);
                variation.pop();
                remainingElements.add(element);
            }
        }
    }
}
