package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.checkIndex;

public abstract class CombinatorialNumber {
    protected byte[] digits;
    protected int base;

    public CombinatorialNumber(int base, int length) {
        this.digits = new byte[length];
        this.base = base;
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

    public abstract void increment();

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
}
