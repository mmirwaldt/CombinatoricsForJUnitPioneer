package org.java.junit.pioneer.jupiter.combinatorics.generator.impl;

import java.util.*;

public class LoopingPermutator<E> extends AbstractPermutator<E> {
    private E element;
    private int frequency;

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
    public Set<List<E>> permutate(Map<E, Integer> elementsWithFrequencies) {
        /*
        Each element of the list is a permutation represented by a map with the elements as keys and the positions as elements.
         */
        List<Map<E, Set<Integer>>> permutationMaps = generatePermutationMaps(elementsWithFrequencies);

        return generatePermutationsFromMaps(permutationMaps);
    }

    private List<Map<E, Set<Integer>>> generatePermutationMaps(Map<E, Integer> elementsWithFrequencies) {
        List<Map<E, Set<Integer>>> permutationMaps = new ArrayList<>();
        permutationMaps.add(new LinkedHashMap<>()); // avoids the edge case of an empty list

        for (Map.Entry<E, Integer> entry : elementsWithFrequencies.entrySet()) {
            element = entry.getKey();
            frequency = entry.getValue();
            permutationMaps = createPermutationMapsForElement(permutationMaps);
        }

        return permutationMaps;
    }

    private List<Map<E, Set<Integer>>> createPermutationMapsForElement(List<Map<E, Set<Integer>>> permutationMaps) {
        List<Map<E, Set<Integer>>> newPermutationMaps = new ArrayList<>();
        for (Map<E, Set<Integer>> permutationMap : permutationMaps) {
            Set<Integer> unusedPositions = unusedPositionsInPermutation(permutationMap, allPositions);
            Set<Set<Integer>> combinations =
                    loopingCombiner.combine(unusedPositions, frequency, false);

            for (Set<Integer> combination : combinations) {
                Map<E, Set<Integer>> newPermutationMap =
                        createNewPermutationMap(permutationMap, combination, element);
                newPermutationMaps.add(newPermutationMap);
            }
        }
        return newPermutationMaps;
    }

    private Map<E, Set<Integer>> createNewPermutationMap(Map<E, Set<Integer>> map, Set<Integer> combination, E element) {
        Map<E, Set<Integer>> permutationMap = new LinkedHashMap<>(map);
        permutationMap.put(element, combination);
        return permutationMap;
    }

    private Set<List<E>> generatePermutationsFromMaps(List<Map<E, Set<Integer>>> permutationMaps) {
        Set<List<E>> result = new HashSet<>();
        for (Map<E, Set<Integer>> permutationMap : permutationMaps) {
            result.add(generatePermutationFromMap(permutationMap));
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
