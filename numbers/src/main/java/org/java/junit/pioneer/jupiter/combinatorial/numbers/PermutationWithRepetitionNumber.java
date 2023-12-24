package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

import static java.util.Objects.checkIndex;

public class PermutationWithRepetitionNumber implements CombinatorialNumber<PermutationWithRepetitionNumber> {
    private final int[] frequencies;
    private final int length;

    /**
     * Places are RELATIVE, i.e. the places depend on the free places from the places of the digit before.
     * E.g. given the frequencies = { 2, 3} for the digits [0, 1], then 2+3=5 places exist.
     * The places for the first digit "0" are absolute but not the places for the second digit "1" (and the following):
     * If the places for first digit "0" are [1, 3], then the places for the second digit "1" can be [0, 2, 1].
     * However, [0, 2, 1] is [0, 4, 2] because R=[0, 2, 4] remains from [1, 3] and [0, 4, 2] = [R[0]=0, R[2]=4, R[1]=2].
     */
    private final CombinationWithoutRepetitionNumber[] relativePlacesForDigits;

    public PermutationWithRepetitionNumber(int[] frequencies) {
        this.frequencies = Arrays.copyOf(frequencies, frequencies.length);
        this.length = IntStream.of(frequencies).sum();
        this.relativePlacesForDigits = new CombinationWithoutRepetitionNumber[frequencies.length - 1];
        int remainingLength = length();
        for (int i = 0; i < relativePlacesForDigits.length; i++) {
            int frequency = frequencies[i];
            relativePlacesForDigits[i] = new CombinationWithoutRepetitionNumber(remainingLength, frequency);
            remainingLength -= frequency;
        }
    }

    @Override
    public int digit(int place) {
        checkIndex(place, length);
        BitSet usedPlacesForAllDigits = new BitSet(length);
        for (int digit = 0; digit < relativePlacesForDigits.length; digit++) {
            CombinationWithoutRepetitionNumber relativePlacesForDigit = relativePlacesForDigits[digit];
            BitSet usedPlacesForDigit = new BitSet(length);
            usedPlacesForDigit.or(usedPlacesForAllDigits);
            for (int relativePlace = 0; relativePlace < relativePlacesForDigit.length(); relativePlace++) {
                int digitForPlace = relativePlacesForDigit.digit(relativePlace);
                int absolutPlace = usedPlacesForAllDigits.nextClearBit(0);
                for (int i = 1; i <= digitForPlace; i++) {
                    absolutPlace = usedPlacesForAllDigits.nextClearBit(absolutPlace + 1);
                }
                usedPlacesForDigit.set(absolutPlace);
                if (absolutPlace == place) {
                    return digit;
                }
            }
            usedPlacesForAllDigits = usedPlacesForDigit;
        }

        int absolutPlace = usedPlacesForAllDigits.nextClearBit(0);
        usedPlacesForAllDigits.set(absolutPlace);
        while (absolutPlace < length) {
            if (absolutPlace == place) {
                return frequencies.length - 1;
            }
            absolutPlace = usedPlacesForAllDigits.nextClearBit(0);
            usedPlacesForAllDigits.set(absolutPlace);
        }
        throw new AssertionError("Cannot find place " + place + " in all places " + Arrays.toString(relativePlacesForDigits));
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int base() {
        return frequencies.length;
    }

    @Override
    public int compareTo(PermutationWithRepetitionNumber o) {
        return 0;
    }

    @Override
    public void increment() {

        int lastDigit = relativePlacesForDigits.length - 1;
        for (int digit = lastDigit; 0 <= digit; digit--) {
            CombinationWithoutRepetitionNumber placesForDigit = relativePlacesForDigits[digit];
            if (placesForDigit.isMax()) {
                if (hasPredecessor(digit)) {
                    int frequency = frequencies[digit];
                    int remainingLength = length;
                    for (int i = 0; i < digit; i++) {
                        remainingLength -= frequencies[i];
                    }
                    relativePlacesForDigits[digit] = new CombinationWithoutRepetitionNumber(remainingLength, frequency);
                } else {
                    throw new ArithmeticException("Overflow of number " + placesForDigit
                            + " at place " + digit + " in all places " + Arrays.toString(relativePlacesForDigits));
                }
            } else {
                placesForDigit.increment();
                break;
            }
        }
    }

    private static boolean hasPredecessor(int digit) {
        return 0 <= digit - 1;
    }

    int[] toArray() {
        int[] result = new int[length];
        for (int place = 0; place < length; place++) {
            result[place] = digit(place);
        }
        return result;
    }


    public static void main(String[] args) {
        var permutationWithRepetitionNumber = new PermutationWithRepetitionNumber(new int[]{2, 4, 3});
        for (int i = 0; i < 180; i++) {
            permutationWithRepetitionNumber.increment();
        }
        System.out.println(Arrays.toString(permutationWithRepetitionNumber.toArray()));
    }
}
