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
}