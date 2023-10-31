package org.java.junit.pioneer.jupiter.combinatorics.samples.countingsort.impl;

import org.java.junit.pioneer.jupiter.combinatorics.samples.countingsort.api.CountingSorter;

import java.util.List;

/**
 * This implementation accepts lists with duplicates.
 */
public class ByteArrayCountingSorter implements CountingSorter {
    private final static int MAX_VALUE = Short.MAX_VALUE + 1; // +1 for value 0
    private final static int MAX_COUNT = Byte.MAX_VALUE;

    public void sort(List<Integer> ints) {
        byte[] counts = count(ints);
        sortArray(ints, counts);
    }

    private byte[] count(List<Integer> ints) {
        byte[] counts = new byte[MAX_VALUE];
        for (int i : ints) {
            if (i < 0) {
                throw new IllegalArgumentException("Found value " + i + " which is negative." +
                        "This sorting algorithm only supports positive values between 0 and " + MAX_VALUE + ".");
            } else if(i <= MAX_VALUE) {
                if (counts[i] + 1 <= MAX_COUNT) {
                    counts[i]++;
                } else {
                    throw new IllegalArgumentException("Counted " + i + " for the " + (MAX_COUNT + 1) + " time. " +
                            "This sorting algorithm only supports at most " + MAX_COUNT + " repetitions of the same  int.");
                }
            } else {
                throw new IllegalArgumentException("Found value " + i + " which is larger than " + MAX_VALUE + "." +
                        "This sorting algorithm only supports values between 0 and " + MAX_VALUE + ".");
            }
        }
        return counts;
    }

    private void sortArray(List<Integer> ints, byte[] counts) {
        int arrayIndex = 0;
        for (int countIndex = 0; countIndex < counts.length; countIndex++) {
            int count = counts[countIndex];
            for (int i = 0; i < count; i++) {
                ints.set(arrayIndex++, countIndex);
            }
        }
    }
}
