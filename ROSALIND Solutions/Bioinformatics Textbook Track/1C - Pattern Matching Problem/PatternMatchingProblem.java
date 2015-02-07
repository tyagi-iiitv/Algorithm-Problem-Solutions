/**
 *  In this problem, we ask a simple question: how many times can one string
 *  occur as a substring of another? Recall from "Frequent Words Problem" that
 *  different occurrences of a substring can overlap with each other. For
 *  example, ATA occurs three times in CGATATATCCATAG.
 */

/**
 *  PATTERN MATCHING PROBLEM
 *
 *  Find all occurrences of a pattern in a string.
 *
 *  Given:  Strings Pattern and Genome.
 *  Return: All starting positions in Genome where Pattern appears as a substring. Use 0-based indexing.
 */

import java.util.*;
public class PatternMatchingProblem {
    public static void main( String[] args ) {
        // DEFINE "pattern" AND "genome"!!!
        String pattern = "";
        String genome  = "";
        // DEFINE "pattern" AND "genome"!!!
        
        ArrayList<Integer> out = patternMatch(pattern,genome);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<Integer> patternMatch( String pattern, String genome ) {
        ArrayList<Integer> pos = new ArrayList<>();
        for(int i = 0; i <= genome.length() - pattern.length(); ++i) {
            if(genome.substring(i,i+pattern.length()).equals(pattern)) {
                pos.add(i);
            }
        }
        return pos;
    }
}