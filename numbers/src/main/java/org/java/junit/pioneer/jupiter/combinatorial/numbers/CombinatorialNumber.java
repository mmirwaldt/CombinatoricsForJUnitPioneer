package org.java.junit.pioneer.jupiter.combinatorial.numbers;

public interface CombinatorialNumber<N extends CombinatorialNumber<N>> extends Comparable<N> {
    int digit(int place);

    int length();

    int base();

    void increment();
}
