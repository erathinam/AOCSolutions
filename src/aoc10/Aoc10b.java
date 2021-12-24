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

public class Aoc10b {
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
        Map<Character, Integer> individualScore = new HashMap<>();
        individualScore.put(')', 1);
        individualScore.put(']', 2);
        individualScore.put('}', 3);
        individualScore.put('>', 4);
        List<Integer> finalScores = new ArrayList<>();
        while (textFile.hasNextLine()) {
            List<Character> paranthesis = new ArrayList<>();
            String s = textFile.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (openingBraces.contains(s.charAt(i))) {
                    paranthesis.add(matchingPhrases.get(s.charAt(i)));
                } else {
                    if (paranthesis.size() == 0 || !paranthesis.get(paranthesis.size() - 1).equals(s.charAt(i))) {
                        break;
                    } else {
                        paranthesis.remove(paranthesis.size() - 1);
                    }
                }
                //Take care incomplete
                int score = 0;
                if (i == s.length() - 1) {
                    for (int j = paranthesis.size() - 1; j >=0; j--) {
                        score *= 5;
                        score += individualScore.get(paranthesis.get(j));
                    }
//                    System.out.println(score);
                    finalScores.add(score);
                }
            }
        }
        Collections.sort(finalScores);
        System.out.println(finalScores.get(finalScores.size()/2));
    }
}
