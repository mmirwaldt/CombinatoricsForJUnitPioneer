package org.java.junit.pioneer.jupiter.combinatorial.numbers;

import org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinatorialNumberTest {
    @Test
    void test_createStart_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(1, 10);
        assertEquals(0, number.digitAt(0));
    }

    @Test
    void test_createStart_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        assertEquals(0, number.digitAt(0));
        assertEquals(0, number.digitAt(1));
    }

    @Test
    void test_createEnd_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createEnd(1, 10);
        assertEquals(1, number.digitAt(0));
    }

    @Test
    void test_createEnd_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createEnd(2, 10);
        assertEquals(1, number.digitAt(0));
        assertEquals(0, number.digitAt(1));
    }

    @Test
    void test_increment_once_with_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(1, 10);
        assertEquals(0, number.digitAt(0));
        number.increment();
        assertEquals(1, number.digitAt(0));
    }

    @Test
    void test_increment_2_times_with_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(1, 10);
        number.increment();
        number.increment();
        assertEquals(2, number.digitAt(0));
    }

    @Test
    void test_increment_2_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        number.increment();
        assertEquals(0, number.digitAt(0));
        assertEquals(1, number.digitAt(1));
        number.increment();
        assertEquals(0, number.digitAt(0));
        assertEquals(2, number.digitAt(1));
    }

    @Test
    void test_increment_9_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 9; i++) {
            number.increment();
        }
        assertEquals(0, number.digitAt(0));
        assertEquals(9, number.digitAt(1));
    }

    @Test
    void test_increment_10_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 10; i++) {
            number.increment();
        }
        assertEquals(1, number.digitAt(0));
        assertEquals(0, number.digitAt(1));
    }

    @Test
    void test_increment_11_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 11; i++) {
            number.increment();
        }
        assertEquals(1, number.digitAt(0));
        assertEquals(1, number.digitAt(1));
    }

    @Test
    void test_increment_99_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 99; i++) {
            number.increment();
        }
        assertEquals(9, number.digitAt(0));
        assertEquals(9, number.digitAt(1));
    }

    @Test
    void test_increment_100_times_with_length_3() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(3, 10);
        for (int i = 0; i < 100; i++) {
            number.increment();
        }
        assertEquals(1, number.digitAt(0));
        assertEquals(0, number.digitAt(1));
        assertEquals(0, number.digitAt(2));
    }

    @Test
    void test_increment_101_times_with_length_3() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(3, 10);
        for (int i = 0; i < 101; i++) {
            number.increment();
        }
        assertEquals(1, number.digitAt(0));
        assertEquals(0, number.digitAt(1));
        assertEquals(1, number.digitAt(2));
    }

    @Test
    void test_increment_110_times_with_length_3() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(3, 10);
        for (int i = 0; i < 110; i++) {
            number.increment();
        }
        assertEquals(1, number.digitAt(0));
        assertEquals(1, number.digitAt(1));
        assertEquals(0, number.digitAt(2));
    }

    @Test
    void test_increment_123_times_with_length_3() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(3, 10);
        for (int i = 0; i < 123; i++) {
            number.increment();
        }
        assertEquals(1, number.digitAt(0));
        assertEquals(2, number.digitAt(1));
        assertEquals(3, number.digitAt(2));
    }

    @Test
    void test_size_no_increment_with_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(1, 10);
        assertEquals(0, number.size());
    }

    @Test
    void test_size_increment_once_with_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(1, 10);
        number.increment();
        assertEquals(1, number.size());
    }

    @Test
    void test_size_increment_twice_with_length_1() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(1, 10);
        number.increment();
        number.increment();
        assertEquals(2, number.size());
    }

    @Test
    void test_size_no_increment_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        assertEquals(0, number.size());
    }

    @Test
    void test_size_increment_once_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        number.increment();
        assertEquals(1, number.size());
    }

    @Test
    void test_size_increment_twice_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        number.increment();
        number.increment();
        assertEquals(2, number.size());
    }

    @Test
    void test_size_increment_9_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 9; i++) {
            number.increment();
        }
        assertEquals(9, number.size());
    }

    @Test
    void test_size_increment_10_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 10; i++) {
            number.increment();
        }
        assertEquals(10, number.size());
    }

    @Test
    void test_size_increment_11_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 11; i++) {
            number.increment();
        }
        assertEquals(11, number.size());
    }

    @Test
    void test_size_increment_12_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 12; i++) {
            number.increment();
        }
        assertEquals(12, number.size());
    }

    @Test
    void test_size_increment_21_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 21; i++) {
            number.increment();
        }
        assertEquals(21, number.size());
    }

    @Test
    void test_size_increment_99_times_with_length_2() {
        org.java.junit.pioneer.jupiter.combinatorics.spliterators.CombinatorialNumber number = CombinatorialNumber.createStart(2, 10);
        for (int i = 0; i < 99; i++) {
            number.increment();
        }
        assertEquals(99, number.size());
    }
}
