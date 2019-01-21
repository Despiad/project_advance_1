package balik.advanced.consoleApp.tester;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestRunnerTest {

    //todo:open files
private final String inputTestCorrect="inputTest.txt";
private final String answerTestCorrect="outputTest.txt";

    @Test
    public void testCorrect(){
        TestRunner runner=new TestRunner(new File(inputTestCorrect),new File(answerTestCorrect));
        runner.runTest();
        assertTrue(runner.checkTest());
    }

}