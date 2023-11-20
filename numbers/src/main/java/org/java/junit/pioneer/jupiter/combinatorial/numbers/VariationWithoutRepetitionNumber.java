package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.*;

import static java.util.Collections.nCopies;

public class VariationWithoutRepetitionNumber extends CombinatorialNumber {
    private final List<Integer> sortedUnusedDigitsForPlace;

    /**
     * stack for indices of sortedUnusedDigits.
     */
    private final Deque<Integer> indicesStack;

    public VariationWithoutRepetitionNumber(int base, int length) {
        super(base, length);
        checkParameters(base, length);
        initDigits(length);
        sortedUnusedDigitsForPlace = new ArrayList<>(base);
        indicesStack = new ArrayDeque<>(nCopies(length, 0));
        initUnusedDigitsForLastPlace(base, length);
    }

    private void initDigits(int length) {
        for (int i = 0; i < length; i++) {
            digits[i] = (byte) i;
        }
    }

    private void initUnusedDigitsForLastPlace(int base, int length) {
        for (int i = length; i < base; i++) {
            sortedUnusedDigitsForPlace.add(i);
        }
    }

    @Override
    public void increment() {
        nonCarryOver();
        carryOver();
    }

    private void nonCarryOver() {
        int nextNonCarryOverIndex = lastNonCarryOverIndex();
        reuseLastDigit();
        useNextDigitAt(nextNonCarryOverIndex);
    }

    private void carryOver() {
        for (int i = indicesStack.size(); i < digits.length; i++) {
            useNextDigitAt(0);
        }
    }

    private void useNextDigitAt(int nextIndex) {
        int unusedDigit = sortedUnusedDigitsForPlace.get(nextIndex);
        useDigit(unusedDigit);
        iterateForward(nextIndex);
    }

    private void iterateForward(int nextIndex) {
        indicesStack.push(nextIndex);
    }

    private void useDigit(int unusedDigit) {
        digits[indicesStack.size()] = (byte) unusedDigit;
        sortedUnusedDigitsForPlace.remove((Integer) unusedDigit);
    }

    private int lastNonCarryOverIndex() {
        int index = nextIndex();
        while (!hasUnusedDigitFor(index)) {
            if (canCarryOver()) {
                reuseLastDigit();
                index = nextIndex();
            } else {
                throw new ArithmeticException("Overflow of number with base " + base + "!");
            }
        }
        return index;
    }

    private boolean canCarryOver() {
        return !indicesStack.isEmpty();
    }

    private boolean hasUnusedDigitFor(int index) {
        return index <= sortedUnusedDigitsForPlace.size();
    }

    private int nextIndex() {
        return indicesStack.pop() + 1;
    }

    private void reuseLastDigit() {
        int oldDigit = digits[indicesStack.size()];
        int insertIndex = Collections.binarySearch(sortedUnusedDigitsForPlace, oldDigit);
        sortedUnusedDigitsForPlace.add((-insertIndex) - 1, oldDigit);
    }

    private static void checkParameters(int base, int length) {
        if (base < 2 || Byte.MAX_VALUE < base) {
            throw new IllegalArgumentException("The number of elements must lie in [1, " + Byte.MAX_VALUE + "]" +
                    " and may not be " + length + "!");
        }
        if (length < 1) {
            throw new IllegalArgumentException("The length must be positive, i.e. it must larger than 0, and not be " + length);
        }
        if (base < length) {
            throw new IllegalArgumentException("No variations without repetition possible for the length " + length
                    + " with " + base + " elements. The length must be smaller or equal than the number of elements");
        }
    }
}
