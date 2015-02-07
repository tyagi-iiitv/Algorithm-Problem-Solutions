/**
 *  This is the first problem in a collection of "code challenges" to accompany
 *  Bioinformatics Algorithms: An Active-Learning Approach by Phillip Compeau &
 *  Pavel Pevzner.
 *
 *  A k-mer is a string of length k. We define Count(Text, Pattern) as the
 *  number of times that a k-mer Pattern appears as a substring of Text. For
 *  example:    Count(ACAACTATGCATACTATCGGGAACTATCCT,ACTAT)=3
 *
 *  We note that Count(CGATATATCCATAG, ATA) is equal to 3 (not 2) since we
 *  should account for overlapping occurrences of Pattern in Text.
 *
 *  We say that Pattern is a most frequent k-mer in Text if it maximizes
 *  Count(Text, Pattern) among all k-mers. For example, "ACTAT" is a most
 *  frequent 5-mer in "ACAACTATGCATCACTATCGGGAACTATCCT", and "ATA" is a most
 *  frequent 3-mer of "CGATATATCCATAG".
 */

/**
 *  FREQUENT WORDS PROBLEM
 *
 *  Find the most frequent k-mers in a string.
 *
 *  Given:  A DNA string Text and an integer k.
 *  Return: All most frequent k-mers in Text (in any order).
 */

import java.util.*;
public class FrequentWordsProblem {
    public static void main( String[] args ) {
        // DEFINE "text" AND "k"!!!
        String text = "";
        int k = 0;
        // DEFINE "text" AND "k"!!!
        
        ArrayList<String> out = frequentWords(text,k);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<String> frequentWords( String text, int k ) {
        ArrayList<String> set = new ArrayList<>();
        int[] count = new int[text.length() - k + 1];
        String[] patterns = new String[count.length];
        int maxCount = 0;
        
        for(int i = 0; i < count.length; ++i) { // Possibly < vs. <=
            patterns[i] = text.substring(i,i+k);
            count[i] = patternCount(text,patterns[i]);
            if(count[i] > maxCount) {
                maxCount = count[i];
            }
        }
        
        for(int i = 0; i < count.length; ++i) {
            if(count[i] == maxCount && !set.contains(patterns[i])) {
                set.add(patterns[i]);
            }
        }
        
        return set;
    }
    
    public static int patternCount( String text, String pattern ) {
        int count = 0;
        for(int i = 0; i <= text.length() - pattern.length(); ++i) {
            if(text.substring(i,i+pattern.length()).equals(pattern)) {
                ++count;
            }
        }
        return count;
    }
}