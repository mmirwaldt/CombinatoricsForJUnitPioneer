package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;

public class RecursivePermutator<E> extends AbstractPermutator<E> {
    private Set<Integer> remainingPositions;
    private Map<E, Integer> remainingElements;
    private Map<E, Set<Integer>> permutationMap;
    private Set<List<E>> results;

    // See the comment in permutateWithRepetition() in LoopingPermutator for the basic idea
    public Set<List<E>> permutate(Map<E, Integer> elementsWithFrequencies) {
        remainingPositions = allPositions;

        permutationMap = new LinkedHashMap<>();
        remainingElements = new HashMap<>(elementsWithFrequencies);

        results = new HashSet<>();
        permutate();
        return results;
    }

    public void permutate() {
        if (remainingElements.size() == 1) {
            addLastPermutationMap();
            results.add(generatePermutationFromMap(permutationMap));
        } else {
            addPermutationMaps();
        }
    }

    private void addPermutationMaps() {
        Map.Entry<E, Integer> entry = getFirstEntry();
        int frequency = entry.getValue();

        Set<Set<Integer>> combinations = loopingCombiner.combine(remainingPositions, frequency, false);

        for (Set<Integer> combination : combinations) {
            addPermutationMapsForCombination(combination, entry);
        }
    }

    private void addPermutationMapsForCombination(Set<Integer> combination, Map.Entry<E, Integer> entry) {
        E element = entry.getKey();
        int frequency = entry.getValue();

        remainingElements.remove(element);
        permutationMap.put(element, new HashSet<>(combination));
        remainingPositions.removeAll(combination);
        permutate();
        remainingPositions.addAll(combination);
        permutationMap.remove(element);
        remainingElements.put(element, frequency);
    }

    private void addLastPermutationMap() {
        Map.Entry<E, Integer> entry = getFirstEntry();
        E element = entry.getKey();
        permutationMap.put(element, new HashSet<>(remainingPositions));
    }

    private Map.Entry<E, Integer> getFirstEntry() {
        return remainingElements.entrySet().iterator().next();
    }
}
