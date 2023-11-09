package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VariationWithoutRepetitionNumberTest {
    @Test
    void test_1_binary_digit_value_0() {
        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(2, 1);
        assertEquals(2, number.base());
        assertEquals(1, number.length());
        assertEquals(0, number.digit(0));
    }

    @Test
    void test_1_binary_digit_increment_0_to_1() {
        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(2, 1);
        assertEquals(0, number.digit(0));
        number.increment();
        assertEquals(1, number.digit(0));
    }

    @Test
    void test_1_trinary_digit_increment_0_to_2() {
        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(3, 1);
        assertEquals(0, number.digit(0));
        number.increment();
        assertEquals(1, number.digit(0));
        number.increment();
        assertEquals(2, number.digit(0));
    }

    @Test
    void test_1_binary_digit_increment_to_overflow() {
        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(2, 1);
        number.increment();
        assertThrows(ArithmeticException.class, number::increment);
    }

    @Test
    void test_1_trinary_digit_increment_to_overflow() {
        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(3, 1);
        number.increment();
        number.increment();
        assertThrows(ArithmeticException.class, number::increment);
    }

//    @Test
//    void test_2_binary_digits_increment_once() {
//        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(2, 2);
//        assertEquals(0, number.digit(0));
//        assertEquals(1, number.digit(1));
//        number.increment();
//        assertEquals(1, number.digit(0));
//        assertEquals(0, number.digit(1));
//    }
//
//    @Test
//    void test_2_binary_digits_increment_twice_to_overflow() {
//        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(2, 2);
//        number.increment();
//        assertThrows(ArithmeticException.class, number::increment);
//    }
//
//    @Test
//    void test_3_trinary_digits_increment_012_to_210() {
//        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(3, 3);
//        assertEquals(0, number.digit(0));
//        assertEquals(1, number.digit(1));
//        assertEquals(2, number.digit(2));
//        number.increment();
//        assertEquals(0, number.digit(0));
//        assertEquals(2, number.digit(1));
//        assertEquals(1, number.digit(2));
//        number.increment();
//        assertEquals(1, number.digit(0));
//        assertEquals(0, number.digit(1));
//        assertEquals(2, number.digit(2));
//        number.increment();
//        assertEquals(1, number.digit(0));
//        assertEquals(2, number.digit(1));
//        assertEquals(0, number.digit(2));
//        number.increment();
//        assertEquals(2, number.digit(0));
//        assertEquals(0, number.digit(1));
//        assertEquals(1, number.digit(2));
//        number.increment();
//        assertEquals(2, number.digit(0));
//        assertEquals(1, number.digit(1));
//        assertEquals(0, number.digit(2));
//    }
//
//    @Test
//    void test_3_trinary_digits_increment_to_overflow() {
//        VariationWithoutRepetitionNumber number = new VariationWithoutRepetitionNumber(3, 3);
//        for (int i = 0; i < 5; i++) {
//            number.increment();
//        }
//        assertThrows(ArithmeticException.class, number::increment);
//    }
}
