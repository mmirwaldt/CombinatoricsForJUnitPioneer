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
        int lastPlace = digits.length - 1;
        for (int place = lastPlace; 0 <= place; place--) {
            int digit = digits[place];

        }
    }

    private boolean isDigitUsed(int digit, int digitIndex) {
        for (int otherDigitIndex = 0; otherDigitIndex < digits.length; otherDigitIndex++) {
            if(otherDigitIndex != digitIndex && digits[otherDigitIndex] == digit) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public void increment() {
//        int lastDigitIndex = digits.length - 1;
//        for (int digitIndex = lastDigitIndex; 0 <= digitIndex; digitIndex--) {
//            int increment = digits[digitIndex] + 1;
//            int sortedIndex = Arrays.binarySearch(sortedIndices, (byte) digitIndex);
//            int higherSortedIndex = sortedIndex;
//            for (; higherSortedIndex - 1 < sortedIndices.length; higherSortedIndex++) {
//                int nextDigitIndex = sortedIndices[higherSortedIndex + 1];
//                int digit = digits[nextDigitIndex];
//                if (increment < digit) {
//                    break;
//                }
//            }
//            if (increment < base) {
//                digits[digitIndex]++;
//                if (sortedIndex < higherSortedIndex) {
//                    System.arraycopy(sortedIndices, sortedIndex + 1, sortedIndices, sortedIndex, sortedIndices.length - (sortedIndex + 1));
//                    System.arraycopy(sortedIndices, higherSortedIndex, sortedIndices, higherSortedIndex + 1, sortedIndices.length - (higherSortedIndex + 1));
//                    sortedIndices[higherSortedIndex] = (byte) digitIndex;
//                }
//                return;
//            } else {
//                int firstDigitIndex = sortedIndices[0];
//                int firstDigit = digits[firstDigitIndex];
//                if (0 < firstDigit) {
//                    digits[digitIndex] = 0;
//                    System.arraycopy(sortedIndices, sortedIndex + 1, sortedIndices, sortedIndex, sortedIndices.length - (sortedIndex + 1));
//                    System.arraycopy(sortedIndices, 0, sortedIndices, 1, sortedIndices.length - 1);
//                    sortedIndices[0] = (byte) digitIndex;
//                } else {
//                    int minSortedIndex = 0;
//                    int minDigitIndex = sortedIndices[minSortedIndex];
//                    int minDigit = digits[minDigitIndex];
//                    for (; minSortedIndex + 1 < sortedIndices.length; minSortedIndex++) {
//                        if (sortedIndex != minSortedIndex + 1) {
//                            int nextDigitIndex = digits[minSortedIndex + 1];
//                            int nextDigit = digits[nextDigitIndex];
//                            if (minDigit < nextDigit) {
//                                break;
//                            } else {
//                                minDigit = nextDigit;
//                            }
//                        }
//                    }
//                    System.arraycopy(sortedIndices, sortedIndex + 1, sortedIndices, sortedIndex, sortedIndices.length - (sortedIndex + 1));
//                    System.arraycopy(sortedIndices, minSortedIndex, sortedIndices, minSortedIndex + 1, sortedIndices.length - (minSortedIndex + 1));
//                    sortedIndices[minSortedIndex] = (byte) digitIndex;
//                    digits[digitIndex] = (byte) minDigit;
//                }
//            }
//        }
//        throw new ArithmeticException("Overflow of number with base " + base + "!");
//    }
//
//    private int nextMinSortedIndex(int sortedIndex) {
//        int digitIndex = sortedIndices[sortedIndex];
//        int increment = digits[digitIndex] + 1;
//        for (int nextSortedIndex = sortedIndex + 1; nextSortedIndex < sortedIndices.length; nextSortedIndex++) {
//            int nextDigitIndex = sortedIndices[nextSortedIndex];
//            int digit = digits[nextDigitIndex];
//            if (increment < digit) {
//                return nextSortedIndex - 1;
//            }
//        }
//        return sortedIndices.length - 1;
//    }
}
