package org.java.junit.pioneer.jupiter.combinatorial.numbers;

public class CombinationWithRepetitionNumber extends AbstractCombinatorialNumber<CombinationWithRepetitionNumber> {
    public CombinationWithRepetitionNumber(int base, int length) {
        super(base, length);
    }

    @Override
    public void increment() {
        for (int i = digits.length - 1; 0 <= i ; i--) {
            int increment = digits[i] + 1;
            if(increment < base) {
                digits[i]++;
                return;
            }
        }
        throw new ArithmeticException("Overflow of number with base " + base + "!");
    }

    @Override
    public boolean isMax() {
        return isMax(true);
    }
}
