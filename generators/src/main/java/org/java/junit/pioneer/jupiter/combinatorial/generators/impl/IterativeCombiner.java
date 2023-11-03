package org.java.junit.pioneer.jupiter.combinatorial.generators.impl;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

class IterativeCombiner<E, C> {
    private final Supplier<C> factory;
    private final Function<C, C> copyFactory;
    private final BiConsumer<E, C> elementConsumer;

    public IterativeCombiner(Supplier<C> factory, Function<C, C> copyFactory, BiConsumer<E, C> elementConsumer) {
        this.factory = factory;
        this.copyFactory = copyFactory;
        this.elementConsumer = elementConsumer;
    }

    public Set<C> combine(Set<E> elements, int length, boolean withRepetition) {
        List<E> elementsList = List.copyOf(elements);

        // don't use a HashSet here because the order of the elements must be same as newLastElements
        Set<C> result = new LinkedHashSet<>();

        List<E> lastElements = new ArrayList<>();

        // avoid the edge case of an empty list as result and no last element
        result.add(factory.get());
        lastElements.add(null);

        for (int i = 0; i < length; i++) {
            // don't use a HashSet here because the order of the elements must be same as in newLastElements
            Set<C> newResult = new LinkedHashSet<>();

            List<E> newLastElements = new ArrayList<>();
            Iterator<E> lastElementsIterator = lastElements.iterator();
            for (C combination : result) {
                E lastElement = lastElementsIterator.next();
                List<E> unusedElements = unusedInCombination(elementsList, lastElement, withRepetition);
                for (E element : unusedElements) {
                    C nextCombination = copyFactory.apply(combination);
                    elementConsumer.accept(element, nextCombination);
                    newResult.add(nextCombination);
                    newLastElements.add(element);
                }
            }
            result = newResult;
            lastElements = newLastElements;
        }
        return result;
    }

    private static <E> List<E> unusedInCombination(List<E> elements, E lastElement, boolean withRepetition) {
        if (lastElement == null) {
            return elements;
        } else {
            int start = elements.indexOf(lastElement);
            if (withRepetition) {
                return elements.subList(start, elements.size());
            } else {
                return elements.subList(start + 1, elements.size());
            }
        }
    }
}
