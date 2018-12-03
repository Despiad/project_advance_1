package advanced.balik.application.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VEBTreeTest {
    private VEBTree vEBTree;

    @Before
    public void setUp() {
        vEBTree = VEBTree.createVEBTree(16);

        assert vEBTree != null;
        assertTrue("Tree should be empty", vEBTree.empty());

        /* Insert Elements */
        vEBTree.insert(3);
        vEBTree.insert(5);
        vEBTree.insert(8);
        vEBTree.insert(10);
        vEBTree.insert(12);
        vEBTree.insert(13);
        vEBTree.insert(14);
        vEBTree.insert(15);

    }

    @Test
    public void testInsertFind() {
        assertTrue("We inserted 3:", vEBTree.find(3));
        assertTrue("We inserted 5:", vEBTree.find(5));
        assertTrue("We inserted 8:", vEBTree.find(8));
        assertTrue("We inserted 10:", vEBTree.find(10));
        assertTrue("We inserted 12:", vEBTree.find(12));
        assertTrue("We inserted 13:", vEBTree.find(13));
        assertTrue("We inserted 14:", vEBTree.find(14));
        assertTrue("We inserted 15:", vEBTree.find(15));

        assertTrue("We DID NOT insert 0:", !vEBTree.find(0));
        assertTrue("We DID NOT insert 1:", !vEBTree.find(1));
        assertTrue("We DID NOT insert 1:", !vEBTree.find(2));
        assertTrue("We DID NOT insert 1:", !vEBTree.find(4));
        assertTrue("We DID NOT insert 1:", !vEBTree.find(6));
        assertTrue("We DID NOT insert 1:", !vEBTree.find(7));
        assertTrue("We DID NOT insert 1:", !vEBTree.find(9));
    }

    @Test
    public void testRemoveFind() {
        vEBTree.remove(3);
        assertTrue("We deleted 3:", !vEBTree.find(3));

        vEBTree.remove(5);
        assertTrue("We deleted 5:", !vEBTree.find(5));

        vEBTree.remove(8);
        assertTrue("We deleted 8:", !vEBTree.find(8));

        vEBTree.remove(10);
        assertTrue("We deleted 10:", !vEBTree.find(10));

        vEBTree.remove(12);
        assertTrue("We deleted 12:", !vEBTree.find(12));

        vEBTree.remove(13);
        assertTrue("We deleted 13:", !vEBTree.find(13));

        vEBTree.remove(14);
        assertTrue("We deleted 14:", !vEBTree.find(14));

        vEBTree.remove(15);
        assertTrue("We deleted 15:", !vEBTree.find(15));
    }

    @Test
    public void testPredecessor() {
        assertEquals("3 has no predecessor:", -1, vEBTree.predecessor(3));

        assertEquals("Predecessor of 5 is 3:", 3, vEBTree.predecessor(5));

        assertEquals("Predecessor of 8 is 5:", 5, vEBTree.predecessor(8));

        assertEquals("Predecessor of 10 is 8:", 8, vEBTree.predecessor(10));

        assertEquals("Predecessor of 15 is 14:", 14, vEBTree.predecessor(15));
    }

    @Test
    public void testMin() {
        assertEquals("Min should be 3:", 3, vEBTree.min());
    }

    @Test
    public void testMax() {
        assertEquals("Max should be 15:", 15, vEBTree.max());
    }

    @Test
    public void testNonEmpty() {
        assertTrue("Tree should be non empty", !vEBTree.empty());
    }
}