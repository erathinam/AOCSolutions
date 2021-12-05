package aoc5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Aoc5b {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "\\src\\aoc5\\aoc5.txt");
        Scanner textfile = new Scanner(file).useDelimiter("\r\n");
        int rowSize = findMaxCount(file) + 1;
        int[][] matrix = new int[rowSize][rowSize];
        while (textfile.hasNextLine()) {
            String[] coordinates = textfile.next().split(" -> ");
            String[] p1Coordinates = coordinates[0].split(",");
            String[] p2Coordinates = coordinates[1].split(",");
            if (p1Coordinates[0].equals(p2Coordinates[0])) {
                incrementX(matrix, Integer.parseInt(p1Coordinates[1]), Integer.parseInt(p2Coordinates[1]), Integer.parseInt(p1Coordinates[0]));
            } else if (p1Coordinates[1].equals(p2Coordinates[1])) {
                incrementY(matrix, Integer.parseInt(p1Coordinates[0]), Integer.parseInt(p2Coordinates[0]), Integer.parseInt(p1Coordinates[1]));
            } else {
                changeXandY(matrix, p1Coordinates, p2Coordinates);
            }
        }

        System.out.println(printMatrix(matrix, rowSize));
    }

    public static void incrementX(int[][] matrix, int x1, int x2, int y) {
        int i = Math.min(x1, x2);
        int j = Math.max(x1, x2);
        for (; i <= j; i++) {
            matrix[i][y] += 1;
        }
    }

    public static void incrementY(int[][] matrix, int y1, int y2, int x) {
        int i = Math.min(y1, y2);
        int j = Math.max(y1, y2);
        for (; i <= j; i++) {
            matrix[x][i] += 1;
        }
    }
    public static void changeXandY(int[][] matrix, String[] a, String[] b) {
        int[] co0 = new int[2];
        int [] co1 = new int[2];
        int[] pointA;
        int[] pointB;
        co0[0] = Integer.parseInt(a[0]);
        co0[1] = Integer.parseInt(a[1]);
        co1[0] = Integer.parseInt(b[0]);
        co1[1] = Integer.parseInt(b[1]);
        if (co0[1] < co1[1]) {
            pointA = co0;
            pointB = co1;
        } else {
            pointA = co1;
            pointB = co0;
        }

        if (pointA[0] > pointB[0]) {
            for (int i = pointA[1], j = pointA[0]; i<= pointB[1] && j>= pointB[0]; i++, j--) {
                matrix[i][j] += 1;
            }
        } else {
            for (int i = pointA[1], j = pointA[0]; i<= pointB[1] && j <= pointB[0]; i++, j++) {
                matrix[i][j] += 1;
            }
        }
    }

    public static int printMatrix(int[][] matrix, int rowSize) {
        int totalCount = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (matrix[i][j] > 1) {
                    totalCount += 1;
                }
            }
        }
        return totalCount;
    }
    public static int findMaxCount(File file) throws FileNotFoundException {
        Scanner textfile = new Scanner(file).useDelimiter("\r\n");
        int max = 0;
        while (textfile.hasNextLine()) {
            String[] coordinates = textfile.next().split(" -> ");
            String [] p1Coordinates = coordinates[0].split(",");
            String [] p2Coordinates = coordinates[1].split(",");
            max = Integer.max(max, Integer.parseInt(p1Coordinates[0]));
            max = Integer.max(max, Integer.parseInt(p2Coordinates[0]));
            max = Integer.max(max, Integer.parseInt(p1Coordinates[1]));
            max = Integer.max(max, Integer.parseInt(p2Coordinates[1]));
        }
        return max;
    }
}
