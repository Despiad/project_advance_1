package advanced.balik.application.view;

public enum Error {
    NO_VERSIONS("No previous version for this heap!",
            "This version is first. Can't step back!"),
    ALREADY_IN_THIS_HEAP("Cannot insert number into this heap!",
            "This number is already in this heap"),
    INVALID_INPUT("Invalid input!","Only integers can be added to the heap."),
    MAX_CELL_SIZE("Maximum vertex size reached!",
            "It is impossible to increase vertex size."),
    MIN_CELL_SIZE("Minimum vertex size reached!",
            "It is impossible to decrease vertex size");

    private final String header;
    private final String message;

    Error(String header, String message) {
        this.header = header;
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }
}
