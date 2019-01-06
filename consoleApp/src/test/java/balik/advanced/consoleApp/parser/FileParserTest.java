package balik.advanced.consoleApp.parser;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileParserTest {
    private File test1;
    private File test2;

    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        test1 = new File(classLoader.getResource("test_1.txt").getFile());
        test2 = new File(classLoader.getResource("test_2.txt").getFile());
    }

    @Test
    public void readFileByFileName() throws IOException {
        final String test1Path = test1.getAbsolutePath();
        final String[] expected = {"balik", "mem", "sanya", "hello", "world"};
        String[] result = FileParser.readFileByFileName(test1Path);
        assertArrayEquals("Arrays should be equal", expected, result);
    }

    @Test
    public void readFile() throws IOException {
        final String[] expected = {"balik", "mem", "sanya", "hello", "world"};
        String[] result = FileParser.readFile(test1);
        assertArrayEquals("Arrays should be equal", expected, result);
    }

    @Test
    public void readEmptyFile() throws IOException {
        final String[] expected = {""};
        String[] result = FileParser.readFile(test2);
        assertArrayEquals("Arrays should be equal", expected, result);
    }
}