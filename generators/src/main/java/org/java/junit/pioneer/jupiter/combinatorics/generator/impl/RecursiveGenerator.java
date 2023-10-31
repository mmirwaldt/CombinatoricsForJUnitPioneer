package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatoricsGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecursiveGenerator implements CombinatoricsGenerator {
    @Override
    public <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements) {
        return variateWithoutRepetition(elements, elements.size());
    }

    @Override
    public <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        // See the comment in permutateWithRepetition() in LoopingPermutator for the basic idea
        Set<List<E>> result = new HashSet<>();
        int length = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        Set<Integer> allPositions = IntStream.range(0, length)
                .boxed()
                .collect(Collectors.toSet());
        permutateWithRepetition(new ArrayList<>(elementsWithFrequencies.entrySet()),
                allPositions.size(), allPositions, new LinkedHashMap<>(), result);
        return result;
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        Set<List<E>> result = new HashSet<>();
        variateWithoutRepetition(elements, length, new ArrayDeque<>(), result);
        return result;
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        Set<List<E>> result = new HashSet<>();
        variateWithRepetition(elements, length, new ArrayDeque<>(), result);
        return result;
    }

    @Override
    public <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length) {
        Set<Set<E>> result = new HashSet<>();
        combineWithoutRepetition(List.copyOf(elements), length, new ArrayDeque<>(), result);
        return result;
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        Set<Map<E, Integer>> result = new HashSet<>();
        combineWithRepetition(List.copyOf(elements), length, new HashMap<>(), 0, result);
        return result;
    }

    public <E> void variateWithRepetition(Set<E> elements, int k, Deque<E> variation, Set<List<E>> results) {
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

    public <E> void variateWithoutRepetition(Set<E> elements, int k, Deque<E> variation, Set<List<E>> results) {
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

    public <E> void combineWithRepetition(List<E> elements, int k, Map<E, Integer> combination, int size, Set<Map<E, Integer>> results) {
        if (size == k) {
            results.add(new HashMap<>(combination));
        } else {
            int start = 0;
            for (E element : elements) {
                List<E> remainingElements = elements.subList(start, elements.size());
                combination.compute(element, (key, value) -> (value == null) ? 1 : value + 1);
                combineWithRepetition(remainingElements, k, combination, size + 1, results);
                combination.compute(element, (key, value) -> (value == null || value == 1) ? null : value - 1);
                start++;
            }
        }
    }

    public <E> void combineWithoutRepetition(List<E> elements, int k, Deque<E> variation, Set<Set<E>> results) {
        if (variation.size() == k) {
            results.add(new HashSet<>(variation));
        } else {
            int start = 0;
            for (E element : elements) {
                List<E> remainingElements = elements.subList(start + 1, elements.size());
                variation.push(element);
                combineWithoutRepetition(remainingElements, k, variation, results);
                variation.pop();
                start++;
            }
        }
    }

    public <E> void permutateWithRepetition(
            List<Map.Entry<E, Integer>> entries,
            int length,
            Set<Integer> remainingPositions,
            Map<E, Set<Integer>> positionsOfElements,
            Set<List<E>> results) {
        if (entries.isEmpty()) {
            List<E> permutation = new ArrayList<>(Collections.nCopies(length, null));
            for (Map.Entry<E, Set<Integer>> positionEntry : positionsOfElements.entrySet()) {
                for (int position : positionEntry.getValue()) {
                    permutation.set(position, positionEntry.getKey());
                }
            }
            results.add(permutation);
        } else if (entries.size() == 1) {
            positionsOfElements.put(entries.get(0).getKey(), new HashSet<>(remainingPositions));
            permutateWithRepetition(Collections.emptyList(), length, remainingPositions, positionsOfElements, results);
        } else {
            Map.Entry<E, Integer> entry = entries.get(0);
            Set<Set<Integer>> combinations =
                    combineWithoutRepetition(remainingPositions, entry.getValue());
            for (Set<Integer> combination : combinations) {
                positionsOfElements.put(entry.getKey(), new HashSet<>(combination));
                remainingPositions.removeAll(combination);
                permutateWithRepetition(entries.subList(1, entries.size()), length, remainingPositions, positionsOfElements, results);
                remainingPositions.addAll(combination);
                positionsOfElements.remove(entry.getKey());
            }
        }
    }
}
