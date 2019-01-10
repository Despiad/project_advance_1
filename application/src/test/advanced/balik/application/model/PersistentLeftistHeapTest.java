package advanced.balik.application.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersistentLeftistHeapTest {
    private List<PersistentLeftistHeap> heaps;

    @Before
    public void setUp() {
        heaps = new ArrayList<>();
        heaps.add(new PersistentLeftistHeap());
    }

    @Test
    public void testZeroEmpty(){
        assertTrue("Version 0 heap should be empty", heaps.get(0).isEmpty());
    }

    @Test
    public void InsertExtractEmptyTest() {
        heaps.add(heaps.get(0).insert(1));
        heaps.add(heaps.get(0).insert(2));
        assertEquals("Version 1 min should be 1:", 1, heaps.get(1).getMin());
        assertEquals("Version 2 min should be 2:", 2, heaps.get(2).getMin());
        assertTrue("Version 0 heap should be empty", heaps.get(0).isEmpty());
        assertEquals("Version 0 min should be -1",-1, heaps.get(0).getMin());
        assertTrue("Version 1 heap should be non empty", !heaps.get(1).isEmpty());
        assertTrue("Version 2 heap should be non empty", !heaps.get(2).isEmpty());
        heaps.add(heaps.get(1).insert(-100));
        assertEquals("Version 3 min should be -100:", -100, heaps.get(3).getMin());
        heaps.add(heaps.get(3).extractMin());
        assertEquals("Version 4 min should be 1:", 1, heaps.get(4).getMin());

    }
}