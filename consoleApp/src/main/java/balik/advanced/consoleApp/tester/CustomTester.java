package balik.advanced.consoleApp.tester;

import balik.advanced.consoleApp.parser.Message;

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
            printResult(Message.OUTPUT_FILE_ERROR.getMessage());
            return;
        }
        try {
            runner.runTest();
        } catch (IOException e) {
            printResult(Message.INPUT_FILE_ERROR.getMessage());
            return;
        }

        if (runner.checkTest()) {
            printResult(Message.TEST_OK.getMessage());
        } else {
            if (runner.isValidateInput()) {
                printResult(Message.WRONG_ANSWER.getMessage());
            } else {
                printResult(Message.INPUT_ERROR.getMessage());
            }
        }
    }

    private void printResult(String result) {
        System.out.println(result);
    }
}
