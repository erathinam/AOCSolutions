package aoc9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Aoc9a {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc9\\aoc9.txt"));
        // Finding size of matrix
        int xCount = 0;
        int yCount = 0;
        while(textFile.hasNextLine()) {
            xCount+=1;
            yCount = textFile.nextLine().length();
        }
        int[][] matrix = new int[xCount][yCount];

        //Filling the matrix
        textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc9\\aoc9.txt"));
        int i = 0;
        while(textFile.hasNextLine()) {
            String nextLine = textFile.nextLine();
            matrix[i] = Arrays.stream(nextLine.split("")).mapToInt(Integer::parseInt).toArray();
            i++;
        }
        int score = 0;
//        for (int a = 0; a < matrix.length; a++) {
//            for (int b = 0; b < matrix[0].length; b++) {
//                System.out.print(matrix[a][b] + "|");
//            }
//            System.out.println();
//        }
        for (int j = 0; j < matrix.length; j++) {
            int prevNumber = Integer.MAX_VALUE;
            for (int k = 0; k < matrix[j].length; k++) {
                score += findScore(matrix, j, k, prevNumber);
                prevNumber = matrix[j][k];
            }
        }
        System.out.println(score);
    }

    public static int findScore(int[][] matrix, int j , int k, int prevNumber) {
        if (prevNumber <= matrix[j][k]) {
            return 0;
        }

        // check for top row with top-left and top-right edge cases
        if (j == 0) {
                return isMin(k == matrix[0].length - 1 ? Integer.MAX_VALUE : matrix[j][k + 1], Integer.MAX_VALUE, matrix[j + 1][k], matrix[j][k]) ? matrix[j][k] + 1 : 0;
        }

        //check for left most column with bottom left edge case
        else if (k == 0) {
            return isMin(matrix[j][k + 1], matrix[j - 1][k], j == matrix.length - 1 ? Integer.MAX_VALUE : matrix[j + 1][k], matrix[j][k]) ? matrix[j][k] + 1 : 0;
        }

        //check for bottom row with bottom right edge case
        else if (j == matrix.length - 1) {
            return isMin(k == matrix[0].length - 1 ? Integer.MAX_VALUE : matrix[j][k + 1], matrix[j - 1][k], Integer.MAX_VALUE, matrix[j][k]) ? matrix[j][k] + 1 : 0;
        }
        // check for right most column
        else if (k == matrix[0].length - 1) {
            return isMin(Integer.MAX_VALUE, matrix[j - 1][k], matrix[j + 1][k], matrix[j][k]) ? matrix[j][k] + 1 : 0;
        }
        // check for middle rows/columns
        else {
            //System.out.println(matrix[1].length);
            return isMin(matrix[j][k + 1], matrix[j - 1][k], matrix[j + 1][k], matrix[j][k]) ? matrix[j][k] + 1: 0;
        }
    }

    public static boolean isMin(int right, int up, int down, int center) {
        return center < up && center < down && center < right;
    }
}
