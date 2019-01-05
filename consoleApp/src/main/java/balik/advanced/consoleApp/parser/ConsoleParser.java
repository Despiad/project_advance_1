package balik.advanced.consoleApp.parser;

import balik.advanced.consoleApp.heap.LeftistHeap;
import picocli.CommandLine;

import static picocli.CommandLine.*;


/**
 * Класс с командами, необходимыми для работы с консольной версией приложения.
 *
 * @version 1.0
 * @autor Александр Яцюк
 */
public class ConsoleParser {
    /**
     * Поле экземпляр кучи
     */
    private static LeftistHeap heap = new LeftistHeap();

    /**
     * Поле счётчик для команды -e
     */
    private int extracter;

    /**
     * Поле счётчик для команды -i
     */
    private int inserter;

    /**
     * Базовый конструктор
     */
    public ConsoleParser() {
        extracter = 0;
        inserter = 0;
    }

    /**
     * names       - имя команды
     * description - описание команды
     *
     * @Option - аннотация для описания команды командной строки
     */
    @Option(names = {"-e", "--extract"}, description = "Extract min number from the heap")

    /**
     * Функция для вывода минимального числа, находящегося в куче
     * @param parameters - имена параметров для команды -e
     */
    void extract(String[] parameters) {
        if (heap.isEmpty()) {
            System.out.println(Message.EMPTY_HEAP.getMessage());
            return;
        }
        if (parameters[extracter].equals("min")) {
            System.out.println(String.format(Message.EXTRACT_MIN.getMessage(), heap.extractMin()));
        } else {
            System.out.println(Message.INCORRECT_SUBCOMMAND.getMessage());
        }
        ++extracter;
    }

    /**
     * names       - имя команды
     * description - описание команды
     * split       - символ-разделитель аргументов команды
     *
     * @Option - аннотация для описания команды командной строки
     */
    @Option(names = {"-i", "--insert"}, split = ",", description = "Insert number into the heap")
    /**
     * Функция для добавления числа в кучу
     * @param numbers - числа-аргументы для команды -i
     */
    void insert(Integer[] numbers) {
        if (numbers[inserter] == null) {
            System.out.println(Message.REQUIRED_PARAMETER.getMessage());
            return;
        }
        for (; inserter < numbers.length; ++inserter) {
            System.out.println(String.format(Message.INSERT.getMessage(), numbers[inserter]));
            heap.insert(numbers[inserter]);
        }
    }

    /**
     * @Option - аннотация для описания команды командной строки
     * @param names       - имя команды
     * @param usageHelp   - атрибут для определения параметров справки
     * @param description - описание команды
     */
    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message")
    /** Поле - триггер для вывода сообщения для помощи*/
    public boolean usageHelpRequested;
}
