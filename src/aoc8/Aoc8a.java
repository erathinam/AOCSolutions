package aoc8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Aoc8a {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc8\\aoc8.txt"));
        List<List<String>> inputs = new ArrayList<>();
        List<List<String>> outputs = new ArrayList<>();
        while(textFile.hasNextLine()) {
            String line = textFile.nextLine().strip();
            String[] splits = line.split("\\|");
            inputs.add(new ArrayList<>(Arrays.asList(splits[0].split(" "))));
            outputs.add(new ArrayList<>(Arrays.asList(splits[1].split(" "))));
        }
        int count = 0;
        for (List<String>output: outputs) {
            for (String output1: output) {
                if (output1.strip().length() == 4 || output1.strip().length() == 7 || output1.strip().length() == 2 || output1.strip().length() == 3) {
                    count++;
                }
            }
        }
        System.out.println(count);

    }

    public static int findDistance(int target, List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += Math.abs(target - numbers.get(i));
        }
        return sum;
    }
}
