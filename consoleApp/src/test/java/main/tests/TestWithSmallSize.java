package main.tests;

import balik.advanced.consoleApp.heap.LeftistHeap;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import util.MyJUnitStopWatch;

import java.util.*;

import static org.junit.Assert.*;

@Epic("Tests with small amount of elements")
@Feature("Number of elements is 500")
public class TestWithSmallSize {
    private LeftistHeap heap;

    private final int size = 500;

    @Rule
    public MyJUnitStopWatch stopwatch = new MyJUnitStopWatch();

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
    @DisplayName("Increasing elements test")
    @Description("We insert each number in range from 0 up to size.\n" +
            "\n" +
            "This test is used for checking if queue can correctly insert numbers, such that each new number is bigger" +
            " than all previous, so it has to be inserted at the end of the queue. " +
            "Also after each pushing we check the min element in the queue.\n" +
            "\n" +
            "After whole insertation we extract numbers from the queue to check if they are in the correct order.")
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
    @DisplayName("Decreasing elements test")
    @Description("We insert each number in range from size down to 1.\n" +
            "\n" +
            "This test is used for checking if queue can correctly insert numbers, " +
            "such that each new number is smaller than all previous, " +
            "so it has to be inserted at the beginning of the queue. " +
            "Also after each pushing we check the min element in the queue.\n" +
            "\n" +
            "After whole insertation we extract numbers from the queue to check if they are in the correct order.")
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
    @DisplayName("Zero Test")
    @Description("We insert 0 in the queue size times.\n" +
            "\n" +
            "This test is used for checking if queue can correctly insert numbers that are equal. " +
            "Also after each insert we check the min element in the queue.\n" +
            "\n" +
            "After whole insertation we extract numbers from the queue to check if they are in the correct order.")
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
    @DisplayName("Same Random Test")
    @Description("We insert the same random value in the queue size times.\n" +
            "\n" +
            "This test is used for checking if queue can correctly insert numbers that are equal. " +
            "Also after each insert we check the min element in the queue.\n" +
            "\n" +
            "After whole insertation we extract numbers from the queue to check if they are in the correct order.")
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
    @DisplayName("Insert-extract test")
    @Description("We insert each number in range from 0 up to size. " +
            "After that we are checking what is the min element in the queue using getMin. " +
            "And after that we extract that element using command extractMin. " +
            "Also after extract we use getMin to check if queue is empty. " +
            "This test is used for checking if queue can correctly store " +
            "and extract elements and it doesn't leave any garbage in the queue. " +
            "By doing this we store one element in the queue and extract it so the queue should be empty.")
    public void insertExtract() {
        for (int i = 0; i < size; ++i) {
            heap.insert(i);
            assertEquals("Min should be current index:", i, heap.getMin());
            heap.extractMin();
            assertTrue("Heap should be empty:", heap.isEmpty());
        }

        assertEquals("Min should be -1:", -1, heap.getMin());
        assertTrue("Heap should be empty:", heap.isEmpty());
    }

    @Test
    @DisplayName("GetMin Test")
    @Description("At the start we insert random element in the queue. " +
            "After that we use command getMin size times to check what the min element in the queue is.\n" +
            "So this is the stress test for command getMin. " +
            "We check if command getMin doesn't effect on the queue (not removes elements from the queue).")
    public void getMinFromOneElementHeap() {
        RANDOM.setSeed(System.currentTimeMillis());
        final int randomNumber = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
        heap.insert(randomNumber);
        for (int i = 0; i < size; ++i) {
            assertEquals("Min should be your random number:", randomNumber, heap.getMin());
        }
    }

    @Test
    @DisplayName("Empty Heap Test")
    @Description("We try to extractMin from empty heap")
    public void extractFromEmptyHeap() {
        for (int i = 0; i < size; ++i) {
            assertEquals("Min should be -1", -1, heap.getMin());
            heap.extractMin();
        }
    }

    @Test
    @DisplayName("Insert-Extract Increasing Test")
    @Description("This test is used for checking " +
            "if queue can correctly process commands insert and extract combined.")
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
    @DisplayName("Insert-Extract Increasing Test with random values")
    @Description("This test is used for checking " +
            "if queue can correctly process commands insert and extract combined.")
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
    @DisplayName("Insert Random Test")
    @Description("We insert random elements in the queue size times.\n" +
            "This test is used for checking if queue can correctly insert numbers, " +
            "such that each new number has different position in the queue. " +
            "Also after each insert we check the min element in the queue.\n" +
            "\n" +
            "After whole insertation we extract numbers from the queue to check if they are in the correct order.")
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

    @Test
    @DisplayName("Compare with priority queue Test")
    @Description("We insert random elements into Java Collection Priority Queue" +
            "and our heap. Then compare minimum elements and all elements order.")
    public void insertCompareWithPriorityQueue() {
        PriorityQueue<Integer> javaPriorityQueue = new PriorityQueue<>();
        RANDOM.setSeed(System.currentTimeMillis());
        for (int i = 0; i < size; ++i) {
            final int randomValue = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
            javaPriorityQueue.add(randomValue);
            heap.insert(randomValue);
            assertEquals("Minimums in heaps should be equal:", (int) javaPriorityQueue.peek(), heap.getMin());
        }

        for (int i = 0; i < size; ++i) {
            assertEquals("Minimums in heaps should be equal:", (int) javaPriorityQueue.peek(), heap.getMin());
            javaPriorityQueue.poll();
            heap.extractMin();
        }

        assertEquals("Heaps should be empty", heap.isEmpty(), javaPriorityQueue.isEmpty());
    }

    @After
    public void closeTest() {
        heap.clear();
    }
}
