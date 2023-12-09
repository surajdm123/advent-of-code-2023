package day3;

import helper.FileHelper;

import java.util.HashSet;
import java.util.Set;

public class Day3 {

    static char[][] grid;
    static int rows;
    static int cols;
    static int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};

    public static void main(String[] args) throws Exception{
        FileHelper fileHelper = new FileHelper();
        final String filePath = "./src/main/resources/input.txt";

        System.out.println(System.getProperty("user.dir"));

        grid = fileHelper.getCharGridFromPath(filePath);
        rows = grid.length;
        cols = grid[0].length;


        // Part 1
        int ans = 0;

        for(int row = 0; row < rows; row++) {
            int start = -1;
            int end = -1;
            StringBuilder sb = new StringBuilder();
            for(int col = 0; col < cols; col++) {
                if(isDigit(row, col)) {
                    start = start == -1 ? col : start;
                    end = col;
                    sb.append(grid[row][col]);
                }

                if((!isDigit(row,col) || col == cols - 1) && !sb.isEmpty()) {
                    if(checkAdjacent(row, start, end)) {
                        ans += Integer.parseInt(sb.toString());
                    }
                    start = -1;
                    end = -1;
                    sb = new StringBuilder();
                }
            }
        }

        // System.out.println(ans);

        // PART 2

        int sum = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(isGear(row, col)) {
                    sum += getGearRatio(row, col);
                }
            }
        }

        System.out.println(sum);
    }

    private static int getGearRatio(int row, int col) {
        Set<Integer> set = new HashSet<>();
        for(int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if(inBound(nextRow, nextCol) && isDigit(nextRow, nextCol)) {
                int fullNumber = getFullNumber(nextRow, nextCol);
                set.add(fullNumber);
            }
        }

        if(set.size() == 2) {
            return set.stream().reduce(1, (a,b) -> a*b);
        }

        return 0;
    }

    private static int getFullNumber(int row, int col) {
        String resStr = grid[row][col] + "";

        // Go to the left
        int nextCol = col - 1;
        while(inBound(row, nextCol) && isDigit(row, nextCol)) {
            resStr = grid[row][nextCol] + resStr;
            nextCol--;
        }

        // Go to the right
        nextCol = col + 1;
        while(inBound(row, nextCol) && isDigit(row, nextCol)) {
            resStr += grid[row][nextCol];
            nextCol++;
        }

        return Integer.parseInt(resStr);

    }

    private static boolean isGear(int row, int col) {
        return grid[row][col] == '*';
    }

    private static boolean checkAdjacent(int row, int start, int end) {
        for(int col = start; col <= end; col++) {
            for(int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];
                if(inBound(nextRow, nextCol)) {
                    if(grid[nextRow][nextCol] != '.' && !isDigit(nextRow, nextCol)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean inBound(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextCol < cols && nextRow < rows;
    }

    private static boolean isDigit(int i, int j) {
        return grid[i][j] >= '0' && grid[i][j] <= '9';
    }
}
