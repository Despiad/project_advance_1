package advanced.balik.application.graph;

import advanced.balik.application.model.PersistentLeftistHeap;
import advanced.balik.application.view.Action;

public class Turn {
    private final PersistentLeftistHeap heap;
    private final String turnLog;
    private final int selectedNode;

    public Turn(PersistentLeftistHeap heap, String turnLog, int selectedNode) {
        this.heap = heap;
        this.turnLog = turnLog;
        this.selectedNode = selectedNode;
    }

    public int getSelectedNode() {
        return selectedNode;
    }

    public PersistentLeftistHeap getHeap() {
        return heap;
    }

    public String getTurnLog() {
        return turnLog;
    }

}
