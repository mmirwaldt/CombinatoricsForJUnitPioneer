package org.java.junit.pioneer.jupiter.combinatorial.generators.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CombinatorialGenerator {
    /**
     * generates permutations without repetition.
     * Permutations care about the order, i.e. the permutations "AB" and "BA" are different,
     * and use all given elements in opposite to variations.
     * Permutations without repetition only use every given element only and exactly once.
     * E.g. given the elements "A", "B", "C", then "ABC" and "CAB" are permutations without repetition.
     * <p/>
     * The number of permutations without repetition can be calculated by factorial, i.e. n! = n*(n-1)*...*2*1.
     * E.g. given the elements "A", "B", "C", then 3! = 3*2*1 = 6 permutations without repetition exist.
     * <p/>
     * IMPORTANT: The number of permutations grows extremely fast!
     * E.g. 20!=2,432,902,008,176,640,000. That's huge if you consider Long.MAX_VALUE = 9,223,372,036,854,775,807.
     * Be careful with the number of given elements.
     * <p/>
     * @param elements the elements for the permutations
     * @return a set of the permutations
     * @param <E> the type of the elements
     */
    <E> Set<List<E>> permutateWithoutRepetition(Set<E> elements);

    /**
     * generates permutations with repetition.
     * Permutations care about the order, i.e. the permutations "AB" and "BA" are different,
     * and use all given elements in opposite to variations.
     * Permutations with repetition use the given elements with their given frequencies.
     * E.g. given the elements 2 "A"s, 3 "B"s, 1 "C", then "AABBBC" and "BACABB" are permutations with repetition.
     * <p/>
     * The number of permutations with repetition can be calculated by
     * n! / (a! * ... * z!), where a, ..., z are the frequencies for the given elements "A", ... , "Z" and where
     * n is the sum of all frequencies, i.e. n = a + ... + z.
     * E.g. given the elements 2 "A"s, 3 "B"s, 1 "C", then (2 + 3 + 1)! / (2! * 3! * 1!) = 6! / (2*1 * (3*2*1) * 1) =
     * = (6*5*4*3*2*1) / (2 * 6 * 1) = 720 / 12 = 60 permutations with repetition exist.
     * <p/>
     * IMPORTANT: The number of permutations grows extremely fast despite the denominator!
     * E.g. 20! / (3! * 2!) = 2,432,902,008,176,640,000 / (6 * 2) = 2,432,902,008,176,640,000 / 12 =
     * = 202,741,834,014,720,000. That's still huge if you consider Long.MAX_VALUE = 9,223,372,036,854,775,807.
     * Be careful with the number of given elements.
     * <p/>
     * @param elementsWithFrequencies a map with the elements as keys and their frequencies as values
     * @return a set of the permutations
     * @param <E> the type of the elements
     */
    <E> Set<List<E>> permutateWithRepetition(Map<E, Integer> elementsWithFrequencies);

    /**
     * generates variations without repetition.
     * Variations care about the order, i.e. the variations "AB" and "BA" are different,
     * and have a given length and can use all given elements, i.e. an element can be omitted, in opposite to permutations.
     * Variations without repetition only use every given element at most once.
     * E.g. given the elements "A", "B", "C" and a length of 2, then "AB" and "CA" are variations without repetition.
     * <p/>
     * The number of variations without repetition can be calculated by n! / (n - k)! = n * (n-1) * ... * (n - k + 1).
     * E.g. given the elements "A", "B", "C", "D", "E" and a length of 3, then 5! / 3! = 5 * 4 = 20 variations without repetition exist.
     * <p/>
     * IMPORTANT: The number of variations grows extremely fast!
     * E.g. 20!=2,432,902,008,176,640,000. That's huge if you consider Long.MAX_VALUE = 9,223,372,036,854,775,807.
     * Be careful with the number of given elements.
     * <p/>
     * @param elements the elements for the variations
     * @return a set of the variations
     * @param <E> the type of the elements
     */
    <E> Set<List<E>> variateWithoutRepetition(Set<E> elements, int length);

    /**
     * generates variations with repetition.
     * Variations care about the order, i.e. the variations "AB" and "BA" are different,
     * and have a given length and can use all given elements, i.e. an element can be omitted, in opposite to permutations.
     * Variations with repetition can use every element arbitrarily often.
     * E.g. given the elements "A", "B", "C" and a length of 2, then "AB", "AA", "CA" are variations with repetition.
     * <p/>
     * The number of variations with repetition can be calculated by n^k = n * ... * n with k factors of n.
     * E.g. given the elements "A", "B", "C" and a length of 2, then 3^2 = 9 variations with repetition exist.
     * <p/>
     * IMPORTANT: The number of variations grows extremely fast!
     * E.g. 20!=2,432,902,008,176,640,000. That's huge if you consider Long.MAX_VALUE = 9,223,372,036,854,775,807.
     * Be careful with the number of given elements.
     * <p/>
     * @param elements the elements for the variations
     * @return a set of the variations
     * @param <E> the type of the elements
     */
    <E> Set<List<E>> variateWithRepetition(Set<E> elements, int length);

    /**
     * generates combinations without repetition.
     * Combinations don't care about the order, i.e. the combinations "AB" and "BA" aren't different,
     * and have a given length and can use all given elements, i.e. an element can be omitted.
     * Combinations without repetition only use every given element at most once.
     * E.g. given the elements "A", "B", "C" and a length of 2, then "AB" and "AC" are combinations without repetition.
     * <p/>
     * The number of combinations without repetition can be calculated by using binomial coefficients.
     * We define binomial(n,k) = n! / ((n-k)! * k!).
     * If n is elements.size() and k is the length, then the number of combinations is binomial(n,k).
     * E.g. given the elements "A", "B", "C", "D", "E" and a length of 2, then 5! / ((5-2)! * 2!) = (5 * 4 * 3!) / (3! * 2!) =
     * = (5 * 4) / (2 * 1) = 20 / 2 = 10 combinations without repetition exist.
     * <p/>
     * IMPORTANT: The number of combinations grows extremely fast!
     * E.g. 20!=2,432,902,008,176,640,000. That's huge if you consider Long.MAX_VALUE = 9,223,372,036,854,775,807.
     * Be careful with the number of given elements.
     * <p/>
     * @param elements the elements for the combinations
     * @return a set of the combinations
     * @param <E> the type of the elements
     */
    <E> Set<Set<E>> combineWithoutRepetition(Set<E> elements, int length);

    /**
     * generates combinations with repetition.
     * Combinations don't care about the order, i.e. the combinations "AB" and "BA" aren't different,
     * and have a given length and can use all given elements, i.e. an element can be omitted.
     * Combinations with repetition can use every element arbitrarily often.
     * E.g. given the elements "A", "B", "C" and a length of 2, then "AB", "AA", "CA" are combinations with repetition.
     * <p/>
     * The number of combinations with repetition can be calculated by using binomial coefficients.
     * We define binomial(n,k) = n! / ((n-k)! * k!).
     * If n is elements.size() and k is length,
     * then the number of combinations with repetition is binomial(n + k - 1, k) = (n + k - 1)! / ((n-1)! * k!).
     * E.g. given the elements "A", "B", "C", "D", "E" and a length of 2, then (5 + 2 - 1)! / ((5-1)! * 2!) =
     * = 6! / (4! * 2!) = (6 * 5 * 4!) / (4! * 2!) = (6 * 5) / (2 * 1) = 30 / 2 = 15 combinations with repetition exist.
     * <p/>
     * IMPORTANT: The number of combinations grows extremely fast!
     * E.g. 20!=2,432,902,008,176,640,000. That's huge if you consider Long.MAX_VALUE = 9,223,372,036,854,775,807.
     * Be careful with the number of given elements.
     * <p/>
     * @param elements the elements for the combinations
     * @return a set of the combinations as maps where the element is the key and the value is the frequency
     * @param <E> the type of the elements
     */
    <E> Set<Map<E, Integer>> combineWithRepetition(Set<E> elements, int length);
}
