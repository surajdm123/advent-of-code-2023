package day2;

import helper.FileHelper;

import java.util.ArrayList;
import java.util.List;

class CubeSet {
    int red;
    int green;
    int blue;

    public CubeSet() {

    }

    public CubeSet(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean isPossible() {
        return this.red <= 12 && this.green <= 13 && this.blue <= 14;
    }
}

class Transaction {
    int gameId;
    List<CubeSet> sets = new ArrayList<>();
}

public class Day2 {

    public static Transaction getTransaction(String line) {
        String gameStr = line.split(":")[0];
        int gameId = Integer.parseInt(gameStr.substring(4).trim());

        String allSetsStr = line.split(":")[1];
        String[] setStrs = allSetsStr.split(";");

        Transaction transaction = new Transaction();
        transaction.gameId = gameId;

        for(String setStr : setStrs) {
            String[] cubes = setStr.split(",");
            CubeSet cubeSet = new CubeSet();
            for(String cube : cubes) {
                cube = cube.trim();
                int count = Integer.parseInt(cube.split(" ")[0]);
                String color = cube.split(" ")[1];

                switch(color) {
                    case "red": cubeSet.red = count; break;
                    case "green": cubeSet.green = count; break;
                    case "blue": cubeSet.blue = count; break;
                }
            }

            transaction.sets.add(cubeSet);
        }

        return transaction;

    }

    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper();
        String[] lines = fileHelper.getLines("src/day2/input.txt");

        int sum = 0;

        // Part 1
        for(String line : lines) {
            Transaction transaction = getTransaction(line);
            boolean possible = true;
            for(CubeSet cubeSet : transaction.sets) {
                possible &= cubeSet.isPossible();
            }
            if(possible) {
                sum += transaction.gameId;
            }
        }

        // System.out.println(sum);

        // Part 2

        int ans = 0;

        for(String line : lines) {
            Transaction transaction = getTransaction(line);
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            for(CubeSet cubeSet : transaction.sets) {
                maxRed = Math.max(maxRed, cubeSet.red);
                maxBlue = Math.max(maxBlue, cubeSet.blue);
                maxGreen = Math.max(maxGreen, cubeSet.green);
            }

            ans += (maxBlue * maxGreen * maxRed);
        }

        System.out.println(ans);
    }
}
