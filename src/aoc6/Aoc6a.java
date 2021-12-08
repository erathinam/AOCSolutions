package aoc6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc6a {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc6\\aoc6.txt")).useDelimiter(",");
        List<Integer> inputs = new ArrayList<>();
        while(textFile.hasNextLine()) {
            inputs.add(textFile.nextInt());
        }
        int noOfDays = 256;
        for (int i = 0; i < noOfDays; i++) {
            List<Integer> copyList = new ArrayList<>();
            for (int j = 0; j < inputs.size(); j++) {
                if (inputs.get(j) > 0) {
                    copyList.add(inputs.get(j)-1);
                }
                else {
                    copyList.add(6);
                    copyList.add(8);
                }
            }
            inputs = copyList;
            System.out.println("Day " + i + "No of fishes" + inputs.size());
        }
        System.out.println(inputs.size());
    }
}
