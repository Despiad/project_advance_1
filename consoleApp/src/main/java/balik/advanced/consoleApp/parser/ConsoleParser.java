package balik.advanced.consoleApp.parser;

import balik.advanced.consoleApp.heap.LeftistHeap;
import balik.advanced.consoleApp.tester.CustomTester;
import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static balik.advanced.consoleApp.parser.FileParser.readFile;
import static picocli.CommandLine.*;


/**
 * Класс с командами, необходимыми для работы с консольной версией приложения.
 *
 * @version 1.3
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

    public void clearHeap() {
        heap.clear();
    }

    /**
     * names       - имя команды
     * description - описание команды
     *
     * @Option - аннотация для описания команды командной строки
     */
    @Option(names = {"-t", "--test"}, split = ",", description = "Run test(s) from file")

    /**
     * Функция для запуска собственных тестов  из файла
     * @param inputFile  - путь файла с тестами
     * @param answerFile - путь файла с результами
     */
    void customTest(String[] paths) {
        if (paths.length != 2) {
            System.out.println(Message.WRONG_PARAMETER_NUMBER.getMessage());
        } else {
            File input = new File(paths[0]);
            File answer = new File(paths[1]);
            CustomTester tester = new CustomTester(input, answer);
            tester.runTest();
        }
    }

    /**
     * names       - имя команды
     * description - описание команды
     *
     * @Option - аннотация для описания команды командной строки
     */
    @Option(names = {"-f", "--file"}, description = "Run program from file")
    /**
     * Функция для парсинга команд из файла
     * @param filename - путь файла с командами
     */
    void fileParse(String filename) {
        try {
            File input = new File(filename);
            new CommandLine(new ConsoleParser()).parse(readFile(input));
        } catch (FileNotFoundException e) {
            System.out.println(Message.FILE_NOT_FOUND.getMessage());
        } catch (IOException e) {
            System.out.println(Message.CANT_RESOLVE_SYMBOLS.getMessage());
        }
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
            System.out.println(String.format(Message.EXTRACT_MIN.getMessage(), heap.getMin()));
            heap.extractMin();
        } else {
            System.out.println(Message.WRONG_PARAMETER.getMessage());
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
            System.out.println(Message.MISSING_PARAMETER.getMessage());
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
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message")
    /** Поле - триггер для вывода сообщения для помощи*/
    public boolean usageHelpRequested;

}
