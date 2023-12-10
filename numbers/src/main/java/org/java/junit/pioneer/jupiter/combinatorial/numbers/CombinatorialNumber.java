package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.checkIndex;

public abstract class CombinatorialNumber implements Comparable<CombinatorialNumber> {
    protected byte[] digits;
    protected byte base;

    public CombinatorialNumber(int base, int length) {
        checkParameters(base, length);
        this.digits = new byte[length];
        this.base = (byte) base;
    }

    public int digit(int i) {
        checkIndex(i, digits.length);
        return digits[i];
    }

    public int length() {
        return digits.length;
    }

    public int base() {
        return base;
    }

//    public abstract CombinatorialNumber copy();
    public abstract void increment();
//    public abstract void decrement();
//    public abstract CombinatorialNumber plus(long i);
//    public abstract long difference(CombinatorialNumber otherNumber);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombinatorialNumber that = (CombinatorialNumber) o;
        return base == that.base && Arrays.equals(digits, that.digits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(base);
        result = 31 * result + Arrays.hashCode(digits);
        return result;
    }

    @Override
    public int compareTo(CombinatorialNumber otherNumber) {
        checkNumber(otherNumber);
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

    private static void checkParameters(int base, int length) {
        checkBase(base, length);
        checkLength(base, length);
    }

    private static void checkBase(int base, int length) {
        if (base < 2 || Byte.MAX_VALUE < base) {
            throw new IllegalArgumentException("The number of elements must lie in [1, " + Byte.MAX_VALUE + "]" +
                    " and may not be " + length + "!");
        }
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

    private void checkNumber(CombinatorialNumber otherNumber) {
        if (this.base() != otherNumber.base()) {
            throw new IllegalArgumentException("Cannot compare two numbers with different bases: "
                    + this.base() + " vs. " + otherNumber.base());
        } else if (this.length() != otherNumber.length()) {
            throw new IllegalArgumentException("Cannot compare two numbers with different lengths: "
                    + this.length() + " vs. " + otherNumber.length());
        }
    }
}
