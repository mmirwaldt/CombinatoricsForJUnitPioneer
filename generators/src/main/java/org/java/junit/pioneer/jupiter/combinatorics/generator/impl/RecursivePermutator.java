package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;

public class RecursivePermutator<E> extends AbstractPermutator<E> {
    private Set<Integer> remainingPositions;
    private Map<E, Integer> remainingFrequencyMap;
    private Map<E, Set<Integer>> positionsMap;
    private Set<List<E>> results;

    // See the comment in permutateWithRepetition() in LoopingPermutator for the basic idea
    public Set<List<E>> permutate(Map<E, Integer> frequencyMap) {
        remainingPositions = allPositions;

        positionsMap = new LinkedHashMap<>();
        remainingFrequencyMap = new HashMap<>(frequencyMap);

        results = new HashSet<>();
        permutate();
        return results;
    }

    public void permutate() {
        if (remainingFrequencyMap.size() == 1) {
            addLastPositionsMap();
            results.add(generatePermutationFromPositionsMap(positionsMap));
        } else {
            addPositionsMaps();
        }
    }

    private void addPositionsMaps() {
        Map.Entry<E, Integer> frequencyEntry = getFirstEntry();
        int frequency = frequencyEntry.getValue();

        Set<Set<Integer>> positionsCombinations = loopingCombiner.combine(remainingPositions, frequency, false);

        for (Set<Integer> positionsCombination : positionsCombinations) {
            addPositionsMaps(positionsCombination, frequencyEntry);
        }
    }

    private void addPositionsMaps(Set<Integer> positionsCombination, Map.Entry<E, Integer> frequencyEntry) {
        E element = frequencyEntry.getKey();
        int frequency = frequencyEntry.getValue();

        remainingFrequencyMap.remove(element);
        positionsMap.put(element, new HashSet<>(positionsCombination));
        remainingPositions.removeAll(positionsCombination);
        permutate();
        remainingPositions.addAll(positionsCombination);
        positionsMap.remove(element);
        remainingFrequencyMap.put(element, frequency);
    }

    private void addLastPositionsMap() {
        Map.Entry<E, Integer> entry = getFirstEntry();
        E element = entry.getKey();
        positionsMap.put(element, new HashSet<>(remainingPositions));
    }

    private Map.Entry<E, Integer> getFirstEntry() {
        return remainingFrequencyMap.entrySet().iterator().next();
    }
}
