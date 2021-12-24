package aoc11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Aoc11b {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc11\\aoc11.txt"));
        // Finding size of matrix
        int xCount = 0;
        int yCount = 0;
        while (textFile.hasNextLine()) {
            xCount += 1;
            yCount = textFile.nextLine().length();
        }
        int[][] matrix = new int[xCount][yCount];

        //Filling the matrix
        textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc11\\aoc11.txt"));
        int a = 0;
        while (textFile.hasNextLine()) {
            String nextLine = textFile.nextLine();
            matrix[a] = Arrays.stream(nextLine.split("")).mapToInt(Integer::parseInt).toArray();
            a++;
        }
        int noOfIterations = 1000;
        int noOfFlashes = 0;
        for (int i = 0; i < noOfIterations; i++) {
            Set<Map.Entry<Integer, Integer>> tbe = new HashSet<>();
            increment(matrix, tbe);
            int currentFlashes = 0;
            do {
                int currFlashes = explode(matrix, tbe);
                noOfFlashes += currFlashes;
                currentFlashes += currFlashes;
            } while(tbe.size() != 0);
            if (currentFlashes == xCount * yCount) {
                System.out.println(i + 1);
                break;
            }
        }
        System.out.println(noOfFlashes);
    }

    public static void increment(int[][] matrix, Set<Map.Entry<Integer, Integer>> tbe) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 9) {
                    matrix[i][j] +=1;
                }
                else {
                    tbe.add(new AbstractMap.SimpleEntry<>(i,j));
                }
            }
        }
    }

    public static int explode(int[][] matrix, Set<Map.Entry<Integer, Integer>> tbe) {
        Set<Map.Entry<Integer, Integer>> tbel = new HashSet<>();
        Set<Map.Entry<Integer, Integer>> tbr = new HashSet<>();
        int flashes = 0;
        for (Map.Entry<Integer, Integer> entry: tbe) {
            flashes += 1;
            int x = entry.getKey(), y = entry.getValue();
            matrix[x][y] = 0;
            incrementAdjacent(x-1, y, matrix, tbel);
            incrementAdjacent(x+1, y, matrix, tbel);
            incrementAdjacent(x, y+1, matrix, tbel);
            incrementAdjacent(x, y-1, matrix, tbel);
            incrementAdjacent(x+1, y-1, matrix, tbel);
            incrementAdjacent(x+1, y+1, matrix, tbel);
            incrementAdjacent(x-1, y-1, matrix, tbel);
            incrementAdjacent(x-1, y+1, matrix, tbel);
            tbr.add(new AbstractMap.SimpleEntry<>(x, y));
        }
        for (Map.Entry<Integer, Integer> entry: tbel) {
            tbe.add(entry);
        }
        for (Map.Entry<Integer, Integer> entry: tbr) {
            tbe.remove(entry);
        }
        return flashes;
    }

    public static void incrementAdjacent(int x, int y, int[][] matrix,  Set<Map.Entry<Integer, Integer>> tbel) {
        if (x < 0 || x > matrix.length - 1 || y < 0 || y > matrix.length - 1 || matrix[x][y] == 0) {
            return;
        }
        if (matrix[x][y] == 9) {
            tbel.add(new AbstractMap.SimpleEntry<>(x, y));
            return;
        }
        matrix[x][y] += 1;
    }
}
