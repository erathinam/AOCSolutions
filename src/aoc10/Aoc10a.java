package aoc10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Aoc10a {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(System.getProperty("user.dir") + "\\src\\aoc10\\aoc10.txt"));
        int totalScore = 0;
        Set<Character> openingBraces = new HashSet<>();
        Set<Character> closingBraces = new HashSet<>();
        openingBraces.addAll(Arrays.asList('[', '(', '{', '<'));
        closingBraces.addAll(Arrays.asList(']', ')', '}', '>'));
        Map<Character, Character> matchingPhrases = new HashMap<>();
        matchingPhrases.put('(', ')');
        matchingPhrases.put('[', ']');
        matchingPhrases.put('{', '}');
        matchingPhrases.put('<', '>');
        Map<Character, Integer> finalCount = new HashMap<>();
        finalCount.put(']', 0);
        finalCount.put('}', 0);
        finalCount.put(')', 0);
        finalCount.put('>', 0);
        while (textFile.hasNextLine()) {
            List<Character> paranthesis = new ArrayList<>();
            String s = textFile.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (openingBraces.contains(s.charAt(i))) {
                    paranthesis.add(matchingPhrases.get(s.charAt(i)));
                } else {
                    if (paranthesis.size() == 0 || !paranthesis.get(paranthesis.size() - 1).equals(s.charAt(i))) {
                        finalCount.put(s.charAt(i), finalCount.get(s.charAt(i)) + 1);
                        break;
                    } else {
                        paranthesis.remove(paranthesis.size() - 1);
                    }
                }
            }
        }
        for (Map.Entry<Character, Integer> entry : finalCount.entrySet()) {
            if (entry.getKey().equals(')')) {
                totalScore += 3 * entry.getValue();
            }
            if (entry.getKey().equals(']')) {
                totalScore += 57 * entry.getValue();
            }
            if (entry.getKey().equals('}')) {
                totalScore += 1197 * entry.getValue();
            }
            if (entry.getKey().equals('>')) {
                totalScore += 25137 * entry.getValue();
            }
        }
        System.out.println(totalScore);
    }
}
