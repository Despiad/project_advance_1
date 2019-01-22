package balik.advanced.consoleApp.parser;

public enum Message {

    MISSING_PARAMETER("Missing parameters in some methods. Use -h for help."),
    UNKNOWN_COMMAND("Unknown command"),
    EMPTY_HEAP("Heap is empty!"),
    REQUIRED_PARAMETER("Required parameter"),
    EXTRACT_MIN("Min number is %d"),
    INSERT("Inserting %d"),
    FILE_NOT_FOUND("File not found"),
    CANT_RESOLVE_SYMBOLS("Can't resolve symbols"),
    OVERWRITTEN_OPTION("Option should be specified only once"),
    WRONG_PARAMETER("Wrong parameter"),
    NO_COMMANDS("There are no commands"),
    WRONG_PARAMETER_NUMBER("Incorrect number of parameters"),
    /**
     * TESTER MESSAGES
     **/
    TEST_OK("OK - Solution passed"),
    INPUT_ERROR("IE - Invalid input format"),
    WRONG_ANSWER("WA - Wrong answer"),
    INPUT_FILE_ERROR("RE - Error reading input file"),
    OUTPUT_FILE_ERROR("RE - Error reading answer file"),
    /**
     * TESTER TOKEN
     **/
    GET_MIN("min"),
    DELETE_MIN("extractMin");

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
