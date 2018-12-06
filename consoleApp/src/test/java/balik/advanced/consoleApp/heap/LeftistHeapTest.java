package balik.advanced.consoleApp.heap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeftistHeapTest {
    private LeftistHeap heap;

    @Before
    public void setUp() {
        heap = new LeftistHeap();
        /* Insert Elements */
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(1);
        heap.insert(2);

    }

    @Test
    public void testNonEmpty() {
        assertTrue("Tree should be non empty", !heap.isEmpty());
    }

    @Test
    public void testExtractMinAndEmpty() {
        assertEquals("Min should be 1:", 1, heap.extractMin());
        assertEquals("Min should be 2:", 2, heap.extractMin());
        assertEquals("Min should be 3:", 3, heap.extractMin());
        assertEquals("Min should be 4:", 4, heap.extractMin());
        assertEquals("Min should be 5:", 5, heap.extractMin());
        assertEquals("Min should be -1:", -1, heap.extractMin());
        assertTrue("Heap should be empty", heap.isEmpty());
    }

}