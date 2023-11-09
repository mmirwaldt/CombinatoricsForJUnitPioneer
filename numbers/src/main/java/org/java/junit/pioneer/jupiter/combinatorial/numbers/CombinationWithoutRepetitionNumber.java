package org.java.junit.pioneer.jupiter.combinatorial.numbers;

public class CombinationWithoutRepetitionNumber extends CombinatorialNumber {
    public CombinationWithoutRepetitionNumber(int base, int length) {
        super(base, length);
        for (int i = 0; i < length; i++) {
            digits[i] = (byte) i;
        }
    }

    @Override
    public void increment() {
        int last = digits.length - 1;
        for (int i = last; 0 <= i; i--) {
            int increment = digits[i] + 1;
            int upperBound = (i < last) ? digits[i + 1] : base;
            if (increment < upperBound) {
                digits[i]++;
                return;
            }
        }
        throw new ArithmeticException("Overflow of number with base " + base + "!");
    }
}
