package balik.advanced.consoleApp;

import balik.advanced.consoleApp.parser.ConsoleParser;
import balik.advanced.consoleApp.parser.Message;
import picocli.CommandLine;

import static picocli.CommandLine.*;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 0) {
                ConsoleParser parser = new ConsoleParser();
                parser.clearHeap();
                String[] test = new String[2];
                boolean isTest = false;
                int counter = 0;
                /**
                 * Если встретилась команда с usageHelp = true, то выведется сообщение с помощью.
                 * Сообщение описывает каждую команду, помеченную аннотацией @Option
                 */

                for (int i = 0; i < args.length; ++i) {
                    if (args[i].equals("-t")|| args[i].equals("--test")) {
                        isTest = true;
                        counter = i;
                    }
                }
                if (isTest) {
                    test[0] = args[counter];
                    test[1] = args[counter + 1];
                    new CommandLine(parser).parse(test);
                } else {
                    new CommandLine(parser).parse(args);
                }

                if (parser.usageHelpRequested) {
                    CommandLine.usage(new ConsoleParser(), System.out);
                }
            } else {
                System.out.println(Message.NO_COMMANDS.getMessage());
            }
        }
        /**
         * В случае пропуска аргументов - сообщение об ошибке
         */ catch (MissingParameterException e) {
            System.out.println(Message.MISSING_PARAMETER.getMessage());
        }
        /**
         * В случае некорректного ввода команды - сообщение об ошибке
         */ catch (UnmatchedArgumentException e) {
            System.out.println(Message.UNKNOWN_COMMAND.getMessage());
        }
        /**
         * В случае повторного ввода одноразовой команды - сообщение об ошибке
         */ catch (OverwrittenOptionException e) {
            System.out.println(Message.OVERWRITTEN_OPTION.getMessage());
        }
        /**
         * В случае некорректного ввода агрументов - сообщение об ошибке
         */ catch (ParameterException e) {
            System.out.println(Message.WRONG_PARAMETER.getMessage());
        }
    }
}

