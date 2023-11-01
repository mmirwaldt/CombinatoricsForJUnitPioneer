package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatoricsGenerator;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class IterativeGenerator implements CombinatoricsGenerator {
    @Override
    public <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements) {
        return variateWithoutRepetition(elements, elements.size());
    }

    @Override
    public <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        return new IterativePermutator<E>().permutateWithRepetition(elementsWithFrequencies);
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        return IterativeVariator.variate(elements, length, false);
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        return IterativeVariator.variate(elements, length, true);
    }

    @Override
    public <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length) {
        Supplier<Set<E>> supplier = HashSet::new;
        Function<Set<E>, Set<E>> copyFactory = HashSet::new;
        BiConsumer<E, Set<E>> elementConsumer = (element, set) -> set.add(element);
        return new IterativeCombiner<>(supplier, copyFactory, elementConsumer)
                .combine(elements, length, false);
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        Supplier<Map<E, Integer>> supplier = HashMap::new;
        Function<Map<E, Integer>, Map<E, Integer>> copyFactory = HashMap::new;
        BiConsumer<E, Map<E, Integer>> elementConsumer = (element, map) ->
                map.compute(element, (key, value) -> (value == null) ? 1 : value + 1);;
        return new IterativeCombiner<>(supplier, copyFactory, elementConsumer)
                .combine(elements, length, true);
    }
}
