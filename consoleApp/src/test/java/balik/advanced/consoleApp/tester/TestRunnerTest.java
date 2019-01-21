package balik.advanced.consoleApp.tester;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestRunnerTest {

    private final static String validateTestFileName = "validateTest.txt";
    private final static String incorrectInputFileName = "incorrectTest.txt";
    private final static String correctInputFileName = "inputTest.txt";
    private final static String answerFileName = "outputTest.txt";

    private ClassLoader classLoader;

    private File answerFile;

    @Before
    public void setUp() {
        classLoader = getClass().getClassLoader();
        answerFile = new File(classLoader.getResource(answerFileName).getFile());
    }

    @Test
    public void correctTest() throws IOException {
        File inputFile = new File(classLoader.getResource(correctInputFileName).getFile());
        TestRunner runner = new TestRunner(inputFile, answerFile);
        runner.runTest();
        assertTrue(runner.checkTest());
        assertTrue(runner.isValidateInput());
    }

    @Test
    public void incorrectTest() throws IOException {
        File inputFile = new File(classLoader.getResource(incorrectInputFileName).getFile());
        TestRunner runner = new TestRunner(inputFile, answerFile);
        runner.runTest();
        assertFalse(runner.checkTest());
        assertTrue(runner.isValidateInput());
    }

    @Test
    public void validateTest() throws IOException {
        File inputFile = new File(classLoader.getResource(validateTestFileName).getFile());
        TestRunner runner = new TestRunner(inputFile, answerFile);
        runner.runTest();
        assertFalse(runner.checkTest());
        assertFalse(runner.isValidateInput());
    }
}