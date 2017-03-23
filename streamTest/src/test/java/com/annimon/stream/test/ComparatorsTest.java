package com.annimon.stream.test;

import com.annimon.stream.Comparators;
import com.annimon.stream.Stream;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public final class ComparatorsTest {
    private static final List<Integer> inputList = Arrays.asList(2, 0, 1, 5, 7, 3, 4, 8, 6, 9);
    private static final List<Integer> sortedList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final List<Integer> reversedList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);

    @Test
    public void testReverseOrder() {
        List<Integer> actualList = Stream.of(inputList).sorted(Comparators.reverseOrder()).toList();
        assertEquals(actualList, reversedList);
    }

    @Test
    public void testComparing() {
        List<Integer> actualList = Stream.of(inputList).sorted(
                Comparators.comparing(v -> v)).toList();
        assertEquals(actualList, sortedList);
    }

    @Test
    public void testComparing_Reversed() {
        List<Integer> actualList = Stream.of(inputList).sorted(
                Comparators.<Integer, Integer>comparing(v -> v).reversed()).toList();
        assertEquals(actualList, reversedList);
    }
}
