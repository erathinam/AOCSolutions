package aoc7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc7a {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc7\\aoc7.txt")).useDelimiter(",");
        List<Integer> inputs = new ArrayList<>();
        int least = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(textFile.hasNextLine()) {
            int number = textFile.nextInt();
            least = Math.min(least, number);
            max = Math.max(max, number);
            inputs.add(number);
        }
        int cost = Integer.MAX_VALUE;
        for (int i = least; i <= max; i++) {
            cost = Math.min(cost, findDistance(i, inputs));
        }
        System.out.println(cost);

    }

    public static int findDistance(int target, List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += Math.abs(target - numbers.get(i));
        }
        return sum;
    }
}
