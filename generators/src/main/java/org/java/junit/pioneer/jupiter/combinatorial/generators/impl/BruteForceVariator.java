package org.java.junit.pioneer.jupiter.combinatorial.generators.impl;

import java.util.*;

import static java.lang.Math.multiplyExact;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class BruteForceVariator {
    public static <E> Set<List<E>> variate(Collection<E> elements, int length) {
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

    public static <E> boolean hasRepetition(List<E> variation) {
        return variation.size() != new HashSet<>(variation).size();
    }

    public static <E> Map<E, Integer> toFrequencyMap(List<E> list) {
        return list.stream().collect(groupingBy(e -> e, summingInt(first -> 1)));
    }
}
