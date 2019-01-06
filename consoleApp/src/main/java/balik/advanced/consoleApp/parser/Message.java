package balik.advanced.consoleApp.parser;

public enum Message {

    MISSING_PARAMETER("Missing parameters in some methods. Use -h for help."),
    UNKNOWN_COMMAND("Unknown command."),
    EMPTY_HEAP("Heap is empty!"),
    INCORRECT_SUBCOMMAND("Incorrect subcommand"),
    REQUIRED_PARAMETER("Required parameter"),
    EXTRACT_MIN("Min number is %d"),
    INSERT("Inserting %d"),
    FILE_NOT_FOUND("File not found"),
    CANT_RESOLVE_SYMBOLS("Can't resolve symbols."),
    OVERWRITTEN_OPTION("Option should be specified only once.");


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
