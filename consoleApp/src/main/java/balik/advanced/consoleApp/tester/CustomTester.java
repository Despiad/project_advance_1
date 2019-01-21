package balik.advanced.consoleApp.tester;

import java.io.File;
import java.io.IOException;

public class CustomTester {
    private File inputFile;
    private File answerFile;

    public CustomTester(File inputFile, File answerFile) {
        this.inputFile = inputFile;
        this.answerFile = answerFile;
    }

    public void runTest() {
        TestRunner runner;
        try {
            runner = new TestRunner(inputFile, answerFile);
        } catch (IOException e) {
            printResult("Error read answer file");
            return;
        }
        try {
            runner.runTest();
        } catch (IOException e) {
            printResult("Error read input file");
            return;
        }

        if(runner.checkTest()){
            printResult("OK");
        }else{
            if(runner.isValidateInput()){
                printResult("WA");
            }else {
                printResult("PE");
            }
        }
    }

    private void printResult(String result) {
        System.out.println(result);
    }
}
