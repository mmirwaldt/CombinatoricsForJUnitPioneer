package org.java.junit.pioneer.jupiter.combinatorics.samples.countingsort.impl;

import org.java.junit.pioneer.jupiter.combinatorics.samples.countingsort.api.CountingSorter;

import java.util.BitSet;
import java.util.List;

/**
 * This implementation only accepts lists without duplicates.
 */
public class BitSetCountingSorter implements CountingSorter {
    public void sort(List<Integer> ints) {
        BitSet bits = count(ints);
        sortArray(ints, bits);
    }

    private BitSet count(List<Integer> ints) {
        BitSet bits = new BitSet();
        for (int current : ints) {
            if (bits.get(current)) {
                throw new IllegalArgumentException("Found " + current + " for the second time. " +
                        "This sorting algorithm only supports in array without repetitions.");
            } else {
                bits.set(current);
            }
        }
        return bits;
    }

    private void sortArray(List<Integer> ints, BitSet bits) {
        int arrayIndex = 0;
        for (int bitIndex = 0; bitIndex < bits.length(); bitIndex++) {
            if (bits.get(bitIndex)) {
                ints.set(arrayIndex++, bitIndex);
            }
        }
    }
}
