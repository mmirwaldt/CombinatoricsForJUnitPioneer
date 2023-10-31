package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class AbstractPermutator<E> {
    protected final static LoopingCombiner<Integer, Set<Integer>> loopingCombiner = createCombiner();

    protected int length;
    protected Set<Integer> allPositions;

    public Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        length = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        allPositions = IntStream.range(0, length)
                .boxed()
                .collect(Collectors.toSet());
        return permutate(elementsWithFrequencies);
    }

    protected abstract Set<List<E>> permutate(Map<E, Integer> elementsWithFrequencies);

    private static LoopingCombiner<Integer, Set<Integer>> createCombiner() {
        Supplier<Set<Integer>> supplier = HashSet::new;
        Function<Set<Integer>, Set<Integer>> copyFactory = HashSet::new;
        BiConsumer<Integer, Set<Integer>> elementConsumer = (e, set) -> set.add(e);
        return new LoopingCombiner<>(supplier, copyFactory, elementConsumer);
    }

    protected List<E> generatePermutationFromMap(Map<E, Set<Integer>> positionsOfElements) {
        List<E> permutation = new ArrayList<>(Collections.nCopies(length, null));
        for (Map.Entry<E, Set<Integer>> positionEntry : positionsOfElements.entrySet()) {
            for (int position : positionEntry.getValue()) {
                permutation.set(position, positionEntry.getKey());
            }
        }
        return permutation;
    }
}
