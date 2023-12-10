package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.*;

import static java.util.Collections.nCopies;

public class VariationWithoutRepetitionNumber extends CombinatorialNumber {
    private final BitSet sortedUnusedDigitsForPlace;

    /**
     * stack for indices of sortedUnusedDigits.
     */
    private final Deque<Integer> indicesStack;

    public VariationWithoutRepetitionNumber(int base, int length) {
        super(base, length);
        checkLength(base, length);
        initDigits(length);
        sortedUnusedDigitsForPlace = new BitSet(base);
        sortedUnusedDigitsForPlace.set(length, base);
        indicesStack = new ArrayDeque<>(nCopies(length, 0));
    }

    private void initDigits(int length) {
        for (int i = 0; i < length; i++) {
            digits[i] = (byte) i;
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
        int unusedDigit = unusedDigitAt(nextIndex);
        useDigit(unusedDigit);
        iterateForward(nextIndex);
    }

    private int unusedDigitAt(int nextIndex) {
        int ones = 0;
        for (int i = 0; i < base; i++) {
            if(sortedUnusedDigitsForPlace.get(i)) {
                ones++;
            }
            if(ones == nextIndex + 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Cannot find a 1 for index " + nextIndex);
    }

    private void iterateForward(int nextIndex) {
        indicesStack.push(nextIndex);
    }

    private void useDigit(int unusedDigit) {
        digits[indicesStack.size()] = (byte) unusedDigit;
        sortedUnusedDigitsForPlace.clear(unusedDigit);
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
        return index <= sortedUnusedDigitsForPlace.cardinality();
    }

    private int nextIndex() {
        return indicesStack.pop() + 1;
    }

    private void reuseLastDigit() {
        int oldDigit = digits[indicesStack.size()];
        sortedUnusedDigitsForPlace.set(oldDigit);
    }

    private static void checkLength(int base, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("The length must be positive, i.e. it must larger than 0, and not be " + length);
        }
        if (base < length) {
            throw new IllegalArgumentException("No variations without repetition possible for the length " + length
                    + " with " + base + " elements. The length must be smaller or equal than the number of elements");
        }
    }
}
