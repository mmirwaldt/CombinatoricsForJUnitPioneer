package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;

public class LoopingVariator {
    public static <E> Set<List<E>> variate(Set<E> elements, int k, boolean withRepetition) {
        Set<List<E>> result = new HashSet<>();
        result.add(Collections.emptyList()); // avoid the edge case of an empty list as result
        for (int i = 0; i < k; i++) {
            Set<List<E>> newResult = new HashSet<>();
            for (List<E> variation : result) {
                Set<E> unusedElements = unusedInVariation(elements, variation, withRepetition);
                for (E element : unusedElements) {
                    List<E> variationOfVariation = new ArrayList<>(variation);
                    variationOfVariation.add(element);
                    newResult.add(variationOfVariation);
                }
            }
            result = newResult;
        }
        return result;
    }

    private static <E> Set<E> unusedInVariation(Set<E> elements, List<E> usedElements, boolean withRepetition) {
        if (withRepetition) {
            return elements;
        } else {
            Set<E> unused = new HashSet<>(elements);
            usedElements.forEach(unused::remove);
            return unused;
        }
    }
}
