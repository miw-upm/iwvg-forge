package es.upm.miw.iwvg.devops.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FlowTest {

    @Test
    void testSize() {
        Stream<Integer> stream = Stream.of(0, 1);
        assertEquals(2, new Flow().size(stream));
        //ERROR: new Flow().size(stream); IllegalStateException: stream has already been operated upon or closed
    }

    @Test
    void testToArray() {
        assertArrayEquals(new Integer[]{0, 1}, new Flow().toArray(Stream.of(0, 1)));
    }

    @Test
    void testToList() {
        assertEquals(Arrays.asList(-3, 4, 0, -2, 5), new Flow().toList(Stream.of(-3, 4, 0, -2, 5)));
    }

    @Test
    void testMax() {
        assertEquals(5, new Flow().max(Stream.of(-3, 4, 0, -2, 5)));
    }

    @Test
    void testSum() {
        assertEquals(4, new Flow().sum(Stream.of(-3, 4, 0, -2, 5)));
    }

    @Test
    void testMul() {
        Stream<Integer> stream = Stream.iterate(1, n -> n + 1).limit(5);
        assertEquals(120, new Flow().mul(stream));
    }

    @Test
    void testMapToString() {
        assertEquals(Arrays.asList("3", "7"), new Flow().mapToString(Stream.of(3, 7)).collect(Collectors.toList()));
    }

    @Test
    void testIncrement() {
        assertEquals(Arrays.asList(4, 8), new Flow().increment(Stream.of(3, 7)).collect(Collectors.toList()));
    }

    @Test
    void testIncrementAndSum() {
        assertEquals(12.0, new Flow().incrementAndSum(Stream.of(3, 7)), 10 - 5);
    }

    @Test
    void testFilterPositive() {
        Stream<Integer> stream = Stream.of(-2, 1, 0, -3, 3);
        assertEquals(Arrays.asList(1, 0, 3), new Flow().filterPositives(stream).collect(Collectors.toList()));
    }

    @Test
    void testIsValueContent() {
        Stream<Integer> stream = Stream.of(-2, 1, 0, -3, 3);
        assertTrue(new Flow().isValueContent(stream, -3));
    }

    @Test
    void testIsValueContentNotExist() {
        Stream<Integer> stream = Stream.of(-2, 1, 0, -3, 3);
        assertFalse(new Flow().isValueContent(stream, 4));
    }

    @Test
    void testAreAllPositive() {
        Stream<Integer> stream = Stream.of(1, 0, 3);
        assertTrue(new Flow().areAllPositive(stream));
    }

    @Test
    void testAreAllPositiveExistNegative() {
        Stream<Integer> stream = Stream.of(-2, 1, 0, -3, 3);
        assertFalse(new Flow().areAllPositive(stream));
    }

    @Test
    void testRemoveCopy() {
        Stream<Integer> stream = Stream.of(0, 1, 0, 2, 2, 0);
        assertEquals(Arrays.asList(0, 1, 2), new Flow().removeCopy(stream).collect(Collectors.toList()));
    }

    @Test
    void testLog() {
        new Flow().log(Stream.of(-2, 1, 0, -3, 3));
    }

    @Test
    void testFlattening() {
        Integer[] array1 = new Integer[]{0, 1};
        Integer[] array2 = new Integer[]{0, 3};
        Integer[] array3 = new Integer[]{2, 5};
        Stream<Integer> stream = new Flow().flattening(Stream.of(array1, array2, array3));
        assertEquals(Arrays.asList(0, 1, 0, 3, 2, 5), stream.collect(Collectors.toList()));
    }


}