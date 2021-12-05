package aoc3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc3b {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> binaryNumbers = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "\\src\\aoc3\\aoc3.txt");
        Scanner textfile = new Scanner(file);
        while (textfile.hasNextLine()) {
            String line = textfile.next();
            binaryNumbers.add(line);
        }
        calculateAnswers(binaryNumbers);
    }

    public static void calculateAnswers(List<String> binaryNumbers) {
        List<String> copy = binaryNumbers;
        String oxygenRatingBinary = null;
        String co2ScrubberRatingBinary = null;
        for (int i = 0; i  < binaryNumbers.get(0).length(); i++) {
            List<String> zeroList = new ArrayList<>();
            List<String> oneList = new ArrayList<>();
            for (String numbers: copy) {
                if (numbers.charAt(i) == '0') {
                    zeroList.add(numbers);
                } else {
                    oneList.add(numbers);
                }
           }
            if (zeroList.size() > oneList.size()) {
                copy = zeroList;
            }
            else {
                copy = oneList;
            }
            if (copy.size() == 1) {
                oxygenRatingBinary = copy.get(0);
                break;
            }
        }
        copy = binaryNumbers;
        for (int i = 0; i  < binaryNumbers.get(0).length(); i++) {
            List<String> zeroList = new ArrayList<>();
            List<String> oneList = new ArrayList<>();
            for (String numbers: copy) {
                if (numbers.charAt(i) == '0') {
                    zeroList.add(numbers);
                } else {
                    oneList.add(numbers);
                }
            }
            if (zeroList.size() > oneList.size()) {
                copy = oneList;
            }
            else {
                copy = zeroList;
            }
            if (copy.size() == 1) {
                co2ScrubberRatingBinary = copy.get(0);
                break;
            }
        }
        int oxygenGeneratorRating = findDecimal(new StringBuilder(oxygenRatingBinary));
        int co2ScrubberRating = findDecimal(new StringBuilder(co2ScrubberRatingBinary));
        System.out.println(oxygenGeneratorRating * co2ScrubberRating);
    }

    public static int findDecimal(StringBuilder binary) {
        int result = 0;
        for (int y = 0; y < binary.length(); y++) {
            result += Math.pow(2, y) * Character.getNumericValue(binary.charAt(binary.length() - 1 - y));
        }
        return result;
    }
}
