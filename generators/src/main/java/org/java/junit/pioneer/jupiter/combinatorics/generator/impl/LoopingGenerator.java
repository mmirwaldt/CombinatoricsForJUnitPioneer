package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import org.java.junit.pioneer.jupiter.combinatorics.generator.api.CombinatoricsGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoopingGenerator implements CombinatoricsGenerator {
    @Override
    public <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements) {
        return variateWithoutRepetition(elements, elements.size());
    }

    @Override
    public <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies) {
        /*
        The basic idea behind this algorithm here is to use combinations to avoid duplicates.
        How?
        Given the elements "B", ... , "Z" with the frequencies b, ... z with n = b + ... + z
        and binomial(n,k) = n! / ((n-k)! * k!), then we can use a list L = [0, ..., n - 1] and pick out first
        b positions out of n positions in L for the "B"s. If LB is combination, i.e. a list with b positions of list L,
        then we can pick out c positions out of the remaining n-b positions in the remaining list LC = L \ LB,
        i.e. list LC is the list L without all elements of LB. We can continue with D until Z.
        (I didn't use "a" as a variable name because it confuses with the indefinite article "a").

        E.g. if the given elements are 2 "A"s, 3 "B"s, 2 "C"s, then n = 2 + 3 + 2 = 7 and L = [0, 1, 2, 3, 4, 5, 6].
        We can pick out 2 positions for the 2 "A"s as a combination. That can be LA1 = [0, 2] or LA2 = [4, 5] or something other.
        Let's take LA1 = [0, 2] as combination for the 2 "A"s. Then LB = [0, 1, 2, 3, 4, 5, 6] \ [0, 2] = [1, 3, 4, 5, 6].
        We can pick out 3 positions from it for the 3 "B"s. That can be LB1 = [1, 3, 5] or LB2 = [1, 5, 6] or something other.
        Let's take LB1 = [1, 3, 5] as combination for the 3 "B"s. Then only LC = [4, 6] remain for the 2 "C"s.
        The permutation with repetition of LA1, LB1 and LC is "ABABCBC" if you replace the positions by the elements,
        i.e. L = [0, 1, 2, 3, 4, 5, 6] becomes ["A", 1, "A", 3, 4, 5, 6] for LA1 = [0, 2],
        ["A", "B", "A", "B", 4, "B", 6] for LB1 = [1, 3, 5]  and finally ["A", "B", "A", "B", "C", "B", ""C"] for LC = [4, 6]
        which leads to "ABABCBC".
         */
        int length = elementsWithFrequencies.values().stream().mapToInt(i -> i).sum();
        List<Map<E, Set<Integer>>> permutationMaps = generatePermutationMaps(elementsWithFrequencies, length);
        return generatePermutations(length, permutationMaps);
    }

    @Override
    public <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length) {
        return variate(elements, length, false);
    }

    @Override
    public <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length) {
        return variate(elements, length, true);
    }

    @Override
    public <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length) {
        List<E> elementsList = List.copyOf(elements);

        // don't use a HashSet here because the order of the elements must be same as newLastElements
        Set<Set<E>> result = new LinkedHashSet<>();

        List<E> lastElements = new ArrayList<>();

        // avoid the edge case of an empty list as result and no last element
        result.add(Collections.emptySet());
        lastElements.add(null);

        for (int i = 0; i < length; i++) {
            // don't use a HashSet here because the order of the elements must be same as newLastElements
            Set<Set<E>> newResult = new LinkedHashSet<>();

            List<E> newLastElements = new ArrayList<>();
            Iterator<E> lastElementsIterator = lastElements.iterator();
            for (Set<E> combination : result) {
                E lastElement = lastElementsIterator.next();
                List<E> unusedElements = unusedInCombination(elementsList, lastElement, false);
                for (E element : unusedElements) {
                    Set<E> nextCombination = new HashSet<>(combination);
                    nextCombination.add(element);
                    newResult.add(nextCombination);
                    newLastElements.add(element);
                }
            }
            result = newResult;
            lastElements = newLastElements;
        }
        return result;
    }

    @Override
    public <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length) {
        List<E> elementsList = List.copyOf(elements);

        // don't use a HashSet here because the order of the elements must be same as newLastElements
        Set<Map<E, Integer>> result = new LinkedHashSet<>();

        List<E> lastElements = new ArrayList<>();

        // avoid the edge case of an empty list as result and no last element
        result.add(Collections.emptyMap());
        lastElements.add(null);

        for (int i = 0; i < length; i++) {
            // don't use a HashSet here because the order of the elements must be same as newLastElements
            Set<Map<E, Integer>> newResult = new LinkedHashSet<>();

            List<E> newLastElements = new ArrayList<>();
            Iterator<E> lastElementsIterator = lastElements.iterator();
            for (Map<E, Integer> combination : result) {
                E lastElement = lastElementsIterator.next();
                List<E> unusedElements = unusedInCombination(elementsList, lastElement, true);
                for (E element : unusedElements) {
                    Map<E, Integer> nextCombination = new HashMap<>(combination);
                    nextCombination.compute(element, (key, value) -> (value == null) ? 1 : value + 1);
                    newResult.add(nextCombination);
                    newLastElements.add(element);
                }
            }
            result = newResult;
            lastElements = newLastElements;
        }
        return result;
    }

    private <E> Set<List<E>> variate(Set<E> elements, int k, boolean withRepetition) {
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

    private static <E> Set<E> unusedInVariation(Set<E> elements, List<E> usedElements, boolean withRepetition) {
        if (withRepetition) {
            return elements;
        } else {
            Set<E> unused = new HashSet<>(elements);
            usedElements.forEach(unused::remove);
            return unused;
        }
    }

    private <E> List<Map<E, Set<Integer>>> generatePermutationMaps(Map<E, Integer> elementsWithFrequencies, int length) {
        Set<Integer> allPositions = IntStream.range(0, length).boxed().collect(Collectors.toSet());
        List<Map<E, Set<Integer>>> permutationMaps = new ArrayList<>();
        permutationMaps.add(new LinkedHashMap<>()); // avoids the edge case of an empty list
        for (Map.Entry<E, Integer> entry : elementsWithFrequencies.entrySet()) {
            E element = entry.getKey();
            int frequency = entry.getValue();
            List<Map<E, Set<Integer>>> newPermutationMaps = new ArrayList<>();
            for (Map<E, Set<Integer>> map : permutationMaps) {
                Set<Integer> unusedPositions = unusedPositionsInPermutation(map, allPositions);
                Set<Set<Integer>> combinations = combineWithoutRepetition(unusedPositions, frequency);
                for (Set<Integer> combination : combinations) {
                    Map<E, Set<Integer>> permutationMap = new LinkedHashMap<>(map);
                    permutationMap.put(element, combination);
                    newPermutationMaps.add(permutationMap);
                }
            }
            permutationMaps = newPermutationMaps;
        }
        return permutationMaps;
    }

    private static <E> Set<List<E>> generatePermutations(int length, List<Map<E, Set<Integer>>> permutationMaps) {
        List<E> permutation = new ArrayList<>(Collections.nCopies(length, null));
        Set<List<E>> result = new HashSet<>();
        for (Map<E, Set<Integer>> permutationMap : permutationMaps) {
            List<E> newPermutation = new ArrayList<>(permutation);
            for (Map.Entry<E, Set<Integer>> permutationEntry : permutationMap.entrySet()) {
                for (int position : permutationEntry.getValue()) {
                    newPermutation.set(position, permutationEntry.getKey());
                }
            }
            result.add(newPermutation);
        }
        return result;
    }

    private static <E> Set<Integer> unusedPositionsInPermutation(Map<E, Set<Integer>> map, Set<Integer> allPositions) {
        Set<Integer> unusedPositions = new HashSet<>(allPositions);
        for (Map.Entry<E, Set<Integer>> mapEntry : map.entrySet()) {
            unusedPositions.removeAll(mapEntry.getValue());
        }
        return unusedPositions;
    }
}
