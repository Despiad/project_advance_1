package advanced.balik.application.view;

public enum Action {
    INSERT("You have added an element equal to %d to the heap."),
    INSERT_RANDOM("A random element equal to %d items was added to the heap."),
    MIN("The minimum element of the heap is %d."),
    CLEAR("Heap was cleaned!");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
