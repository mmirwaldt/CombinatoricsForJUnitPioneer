package org.java.junit.pioneer.jupiter.combinatorial.numbers;

public class VariationWithoutRepetitionNumber extends CombinatorialNumber {
    public VariationWithoutRepetitionNumber(int base, int length) {
        super(base, length);
        for (int i = 0; i < length; i++) {
            digits[i] = (byte) i;
        }
    }

    @Override
    public void increment() {
        int increment = digits[0] + 1;
        if(increment < base) {
            digits[0]++;
        } else {
            throw new ArithmeticException("Overflow of number with base " + base + "!");
        }
    }
}
