package aoc2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aoc2b {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> stepList = new ArrayList<>();
        ArrayList<String> commandList = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "\\src\\aoc2\\aoc2.txt");
        Scanner textfile = new Scanner(file);
        while (textfile.hasNextLine()) {
            String line = textfile.next();
            commandList.add(line);
            stepList.add(textfile.nextInt());
        }
        int x = 0;
        int depth = 0;
        int aim = 0;
        for (int i = 0; i < stepList.size(); i++) {
            if ("forward".equals(commandList.get(i).strip())) {
                x += stepList.get(i);
                depth += aim * stepList.get(i);
            } else if ("down".equals(commandList.get(i).strip())) {
                aim += stepList.get(i);
            } else {
                aim -= stepList.get(i);
            }

        }
        System.out.println(x * depth);
    }
}
