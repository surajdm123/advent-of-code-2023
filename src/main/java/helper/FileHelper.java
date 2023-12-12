package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public String[] getLines(final String fileName) {

        String filePath = "./src/main/resources/" + fileName;

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] linesArray = lines.toArray(new String[0]);

        return linesArray;
    }

    public char[][] getCharGridFromPath(final String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        int maxColumns = 0;

        String filePath = "./src/main/resources/" + fileName;

        // Read the file and find the maximum line length
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                if (line.length() > maxColumns) {
                    maxColumns = line.length();
                }
            }
        }

        // Create the 2D char array
        char[][] charArray = new char[lines.size()][maxColumns];

        // Fill the 2D array
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                charArray[i][j] = lines.get(i).charAt(j);
            }
        }

        return charArray;
    }

    public String readFileAsString(String fileName) throws IOException {
        final String filePath = "./src/main/resources/" + fileName;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
