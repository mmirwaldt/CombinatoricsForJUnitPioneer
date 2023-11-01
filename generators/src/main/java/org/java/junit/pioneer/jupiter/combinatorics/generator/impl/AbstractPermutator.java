package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class AbstractPermutator<E> {
    protected final static IterativeCombiner<Integer, Set<Integer>> ITERATIVE_COMBINER = createCombiner();

    protected int length;
    protected Set<Integer> allPositions;

    public Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        length = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        allPositions = IntStream.range(0, length)
                .boxed()
                .collect(Collectors.toSet());
        return permutate(elementsWithFrequencies);
    }

    protected abstract Set<List<E>> permutate(Map<E, Integer> frequencyMap);

    private static IterativeCombiner<Integer, Set<Integer>> createCombiner() {
        Supplier<Set<Integer>> factory = HashSet::new;
        Function<Set<Integer>, Set<Integer>> copyFactory = HashSet::new;
        BiConsumer<Integer, Set<Integer>> elementConsumer = (e, set) -> set.add(e);
        return new IterativeCombiner<>(factory, copyFactory, elementConsumer);
    }

    protected List<E> generatePermutationFromPositionsMap(Map<E, Set<Integer>> positionsMap) {
        List<E> permutation = new ArrayList<>(Collections.nCopies(length, null));
        for (Map.Entry<E, Set<Integer>> positionEntry : positionsMap.entrySet()) {
            for (int position : positionEntry.getValue()) {
                permutation.set(position, positionEntry.getKey());
            }
        }
        return permutation;
    }
}
