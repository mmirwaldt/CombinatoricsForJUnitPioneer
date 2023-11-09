package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VariationWithRepetitionNumberTest {
    @Test
    void test_1_binary_digit_value_0() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 1);
        assertEquals(2, number.base());
        assertEquals(1, number.length());
        assertEquals(0, number.digit(0));
    }

    @Test
    void test_1_binary_digit_increment_0_to_1() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 1);
        assertEquals(0, number.digit(0));
        number.increment();
        assertEquals(1, number.digit(0));
    }

    @Test
    void test_1_binary_digit_increment_to_overflow() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 1);
        number.increment();
        assertThrows(ArithmeticException.class, number::increment);
    }

    @Test
    void test_2_binary_digits_increment_0_to_3() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 2);
        assertEquals(0, number.digit(0));
        assertEquals(0, number.digit(1));
        number.increment();
        assertEquals(0, number.digit(0));
        assertEquals(1, number.digit(1));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(0, number.digit(1));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(1, number.digit(1));
    }

    @Test
    void test_2_binary_digits_increment_to_overflow() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 2);
        number.increment();
        number.increment();
        number.increment();
        assertThrows(ArithmeticException.class, number::increment);
    }

    @Test
    void test_3_binary_digits_increment_0_to_7() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 3);
        assertEquals(0, number.digit(0));
        assertEquals(0, number.digit(1));
        assertEquals(0, number.digit(2));
        number.increment();
        assertEquals(0, number.digit(0));
        assertEquals(0, number.digit(1));
        assertEquals(1, number.digit(2));
        number.increment();
        assertEquals(0, number.digit(0));
        assertEquals(1, number.digit(1));
        assertEquals(0, number.digit(2));
        number.increment();
        assertEquals(0, number.digit(0));
        assertEquals(1, number.digit(1));
        assertEquals(1, number.digit(2));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(0, number.digit(1));
        assertEquals(0, number.digit(2));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(0, number.digit(1));
        assertEquals(1, number.digit(2));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(1, number.digit(1));
        assertEquals(0, number.digit(2));
        number.increment();
        assertEquals(1, number.digit(0));
        assertEquals(1, number.digit(1));
        assertEquals(1, number.digit(2));
    }

    @Test
    void test_3_binary_digits_increment_to_overflow() {
        VariationWithRepetitionNumber number = new VariationWithRepetitionNumber(2, 3);
        for (int i = 0; i < 7; i++) {
            number.increment();
        }
        assertThrows(ArithmeticException.class, number::increment);
    }
}
