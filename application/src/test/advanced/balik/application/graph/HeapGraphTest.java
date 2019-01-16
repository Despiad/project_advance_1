package advanced.balik.application.graph;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapGraphTest {
    private static Scene scene;

    private static HeapGraph heapGraph;

    static { //crutch to access elements without launching application
        new JFXPanel();
    }

    @Before
    public void setUp() {
        heapGraph = new HeapGraph();
        scene = new Scene(heapGraph.getContent());
    }

    @Test
    public void testInsertAndClear() {
        heapGraph.addNode(0);

        assertNotNull(scene.lookup("#0"));
        assertSelectedCell(0);
        assertEquals(1, getCellNumber());
        assertEquals(0, getVertexNumber());

        heapGraph.addNode(100);

        assertNotNull(scene.lookup("#100"));
        assertSelectedCell(100);
        assertEquals(2, getCellNumber());
        assertEquals(1, getVertexNumber());

        heapGraph.addNode(-100);

        assertNotNull(scene.lookup("#-100"));
        assertSelectedCell(-100);
        assertEquals(3, getCellNumber());
        assertEquals(2, getVertexNumber());

        assertEquals(-100, heapGraph.getMin());

        heapGraph.clear();

        assertEquals(-1, heapGraph.getMin());

        assertEquals(0, getCellNumber());
        assertEquals(0, getVertexNumber());
        assertNull(scene.lookup(".cell-selected"));
    }

    @Test
    public void testMinAndInsertAndEmpty() {
        assertTrue("Heap should be empty: ", heapGraph.isEmpty());
        heapGraph.addNode(1);

        assertNotNull(scene.lookup("#1"));
        assertEquals(1, heapGraph.getMin());

        heapGraph.extractMin();
        assertNull(scene.lookup("#1"));
        assertEquals(-1, heapGraph.getMin());

        heapGraph.addNode(1);
        assertSelectedCell(1);

        heapGraph.addNode(-1);
        assertSelectedCell(-1);

        heapGraph.addNode(-2);
        assertSelectedCell(-2);

        heapGraph.addNode(2);
        assertSelectedCell(2);

        assertFalse("Heap should be non empty", heapGraph.isEmpty());

        assertEquals("Min element should be -2",-2,heapGraph.getMin());
        heapGraph.extractMin();
        assertNull(scene.lookup("#-2"));

        assertEquals("Min element should be -1",-1,heapGraph.getMin());
        heapGraph.extractMin();
        assertNull(scene.lookup("#-1"));

        assertEquals("Min element should be 1",1,heapGraph.getMin());
        heapGraph.extractMin();
        assertNull(scene.lookup("#1"));

        assertEquals("Min element should be 2",2,heapGraph.getMin());
        heapGraph.extractMin();
        assertNull(scene.lookup("#2"));

        assertTrue("Heap should be empty: ", heapGraph.isEmpty());
    }

    @Test
    public void testResize() {
        heapGraph.addNode(1);// firstly, we should draw smth
        assertEquals("20.0", String.valueOf(HeapGraph.CELL_RADIUS));
        heapGraph.setMode(ViewMode.changeTo16px);
        assertEquals("22.5", String.valueOf(HeapGraph.CELL_RADIUS));
        heapGraph.setMode(ViewMode.changeTo18px);
        assertEquals("25.0", String.valueOf(HeapGraph.CELL_RADIUS));
        heapGraph.setMode(ViewMode.changeTo20px);
        assertEquals("27.5", String.valueOf(HeapGraph.CELL_RADIUS));
        heapGraph.setMode(ViewMode.STANDART);
        assertEquals("20.0", String.valueOf(HeapGraph.CELL_RADIUS));
    }

    @After
    public void clear() {
        heapGraph.clear();
    }

    private int getVertexNumber() {
        return heapGraph.getContent().lookupAll(".vertex").size();
    }

    private int getCellNumber() {
        return heapGraph.getContent().lookupAll(".cell").size();
    }

    private void assertSelectedCell(int value) {
        assertEquals(value, Integer.parseInt(heapGraph.getContent().lookup(".cell-selected").getId()));
    }
}