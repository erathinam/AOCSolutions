package aoc6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class aoc6b {
    public static final int NO_OF_DAYS = 256;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc6\\aoc6.txt")).useDelimiter(",");
        List<Integer> inputs = new ArrayList<>();
        while (textFile.hasNextLine()) {
            inputs.add(textFile.nextInt());
        }
        int[][] matrix = new int[NO_OF_DAYS + 1][9];
        int sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += recurse(matrix, 0, inputs.get(i));
        }
        System.out.println(sum);
        //printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int recurse(int[][] matrix, int numberOfDays, int number) {
        if (matrix[numberOfDays][number] > 0) {
            return matrix[numberOfDays][number];
        }

        if (number + numberOfDays + 1 > NO_OF_DAYS) {
            matrix[numberOfDays][number] = 1;
            return 1;
        }
        else {
            matrix[numberOfDays][number] = recurse(matrix, number + numberOfDays + 1, 6) + recurse(matrix, number + numberOfDays + 1, 8);
            return matrix[numberOfDays][number];
        }
    }
}
