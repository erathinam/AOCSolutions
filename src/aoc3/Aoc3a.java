package aoc3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc3a {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> binaryNumbers = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "\\src\\aoc3\\aoc3.txt");
        Scanner textfile = new Scanner(file);
        while (textfile.hasNextLine()) {
            String line = textfile.next();
            binaryNumbers.add(line);
        }

        StringBuilder gammaRateBinary = new StringBuilder();
        StringBuilder epsilonRateBinary = new StringBuilder();
        for (int i = 0; i  < binaryNumbers.get(0).length(); i++) {
            int zeroes = 0;
            int ones = 0;
            for (String binaryNumber : binaryNumbers) {
                if (binaryNumber.charAt(i) == '0') {
                    zeroes += 1;
                } else {
                    ones += 1;
                }
            }
            gammaRateBinary.append(ones > zeroes ? '1' : '0');
            epsilonRateBinary.append(ones > zeroes ? '0' : '1');
        }

        int gammaRateDecimal = findDecimal(gammaRateBinary);
        int epsilonRateDecimal = findDecimal(epsilonRateBinary);
        System.out.println(gammaRateDecimal * epsilonRateDecimal);
    }

    public static int findDecimal(StringBuilder binary) {
        int result = 0;
        for (int y = 0; y < binary.length(); y++) {
            result += Math.pow(2, y) * Character.getNumericValue(binary.charAt(binary.length() - 1 - y));
        }
        return result;
    }
}
