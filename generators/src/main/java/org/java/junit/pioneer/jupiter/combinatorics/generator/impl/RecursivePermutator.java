package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecursivePermutator<E> {
    protected int length;
    protected Set<Integer> allPositions;
    private Set<Integer> remainingPositions;
    private Map<E, Integer> remainingFrequencyMap;
    private Map<E, Set<Integer>> positionsMap;
    private Set<List<E>> results;

    // See the comment in permutateWithRepetition() in IterativePermutator for the basic idea
    public Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        length = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        allPositions = IntStream.range(0, length)
                .boxed()
                .collect(Collectors.toSet());
        remainingPositions = allPositions;

        positionsMap = new LinkedHashMap<>();
        remainingFrequencyMap = new HashMap<>(elementsWithFrequencies);

        results = new HashSet<>();
        permutateRecursive();
        return results;
    }

    @SuppressWarnings("DuplicatedCode")
    public void permutateRecursive() {
        if (remainingFrequencyMap.size() == 1) {
            addLastPositionsMap();
            List<E> permutation = new ArrayList<>(Collections.nCopies(length, null));
            for (Map.Entry<E, Set<Integer>> positionEntry : positionsMap.entrySet()) {
                for (int position : positionEntry.getValue()) {
                    permutation.set(position, positionEntry.getKey());
                }
            }
            results.add(permutation);
        } else {
            addPositionsMaps();
        }
    }

    private void addPositionsMaps() {
        Map.Entry<E, Integer> frequencyEntry = getFirstEntry();
        int frequency = frequencyEntry.getValue();

        Set<Set<Integer>> positionsCombinations = RecursiveCombiner.combineWithoutRepetition(remainingPositions, frequency);

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
        permutateRecursive();
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
