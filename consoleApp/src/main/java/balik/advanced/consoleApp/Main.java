package balik.advanced.consoleApp;

import balik.advanced.consoleApp.heap.LeftistHeap;
import picocli.CommandLine;

import static picocli.CommandLine.*;
//little change for Sanya branch 
class Heap {
    private static LeftistHeap heap = new LeftistHeap();
    private int counter;
    private int inserter;

    Heap() {
        counter=0;
        inserter=0;
    }

    @Option(names = {"-e", "--extract"}, description = "Extract min number from the heap")
    void extract(String[] param) {
        if (param[counter].equals("min")) {
            System.out.println("Min number " + heap.extractMin());
        } else {
            System.out.println("Next time");
        }
        ++counter;
    }

    @Option(names = {"-i", "--insert"}, split = ",", required = true, description = "Insert number into the heap")
    void setCount(int[] numbers) {
        for (int i = inserter; inserter < numbers.length; ++inserter) {
            System.out.println("Inserting " + numbers[inserter]);
            heap.insert(numbers[inserter]);
        }
    }

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;
}

public class Main {
    public static void main(String[] args) {
        if (args.length != 0) {
            Heap parser = new Heap();
            new CommandLine(parser).parse(args);
            if (parser.usageHelpRequested) {
                CommandLine.usage(new Heap(), System.out);
            }
        }
    }
}
