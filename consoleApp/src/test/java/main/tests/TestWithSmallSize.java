package main.tests;

import balik.advanced.consoleApp.heap.LeftistHeap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TestWithSmallSize {
    private LeftistHeap heap;

    private final int size = 50;

    /**
     * Генератор случайных чисел.
     */
    private static final Random RANDOM = new Random();

    /**
     * Верхняя граница (не включительно) генерации случайных чисел.
     */
    private static final int UPPER_BOUND_RANDOM = 1000;

    /**
     * Нижняя граница (включительно) генерации случайных чисел.
     */
    private static final int LOWER_BOUND_RANDOM = -999;

    @Before
    public void setUp() {
        heap = new LeftistHeap();
    }

    @Test
    public void insertIncreasing() {
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            heap.insert(i);
            expected.add(i);
            assertEquals("Min should be 0:", 0, heap.getMin());
        }

        Integer[] expectedArray = new Integer[expected.size()];
        expectedArray = expected.toArray(expectedArray);
        Arrays.sort(expectedArray);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            result.add(heap.getMin());
            heap.extractMin();
        }

        Integer[] resultArray = new Integer[result.size()];
        resultArray = result.toArray(resultArray);

        assertArrayEquals("Arrays should be equal", expectedArray, resultArray);
    }

    @Test
    public void insertDecreasing() {
        List<Integer> expected = new ArrayList<>();
        for (int i = size; i > 0; --i) {
            heap.insert(i);
            expected.add(i);
            assertEquals("Min should be current index:", i, heap.getMin());
        }

        Integer[] expectedArray = new Integer[expected.size()];
        expectedArray = expected.toArray(expectedArray);
        Arrays.sort(expectedArray);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            result.add(heap.getMin());
            heap.extractMin();
        }

        Integer[] resultArray = new Integer[result.size()];
        resultArray = result.toArray(resultArray);

        assertArrayEquals("Arrays should be equal", expectedArray, resultArray);
    }

    @Test
    public void insertZero() {
        Integer[] expectedArray = new Integer[size];
        Arrays.fill(expectedArray, 0);

        for (int i = 0; i < size; ++i) {
            heap.insert(0);
            assertEquals("Min should be 0:", 0, heap.getMin());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            result.add(heap.getMin());
            heap.extractMin();
        }

        Integer[] resultArray = new Integer[result.size()];
        resultArray = result.toArray(resultArray);

        assertArrayEquals("Arrays should be equal", expectedArray, resultArray);
    }

    @Test
    public void insertOneRandomNumber() {
        RANDOM.setSeed(System.currentTimeMillis());
        final int randomNumber = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
        Integer[] expectedArray = new Integer[size];
        Arrays.fill(expectedArray, randomNumber);

        for (int i = 0; i < size; ++i) {
            heap.insert(randomNumber);
            assertEquals("Min should be your random number:", randomNumber, heap.getMin());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            result.add(heap.getMin());
            heap.extractMin();
        }

        Integer[] resultArray = new Integer[result.size()];
        resultArray = result.toArray(resultArray);

        assertArrayEquals("Arrays should be equal", expectedArray, resultArray);
    }

    @Test
    public void insertExtract() {
        for (int i = 0; i < size; ++i) {
            heap.insert(i);
            assertEquals("Min should be current index:", i, heap.getMin());
            heap.extractMin();
        }

        assertEquals("Min should be -1:", -1, heap.getMin());
        assertTrue("Heap should be empty:", heap.isEmpty());
    }

    @Test
    public void getMinFromOneElementHeap() {
        RANDOM.setSeed(System.currentTimeMillis());
        final int randomNumber = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
        heap.insert(randomNumber);
        for (int i = 0; i < size; ++i) {
            assertEquals("Min should be your random number:", randomNumber, heap.getMin());
        }
    }

    @Test
    public void extractFromEmptyHeap() {
        for (int i = 0; i < size; ++i) {
            assertEquals("Min should be -1", -1, heap.getMin());
            heap.extractMin();
        }
    }

    @Test
    public void insertExtractIncreasing() {
        for (int i = 0; i < size; ++i) {
            heap.insert(i);
            assertEquals("Min should be current index:", i, heap.getMin());
            heap.insert(i + 1);
            assertEquals("Min should be current index:", i, heap.getMin());
            heap.extractMin();
            assertEquals("Min should be current index+1:", i + 1, heap.getMin());
            heap.extractMin();
        }
    }

    @Test
    public void insertExtractRandom() {
        int previousElement = -1;
        RANDOM.setSeed(System.currentTimeMillis());
        for (int i = 0; i < size; ++i) {
            assertEquals("Min should be previous random element:", previousElement, heap.getMin());
            heap.extractMin();
            previousElement = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
            heap.insert(previousElement);
        }
    }

    @Test
    public void insertRandom() {
        List<Integer> expected = new ArrayList<>();
        RANDOM.setSeed(System.currentTimeMillis());
        for (int i = 0; i < size; ++i) {
            final int randomValue = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
            heap.insert(randomValue);
            expected.add(randomValue);
        }

        Integer[] expectedArray = new Integer[expected.size()];
        expectedArray = expected.toArray(expectedArray);
        Arrays.sort(expectedArray);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            result.add(heap.getMin());
            heap.extractMin();
        }

        Integer[] resultArray = new Integer[result.size()];
        resultArray = result.toArray(resultArray);

        assertArrayEquals("Arrays should be equal", expectedArray, resultArray);
    }

    //todo:compare with priority queue random

    @After
    public void closeTest() {
        heap.clear();
    }
}
