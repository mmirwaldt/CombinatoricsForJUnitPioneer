package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.checkIndex;

public abstract class AbstractCombinatorialNumber<N extends CombinatorialNumber<N>> implements CombinatorialNumber<N> {
    protected int[] digits;
    protected int base;

    public AbstractCombinatorialNumber(int base, int length) {
        checkBase(base, length);
        this.digits = new int[length];
        this.base = base;
    }

    public int digit(int place) {
        checkIndex(place, digits.length);
        return digits[place];
    }

    public int length() {
        return digits.length;
    }

    public int base() {
        return base;
    }

    public abstract void increment();
//    public abstract CombinatorialNumber copy();
//    public abstract CombinatorialNumber plus(long i);

//    public abstract void decrement();
//    public abstract long difference(CombinatorialNumber otherNumber);
    public abstract boolean isMax();

    protected boolean isMax(boolean withRepetition) {
        for (int i = 0; i < length(); i++) {
            int highestDigit = base - 1 - ((withRepetition)? 0 : i);
            if(digit(i) != highestDigit) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        N that = (N) o;
        return base == that.base() && length() == that.length() && compareUnchecked(that) == 0;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(base);
        result = 31 * result + Arrays.hashCode(digits);
        return result;
    }

    @Override
    public int compareTo(N otherNumber) {
        checkNumber(otherNumber);
        return compareUnchecked(otherNumber);
    }

    private int compareUnchecked(N otherNumber) {
        for (int i = this.length() - 1; 0 <= i; i--) {
            int left = digit(i);
            int right = otherNumber.digit(i);
            int result = Integer.compare(left, right);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    private static void checkBase(int base, int length) {
        if (base < 2 || Byte.MAX_VALUE < base) {
            throw new IllegalArgumentException("The number of elements must lie in [1, " + Byte.MAX_VALUE + "]" +
                    " and may not be " + length + "!");
        }
    }

    private void checkNumber(N otherNumber) {
        if (this.base() != otherNumber.base()) {
            throw new IllegalArgumentException("Cannot compare two numbers with different bases: "
                    + this.base() + " vs. " + otherNumber.base());
        } else if (this.length() != otherNumber.length()) {
            throw new IllegalArgumentException("Cannot compare two numbers with different lengths: "
                    + this.length() + " vs. " + otherNumber.length());
        }
    }
}
