package aoc8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Aoc8b {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc8\\aoc8.txt"));
        List<List<String>> inputs = new ArrayList<>();
        List<List<String>> outputs = new ArrayList<>();
        while(textFile.hasNextLine()) {
            String line = textFile.nextLine().strip();
            String[] splits = line.split("\\|");
            // sorting the inputs and outputs
            inputs.add(Arrays.stream(splits[0].split(" ")).map(input -> {
                char[] charArray = input.toCharArray();
                Arrays.sort(charArray);
                return new String(charArray);
            }).collect(Collectors.toList()));
            outputs.add(Arrays.stream(splits[1].split(" ")).filter(string -> string.length() > 0).map(output -> {
                char[] charArray = output.toCharArray();
                Arrays.sort(charArray);
                return new String(charArray);
            }).collect(Collectors.toList()));
        }
        Map<Integer, List<Integer>> characterMap = new HashMap<>();
        characterMap.put(2, Arrays.asList(1));
        characterMap.put(3, Arrays.asList(7));
        characterMap.put(4, Arrays.asList(4));
        characterMap.put(5, Arrays.asList(2, 3, 5));
        characterMap.put(6, Arrays.asList(6, 9, 0));
        characterMap.put(7, Arrays.asList(8));
        int finalScore = 0;
        for (int i = 0; i < inputs.size(); i++) {
            List<String> input = inputs.get(i);
            List<String> output = outputs.get(i);
         Map<Integer, List<String>> answerMap = new HashMap<>();
         for (String character: input) {
             if (answerMap.containsKey(character.length())) {
                 List<String> existingMap = answerMap.get(character.length());
                 existingMap.add(character);
                 answerMap.put(character.length(), existingMap);
             } else {
                 List<String> newMap = new ArrayList<>();
                 newMap.add(character);
                 answerMap.put(character.length(), newMap);
             }
         }
            Map<String, Integer> finalAnswer = findAnswer(answerMap);
//            for(Map.Entry entry: finalAnswer.entrySet()) {
//                System.out.println(entry.getKey() + " " + entry.getValue());
//            }

         int score = 0;
         for (int j = 0; j < output.size(); j++) {
             //System.out.println("output answer for " + output.get(j) + " is " + finalAnswer.get(output.get(j)));
             score = score * 10 + finalAnswer.get(output.get(j));
         }
         finalScore += score;
//         System.out.println(score);
        }
        System.out.println(finalScore);
    }

    public static Map<String, Integer> findAnswer(Map<Integer, List<String>> answerMap) {
        Map<String, Integer> finalAnswer = new HashMap<>();
        finalAnswer.put(answerMap.get(2).get(0), 1);
        finalAnswer.put(answerMap.get(3).get(0), 7);
        finalAnswer.put(answerMap.get(4).get(0), 4);

        String RC = answerMap.get(2).get(0);
        String T = findDiff(RC, answerMap.get(3).get(0));
        String LMC  = findDiff(RC, answerMap.get(4).get(0));

        // finding 5
        String fiveString = sortString((T + LMC));
        int toBeRemoved = 0;
        for (int i = 0; i < answerMap.get(5).size(); i++) {
            if (containsCharacters(answerMap.get(5).get(i), fiveString)) {
                finalAnswer.put(answerMap.get(5).get(i), 5);
                toBeRemoved = i;
                break;
            }
        }
        answerMap.get(5).remove(toBeRemoved);
        // finding 3
        String threeString = sortString(T + RC);
        for (int i = 0; i < answerMap.get(5).size(); i++) {
            if (containsCharacters(answerMap.get(5).get(i), threeString)) {
                finalAnswer.put(answerMap.get(5).get(i), 3);
                toBeRemoved = i;
                break;
            }
        }
        answerMap.get(5).remove(toBeRemoved);

        //finding 2
        finalAnswer.put(answerMap.get(5).get(0), 2);

        //finding 9
        String nineString = sortString(T + LMC + RC);
        for (int i = 0; i < answerMap.get(6).size(); i++) {
            if (containsCharacters(answerMap.get(6).get(i), nineString)) {
                finalAnswer.put(answerMap.get(6).get(i), 9);
                toBeRemoved = i;
                break;
            }
        }
        answerMap.get(6).remove(toBeRemoved);

        //finding 6
        String sixString = sortString(T + LMC);
        for (int i = 0; i < answerMap.get(6).size(); i++) {
            if (containsCharacters(answerMap.get(6).get(i), sixString)) {
                finalAnswer.put(answerMap.get(6).get(i), 6);
                toBeRemoved = i;
                break;
            }
        }
        answerMap.get(6).remove(toBeRemoved);

        //finding 0
        finalAnswer.put(answerMap.get(6).get(0), 0);
        finalAnswer.put("abcdefg", 8);

        return finalAnswer;
    }

    public static String findDiff(String s1, String s2) {
        String biggest = s1.length() > s2.length() ? s1 : s2;
        String smallest = s1.length() <= s2.length() ? s1 : s2;
        StringBuilder sb = new StringBuilder();
        char[] biggestArray = biggest.toCharArray();
        for (int i = 0; i < biggest.length(); i++) {
            if (!smallest.contains(String.valueOf(biggestArray[i]))) {
                sb.append(biggestArray[i]);
            }
        }
        return sb.toString();
    }

    public static String sortString(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return String.valueOf(c);
    }

    public static boolean containsCharacters(String s1, String s2) {
        Set<Character> charsSet = s1.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        for (int i = 0; i < s2.length(); i++) {
            if (!charsSet.contains(s2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int findDistance(int target, List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += Math.abs(target - numbers.get(i));
        }
        return sum;
    }
}
