package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CombinationWithoutRepetitionNumberTest {
    @Test
    void test_1_binary_digit_value_0() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(2, 1);
        assertEquals(2, number.base());
        assertEquals(1, number.length());
        assertEquals(0, number.digit(0));
    }

    @Test
    void test_1_binary_digit_increment_0_to_1() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(2, 1);
        assertEquals(0, number.digit(0));
        number.increment();
        assertEquals(1, number.digit(0));
    }

    @Test
    void test_1_binary_digit_increment_to_overflow() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(2, 1);
        number.increment();
        assertThrows(ArithmeticException.class, number::increment);
    }

    @Test
    void test_2_binary_digits_length_2() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(2, 2);
        assertEquals(0, number.digit(0));
        assertEquals(1, number.digit(1));
    }

    @Test
    void test_2_binary_digits_length_2_increment_once_to_overflow() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(2, 2);
        assertThrows(ArithmeticException.class, number::increment);
    }

    @Test
    void test_3_trinary_digits_length_2() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(3, 2);
        assertEquals(0, number.digit(0));
        assertEquals(1, number.digit(1));
        number.increment();
        assertEquals(0, number.digit(0));
        assertEquals(2, number.digit(1));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(2, number.digit(1));
    }

    @Test
    void test_3_trinary_digits_length_2_increment_to_overflow() {
        CombinationWithoutRepetitionNumber number = new CombinationWithoutRepetitionNumber(3, 2);
        for (int i = 0; i < 2; i++) {
            number.increment();
        }
        assertThrows(ArithmeticException.class, number::increment);
    }
}
