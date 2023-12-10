package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.BitSet;

public class VariationWithoutRepetitionNumber extends CombinatorialNumber {
    private final BitSet unusedDigits;
    private final byte[] placesStack;
    private int stackPointer;

    public VariationWithoutRepetitionNumber(int base, int length) {
        super(base, length);
        checkLength(base, length);
        initDigits(length);
        unusedDigits = new BitSet(base);
        unusedDigits.set(length, base);
        placesStack = new byte[length];
        stackPointer = length - 1;
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
        for (int i = stackSize(); i < digits.length; i++) {
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
            if(unusedDigits.get(i)) {
                ones++;
            }
            if(ones == nextIndex + 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Cannot find a 1 for index " + nextIndex);
    }

    private void iterateForward(int nextIndex) {
        push(nextIndex);
    }

    private void useDigit(int unusedDigit) {
        digits[stackSize()] = (byte) unusedDigit;
        unusedDigits.clear(unusedDigit);
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
        return !isStackEmpty();
    }

    private boolean hasUnusedDigitFor(int index) {
        return index <= unusedDigits.cardinality();
    }

    private int nextIndex() {
        return pop() + 1;
    }

    private void reuseLastDigit() {
        int oldDigit = digits[stackSize()];
        unusedDigits.set(oldDigit);
    }

    private int stackSize() {
        return stackPointer + 1;
    }

    private boolean isStackEmpty() {
        return stackPointer == -1;
    }

    private int pop() {
        return placesStack[stackPointer--];
    }

    private void push(int i) {
        placesStack[++stackPointer] = (byte) i;
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
