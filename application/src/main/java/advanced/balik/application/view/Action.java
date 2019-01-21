package advanced.balik.application.view;

public enum Action {
    INSERT("You have added an element equal to %d to the heap."),
    INSERT_RANDOM("A random element equal to %d items was added to the heap."),
    MIN("The minimum element of the heap is %d."),
    EXTRACT_MIN("A minimum element equal to %d was taken from the heap."),
    CLEAR("Heap was cleaned!"),
    EMPTY("Heap is empty!"),
    STEP_BACK("Step back to previous heap!");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
