package advanced.balik.application.model;

import java.util.ArrayList;
import java.util.List;

public class VersionController {
    private List<PersistentLeftistHeap> heaps;

    public VersionController() {
        this.heaps = new ArrayList<>();
        heaps.add(new PersistentLeftistHeap());
    }

    public PersistentLeftistHeap getHeap(int version) {
        return heaps.get(version);
    }

    public PersistentLeftistHeap insert(int version, int element) {
        PersistentLeftistHeap newVersion = getHeap(version).insert(element);
        heaps.add(newVersion);
        return newVersion;
    }

    public int getMin(int version) {
        return getHeap(version).getMin();
    }

    public PersistentLeftistHeap extractMin(int version) {
        PersistentLeftistHeap newVersion = getHeap(version).extractMin();
        heaps.add(newVersion);
        return newVersion;
    }

    public boolean isEmpty(int version) {
        return getHeap(version).isEmpty();
    }

    public PersistentLeftistHeap clear(int version) {
        return getHeap(version).clear();
    }
}
