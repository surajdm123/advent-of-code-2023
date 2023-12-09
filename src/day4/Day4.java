package day4;

import helper.FileHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day4 {

    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String args[]) {
        FileHelper helper = new FileHelper();
        String[] lines = helper.getLines("src/day4/input.txt");

        // Part 1:
        int totalPoints = 0;
        for(String line : lines) {
            totalPoints += getPointsFromLineItem(line);
        }

        System.out.println("Part 1: " + totalPoints);

        // Part 2
        for(String line : lines) {
            populateMapFromLineItem(line);
        }

        int totalCount = 0;
        for(int count : map.values()) {
            totalCount += count;
        }

        System.out.println("Part 2: " + totalCount);
    }

    public static int getPointsFromLineItem(String line) {
        Set<Integer> winningNumbers = getNumbers(line, true);
        Set<Integer> myNumbers = getNumbers(line, false);

        Set<Integer> intersection = new HashSet<>(myNumbers);

        intersection.retainAll(winningNumbers);

        if (intersection.size() == 0) return 0;

        int ans = 1;

        for(int i=0; i<intersection.size() - 1; i++) {
            ans *= 2;
        }

        return ans;
    }

    public static void populateMapFromLineItem(String line) {
        Set<Integer> winningNumbers = getNumbers(line, true);
        Set<Integer> myNumbers = getNumbers(line, false);
        int gameId = getGameId(line);

        map.putIfAbsent(gameId, 1);

        Set<Integer> intersection = new HashSet<>(myNumbers);
        intersection.retainAll(winningNumbers);

        for(int i=0; i<intersection.size(); i++) {
            int currentCount = map.get(gameId);
            int nextGameId = gameId + 1 + i;
            map.putIfAbsent(nextGameId, 1);
            map.put(nextGameId, map.get(nextGameId) + currentCount);
        }
    }

    private static int getGameId(String line) {
        final String gameString = line.split(":")[0];
        final String gameIdStr = gameString.substring(4);
        final int gameId = Integer.parseInt(gameIdStr.trim());
        return gameId;
    }

    public static Set<Integer> getNumbers(String line, boolean isWinningNumbers) {

        int idx = isWinningNumbers ? 0 : 1;

        Set<Integer> set = new HashSet<>();

        String winningNumberPartStr = line.split(":")[1].split("\\|")[idx];
        String[] winningNumberStrs = winningNumberPartStr.split(" ");

        for(String winningNumberStr : winningNumberStrs) {
            if (!winningNumberStr.isBlank())
                set.add(Integer.parseInt(winningNumberStr.trim()));
        }

        return set;
    }

}
