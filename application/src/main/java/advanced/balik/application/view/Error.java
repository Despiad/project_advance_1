package advanced.balik.application.view;

public enum Error {
    NO_VERSIONS("No previous version for this heap!",
            "This version is first. Can't step back!"),
    INVALID_INPUT("Invalid input!","Only integers can be added to the heap."),
    MAX_CELL_SIZE("Maximum vertex size reached!",
            "It is impossible to increase vertex size."),
    MIN_CELL_SIZE("Minimum vertex size reached!",
            "It is impossible to decrease vertex size"),
    TOO_BIG("Too big element!","Absolute value of element must not exceed 1000");

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
