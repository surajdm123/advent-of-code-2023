package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day1 {

    public static void main(String[] args) {

        String filePath = "src/day1/input.txt";
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

        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);

        int sum = 0;
        for(String line : linesArray) {
            int minFirstIndex = Integer.MAX_VALUE;
            int maxLastIndex = Integer.MIN_VALUE;

            int first = -1;
            int last = -1;

//            PART 1

//            for (int i=0; i<line.length(); i++) {
//                if(Character.isDigit(line.charAt(i))) {
//                    last = line.charAt(i) - '0';
//                    if(first == -1) {
//                        first = line.charAt(i) - '0';
//                    }
//                }
//            }

            // PART 2
            for(Map.Entry<String, Integer> entrySet : map.entrySet()) {
                String entry = entrySet.getKey();
                int firstIndex = line.indexOf(entry);
                int lastIndex = line.lastIndexOf(entry);

                if(firstIndex < minFirstIndex && firstIndex != -1) {
                    minFirstIndex = firstIndex;
                    first = map.get(entry);
                }

                if(lastIndex > maxLastIndex && lastIndex != -1) {
                    maxLastIndex = lastIndex;
                    last = map.get(entry);
                }
            }

            int current = Integer.valueOf(first * 10 + last);
            sum += current;
        }

        System.out.println(sum);

    }
}
