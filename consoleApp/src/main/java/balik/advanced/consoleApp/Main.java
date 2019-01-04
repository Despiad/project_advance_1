package balik.advanced.consoleApp;

import balik.advanced.consoleApp.heap.LeftistHeap;
import picocli.CommandLine;

import static picocli.CommandLine.*;

/**
 * Класс с командами, необходимыми для работы с консольной версией приложения.
 *
 * @version 1.0
 * @autor Александр Яцюк
 */
class Heap {
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
     * Конструктор - создание нового объекта
     */
    Heap() {
        extracter = 0;
        inserter = 0;
    }

    /**
     * @param names       - имя команды
     * @param description - описание команды
     * @Option - аннотация для описания команды командной строки
     */
    @Option(names = {"-e", "--extract"}, description = "Extract min number from the heap")

    /**
     * Функция для вывода минимального числа, находящегося в куче
     * @param parameters - имена параметров для команды -e
     */
    void extract(String[] parameters) {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty");
        }
        if (parameters[extracter].equals("min")) {
            System.out.println("Min number " + heap.extractMin());
        } else {
            System.out.println("Incorrect subcommand");
        }
        ++extracter;
    }

    /**
     * @param names       - имя команды
     * @param description - описание команды
     * @param split       - символ-разделитель аргументов команды
     * @Option - аннотация для описания команды командной строки
     */
    @Option(names = {"-i", "--insert"}, split = ",", description = "Insert number into the heap")
    /**
     * Функция для добавления числа в кучу
     * @param numbers - числа-аргументы для команды -i
     */
    void insert(Integer[] numbers) {
        if (numbers[inserter] == null) {
            System.out.println("Required parameter");
        }
        for (int i = inserter; inserter < numbers.length; ++inserter) {
            System.out.println("Inserting " + numbers[inserter]);
            heap.insert(numbers[inserter]);
        }
    }

    /**
     * @Option - аннотация для описания команды командной строки
     * @param names       - имя команды
     * @param usageHelp   - атрибут для определения параметров справки
     * @param description - описание команды
     */
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    /** Поле - триггер для вывода сообщения для помощи*/
    boolean usageHelpRequested;
}

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 0) {
                Heap parser = new Heap();
                /**
                 * На этом моменте парсятся команды коммандной строки
                 */
                new CommandLine(parser).parse(args);
                /**
                 * Если встретилась команда с usageHelp = true, то выведется сообщение с помощью.
                 * Сообщение описывает каждую команду, помеченную аннотацией @Option
                 */
                if (parser.usageHelpRequested) {
                    CommandLine.usage(new Heap(), System.out);
                }
            }
        }
        /**
         * В случае некорректного ввода аргументов - сообщение об ошибке
         */
        catch (MissingParameterException e) {
            System.out.println("Missing parameters in some methods. Use -h for help.");
        }
        /**
         * В случае некорректного ввода команды - сообщение об ошибке
         */
        catch (UnmatchedArgumentException e) {
            System.out.println("Unknown command.");
        }

    }
}

