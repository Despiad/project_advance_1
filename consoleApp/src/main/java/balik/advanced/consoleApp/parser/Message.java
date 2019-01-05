package balik.advanced.consoleApp.parser;

public enum Message {

    MISSING_PARAMETER("Missing parameters in some methods. Use -h for help."),
    UNKNOWN_COMMAND("Unknown command."),
    EMPTY_HEAP("Heap is empty!"),
    INCORRECT_SUBCOMMAND("Incorrect subcommand"),
    REQUIRED_PARAMETER("Required parameter"),
    EXTRACT_MIN("Min number is %d"),
    INSERT("Inserting %d");


    private final String message;

    /**
     * Базовый конструктор
     */
    Message(String message) {
        this.message = message;
    }

    /**
     * Геттер для {@link Message#message}.
     *
     * @return {@link Message#message}
     */
    public String getMessage() {
        return message;
    }
}
