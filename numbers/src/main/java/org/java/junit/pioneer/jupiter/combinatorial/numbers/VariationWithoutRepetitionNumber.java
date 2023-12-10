package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.BitSet;

public class VariationWithoutRepetitionNumber extends CombinatorialNumber {
    private final BitSet unusedDigits;
    private final byte[] unusedDigitsIndices;
    private int stackPointer;

    public VariationWithoutRepetitionNumber(int base, int length) {
        super(base, length);
        checkLength(base, length);
        initDigits(length);
        unusedDigits = new BitSet(base);
        unusedDigits.set(length, base);
        unusedDigitsIndices = new byte[length];
        stackPointer = length - 1;
    }

    private void initDigits(int length) {
        for (int i = 0; i < length; i++) {
            digits[i] = (byte) i;
        }
    }

    @Override
    public void increment() {
        int firstNextUnusedDigitIndex = clearPlacesWithNoNextUnusedDigit();
        setNextDigitForFirstPlace(firstNextUnusedDigitIndex);
        setNextDigitsForNextPlaces();
    }

    private void setNextDigitForFirstPlace(int nextUnusedDigitIndex) {
        int oldDigit = digits[currentPlace()];
        unusedDigits.set(oldDigit);
        int newDigit = nextUnusedDigit(nextUnusedDigitIndex);
        digits[currentPlace()] = (byte) newDigit;
        unusedDigits.clear(newDigit);
        pushUnusedDigitIndex(nextUnusedDigitIndex);
    }

    private void setNextDigitsForNextPlaces() {
        for (int i = currentPlace(); i < digits.length; i++) {
            int firstUnusedDigit = nextUnusedDigit(0);
            digits[currentPlace()] = (byte) firstUnusedDigit;
            unusedDigits.clear(firstUnusedDigit);
            pushUnusedDigitIndex(0);
        }
    }

    private int clearPlacesWithNoNextUnusedDigit() {
        int nextUnusedDigitIndex = popUnusedDigitIndex() + 1;
        while (hasNoNextUnusedDigit(nextUnusedDigitIndex)) {
            if (canCarryOver()) {
                int oldDigit = digits[currentPlace()];
                unusedDigits.set(oldDigit);
                nextUnusedDigitIndex = popUnusedDigitIndex() + 1;
            } else {
                throw new ArithmeticException("Overflow of number with base " + base + "!");
            }
        }
        return nextUnusedDigitIndex;
    }

    private boolean canCarryOver() {
        return isPlace(previousPlace());
    }

    private boolean hasNoNextUnusedDigit(int nextUnusedDigitIndex) {
        return maxUnusedDigits() < nextUnusedDigitIndex;
    }

    private int previousPlace() {
        return stackPointer;
    }

    private int currentPlace() {
        return stackPointer + 1;
    }

    private boolean isPlace(int place) {
        return 0 <= place;
    }

    private int nextUnusedDigit(int nextIndex) {
        int ones = 0;
        for (int i = unusedDigits.nextSetBit(0); 0 <= i && i < base; ) {
            ones++;
            if(ones == nextIndex + 1) {
                return i;
            }
            i = unusedDigits.nextSetBit(i + 1);
        }
        throw new IllegalArgumentException("Cannot find a 1 for index " + nextIndex);
    }

    private int maxUnusedDigits() {
        return unusedDigits.cardinality();
    }

    private int popUnusedDigitIndex() {
        return unusedDigitsIndices[stackPointer--];
    }

    private void pushUnusedDigitIndex(int i) {
        unusedDigitsIndices[++stackPointer] = (byte) i;
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
