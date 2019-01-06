package balik.advanced.consoleApp.parser;

import java.io.*;

public class FileParser {

    public static String[] readFileByFileName(String fileName) throws IOException {
        return readFile(new File(fileName));
    }

    public static String[] readFile(File input) throws IOException {
        String[] result;
        try (Reader reader = new FileReader(input);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            StringBuilder allLines = new StringBuilder();

            String currLine = bufferedReader.readLine();
            while (currLine != null) {
                allLines.append(currLine).append(" ");
                currLine = bufferedReader.readLine();
            }

            result = allLines.toString().split(" ");
        } catch (IOException e) {
            throw e;
        }
        return result;
    }

}
