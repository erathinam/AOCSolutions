package aoc9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Aoc9b {
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
        boolean [][] visited = new boolean[xCount][yCount];
        //Filling the matrix
        textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc9\\aoc9.txt"));
        int i = 0;
        while(textFile.hasNextLine()) {
            String nextLine = textFile.nextLine();
            matrix[i] = Arrays.stream(nextLine.split("")).mapToInt(Integer::parseInt).toArray();
            i++;
        }
        Queue<Integer> values = new PriorityQueue<>();
        for (int a = 0; a < matrix.length; a++) {
            for (int b = 0; b < matrix[0].length; b++) {
                int score = traverse(matrix, visited, a, b);
                if (score != 0) {
                    if (values.size() >= 3) {
                        if (values.peek() < score) {
                            values.poll();
//                            System.out.println("the value at " + a + " " + b + " is " + score);
                            values.add(score);
                        }
                    } else {
//                        System.out.println("the value at " + a + " " + b + " is " + score);
                        values.add(score);
                    }
                }
            }
        }
        int finalScore = 1;
        for (Integer value: values) {
            finalScore *= value;
        }
        System.out.println(finalScore);
    }

    public static int traverse(int[][] matrix, boolean[][] visited, int a, int b) {
//        System.out.println("Traversing " + a + " " + b);
        if ( a < 0 || a >= matrix.length || b < 0 || b >= matrix[0].length || matrix[a][b] == 9 || visited[a][b]) {
            return 0;
        }
        visited[a][b] = true;
        int count = 0;
        count += traverse(matrix, visited, a -1, b);
        count += traverse(matrix, visited, a, b - 1);
        count += traverse(matrix, visited, a, b + 1);
        count += traverse(matrix, visited, a + 1, b);
//        System.out.println("value at " + a + "," + b + " is " + (count+1));
        return count + 1;
    }


}
