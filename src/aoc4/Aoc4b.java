package aoc4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Aoc4b {

    public static final int BOARDS = 100;
    public static final int ROWSIZE = 5;

    public static void main(String[] args) throws FileNotFoundException {
    File file = new File(System.getProperty("user.dir") + "\\src\\aoc4\\aoc4.txt");
    Scanner textfile = new Scanner(file);
    int[] bingoOrder = null;
    int[][][] boards = new int[BOARDS][ROWSIZE][ROWSIZE];
    int[][][] bingoBoards = new int[BOARDS][ROWSIZE][ROWSIZE];

    while (textfile.hasNextLine()) {
        if (bingoOrder == null) {
            bingoOrder = Arrays.stream(textfile.next().split(",")).mapToInt(Integer::parseInt).toArray();
            continue;
        }
        for (int i = 0; i < BOARDS; i++) {
            for (int j = 0; j < ROWSIZE; j++) {
                for (int k = 0; k < ROWSIZE; k++) {
                    boards[i][j][k] = Integer.parseInt(textfile.next());
                }
            }
        }
    }
    Integer lastBoardWon = null;
    int[] listOfBoardsWon = new int[BOARDS];

        for (int k : bingoOrder) {
            lastBoardWon = addBingoNumber(k, boards, bingoBoards, listOfBoardsWon);
            int boardsWon = 0;
            for (int i : listOfBoardsWon) {
                boardsWon += i == 1 ? 1 : 0;
            }
            if (boardsWon == BOARDS) {
                System.out.println(findBingoScore(k, boards[lastBoardWon], bingoBoards[lastBoardWon]));
                break;
            }

        }
    }
    public static Integer addBingoNumber(int number, int[][][] boards, int[][][] bingoBoards, int[] listOfBoardsWon) {
    Integer lastBoardWon = null;
        for (int i = 0; i < boards.length; i++) {
            if (listOfBoardsWon[i] == 1) {
                continue;
            }
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (boards[i][j][k] == number) {
                        bingoBoards[i][j][k] = 1;
                    }
                }
            }
            if (checkBingo(bingoBoards[i])) {
                listOfBoardsWon[i] = 1;
                lastBoardWon = i;
            }
        }
        return lastBoardWon;
    }

    public static boolean checkBingo(int[][] bingoBoard) {

        for (int i = 0; i < ROWSIZE; i++) {
            boolean rowBoolean = true;
            for (int j = 0; j < ROWSIZE; j++) {
                if (bingoBoard[i][j] != 1) {
                    rowBoolean = false;
                    break;
                }
            }
            if (rowBoolean) {
                return true;
            }
        }

        for (int i = 0; i < ROWSIZE; i++) {
            boolean columnBoolean = true;
            for (int j = 0; j < ROWSIZE; j++) {
                if (bingoBoard[j][i] != 1) {
                    columnBoolean = false;
                    break;
                }
            }
            if (columnBoolean) {
                return true;
            }
        }
        return false;
    }

    public static int findBingoScore(int bingoNumber, int[][] board, int[][] bingoBoard) {
        int sum = 0;
        for (int i = 0; i < ROWSIZE;i++) {
            for (int j = 0; j < ROWSIZE; j++) {
                if (bingoBoard[i][j] == 0) {
                    sum += board[i][j];
                }
            }
        }
        return sum * bingoNumber;
    }
    public static void printBoards(int[][][] boards) {
        for (int i = 0; i < BOARDS; i++) {
            for (int j = 0; j < ROWSIZE; j++) {
                for (int k = 0; k < ROWSIZE; k++) {
                    System.out.print(boards[i][j][k] + " ");
                }
                System.out.println("");
            }
            System.out.println("=====================================");
        }
    }
}
