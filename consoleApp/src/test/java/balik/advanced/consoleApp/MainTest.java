package balik.advanced.consoleApp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.*;

public class MainTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testOnlyInsert() {
        final String[] args = {"-i", "5", "-i", "6", "-i", "5", "-i", "1", "-i", "2", "-i", "3"};
        final String expectedOut = "Inserting 5\n" +
                "Inserting 6\n" +
                "Inserting 5\n" +
                "Inserting 1\n" +
                "Inserting 2\n" +
                "Inserting 3\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testInsertAndExtract() {
        final String[] args = {"-e", "min", "-i", "6", "-i", "5", "-i", "1", "-e", "min", "-e", "min"};
        final String expectedOut = "Heap is empty!\n" +
                "Inserting 6\n" +
                "Inserting 5\n" +
                "Inserting 1\n" +
                "Min number is 1\n" +
                "Min number is 5\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testOnlyFile() {
        final String[] args = {"-f", "/home/despiad/Desktop/project_advance_1/consoleApp/src/main/testFiles/test_1.txt"};
        final String expectedOut = "Heap is empty!\n" +
                "Heap is empty!\n" +
                "Inserting 3\n" +
                "Min number is 3\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testFileNotFound() {
        final String[] args = {"-f", "/home/despiad/Desktop/project_advance_1/consoleApp/src/main/testFiles/test_4.txt"};
        final String expectedOut = "File not found\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testFileWrongCommands() {
        final String[] args = {"-f", "/home/despiad/Desktop/project_advance_1/consoleApp/src/main/testFiles/test_2.txt"};
        final String expectedOut = "Inserting 1\n" + "Min number is 1\n" + "Heap is empty!\n" + "Unknown command\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testWrongCommands() {
        final String[] args = {"sanya", "sanya", "sanya", "-i", "1", "-e", "min", "-e", "min"};
        final String expectedOut = "Inserting 1\n" + "Min number is 1\n" + "Heap is empty!\n" + "Unknown command\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testWrongCommandParameterAtStart() {
        final String[] args = {"-i", "a", "-e", "min", "-e", "min", "-i,", "1"};
        final String expectedOut = "Wrong parameter\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testWrongCommandParameterInEnd() {
        final String[] args = {"-i", "3", "-e", "min", "-i", "a", "-e", "aaa"};
        final String expectedOut = "Inserting 3\n" + "Min number is 3\n" + "Wrong parameter\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testOverwrittenCommand() {
        final String[] args = {"-f", "/home/despiad/Desktop/project_advance_1/consoleApp/src/main/testFiles/test_2.txt", "-f", "/home/despiad/Desktop/project_advance_1/consoleApp/src/main/testFiles/test_1.txt"};
        final String expectedOut = "Inserting 1\n" + "Min number is 1\n" + "Heap is empty!\n" + "Option should be specified only once\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }

    @Test
    public void testEmptyCommands() {
        final String[] args = {};
        final String expectedOut = "There are no commands\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }
    @Test
    public void testMissedParameter() {
        final String[] args = {"-i", "-e","min"};
        final String expectedOut = "Missing parameters in some methods. Use -h for help.\n";
        Main.main(args);
        assertEquals(expectedOut, systemOutRule.getLog());
    }


}