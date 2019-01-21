package balik.advanced.consoleApp.tester;

import balik.advanced.consoleApp.parser.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class CustomTesterTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

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
    public void correctTest() {
        File inputFile = new File(classLoader.getResource(correctInputFileName).getFile());
        CustomTester tester = new CustomTester(inputFile, answerFile);
        tester.runTest();
        assertEquals(Message.TEST_OK.getMessage() + "\n", systemOutRule.getLog());
    }

    @Test
    public void incorrectTest() {
        File inputFile = new File(classLoader.getResource(incorrectInputFileName).getFile());
        CustomTester tester = new CustomTester(inputFile, answerFile);
        tester.runTest();
        assertEquals(Message.WRONG_ANSWER.getMessage() + "\n", systemOutRule.getLog());
    }

    @Test
    public void validateTest() {
        File inputFile = new File(classLoader.getResource(validateTestFileName).getFile());
        CustomTester tester = new CustomTester(inputFile, answerFile);
        tester.runTest();
        assertEquals(Message.INPUT_ERROR.getMessage() + "\n", systemOutRule.getLog());
    }

    @Test
    public void incorrectOutputFile() {
        File inputFile = new File(classLoader.getResource(correctInputFileName).getFile());
        CustomTester tester=new CustomTester(inputFile,new File("where is it?"));
        tester.runTest();
        assertEquals(Message.OUTPUT_FILE_ERROR.getMessage() + "\n", systemOutRule.getLog());
    }

    @Test
    public void incorrectInputFile(){
        CustomTester tester = new CustomTester(new File("where is it?"), answerFile);
        tester.runTest();
        assertEquals(Message.INPUT_FILE_ERROR.getMessage() + "\n", systemOutRule.getLog());
    }

    @After
    public void clearLogs(){
        systemOutRule.clearLog();
    }
}