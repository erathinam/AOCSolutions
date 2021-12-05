package aoc1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aoc1b {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        File file = new File(System.getProperty("user.dir") + "\\src\\aoc1\\aoc1.txt");
        Scanner textfile = new Scanner(file);
        while (textfile.hasNextLine()) {
            int line = textfile.nextInt();
            list.add(line);
        }
        int start = 0, end = start + 3;
        int sum, prevSum = 0;
        int increased = 0;
        while (end < list.size()) {
            sum = list.get(start) + list.get(start + 1) + list.get(start + 2);
            if (sum > prevSum) {
                increased +=1;
            }
            prevSum = sum;
            start +=1;
            end +=1;
        }
        System.out.println(increased);
    }
}