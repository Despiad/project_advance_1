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
    public void testTreeGraphConsistency() {
        assertEquals("20.0", String.valueOf(HeapGraph.CELL_RADIUS));

        assertEquals(2, heapGraph.getContent().getChildren().size());

        heapGraph.addNode(0);

        assertSelectedCell(0);
        assertEquals(1, getCellNumber());
        assertEquals(0, getVertexNumber());

        heapGraph.addNode(100);

        assertSelectedCell(100);
        assertEquals(2, getCellNumber());
        assertEquals(1, getVertexNumber());

        heapGraph.addNode(-100);

        assertSelectedCell(-100);
        assertEquals(3, getCellNumber());
        assertEquals(2, getVertexNumber());

        assertEquals(-100, heapGraph.getMin());

        heapGraph.clear();

        assertEquals(0, getCellNumber());
        assertEquals(0, getVertexNumber());
        assertNull(scene.lookup(".cell-selected"));
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