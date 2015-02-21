/**
 *  BWMATCHING
 *
 *  Implement BWMatching
 *
 *  Given:  A string BWT(Text), followed by a collection of strings Patterns.
 *  Return: A list of integers, where the i-th integer corresponds to the number of substring matches of the i-th member of Patterns in Text.
 */

import java.util.*;
public class BWMatching {
    public static void main( String[] args ) {
        // DEFINE "bwt" AND "patterns"!!!
        String bwt = "";
        String[] patterns = {};
        // DEFINE "bwt" AND "patterns"!!!
        for(int i = 0; i < patterns.length; ++i) {
            System.out.print(bwMatching(bwt,patterns[i]) + " ");
        }
    }
    
    public static int bwMatching( String bwt, String pattern ) {
        char[] last = bwt.toCharArray();
        char[] first = Arrays.copyOf(last,last.length);
        Arrays.sort(first);
        int[] subsFirst = new int[first.length];
        subsFirst[0] = 1;
        for(int i = 1; i < subsFirst.length; ++i) {
            if(first[i] == first[i-1]) {
                subsFirst[i] = subsFirst[i-1] + 1;
            }
            else {
                subsFirst[i] = 1;
            }
        }
        int[] subsLast = new int[first.length];
        int[] counts = new int[256];
        for(int i = 0; i < subsLast.length; ++i) {
            subsLast[i] = ++counts[last[i]];
        }
        int[] lastToFirst = new int[first.length];
        for(int i = 0; i < lastToFirst.length; ++i) {
            for(int j = 0; j < first.length; ++j) {
                if(first[j] == last[i] && subsFirst[j] == subsLast[i]) {
                    lastToFirst[i] = j;
                    break;
                }
            }
        }
        int top = 0;
        int bot = first.length-1;
        while(top <= bot) {
            if(pattern.length() == 0) {
                return bot - top + 1;
            }
            char symbol = pattern.charAt(pattern.length()-1);
            pattern = pattern.substring(0,pattern.length()-1);
            int topIndex = -1;
            int botIndex = -1;
            for(int i = top; i <= bot; ++i) {
                if(last[i] == symbol) {
                    if(topIndex == -1) {
                        topIndex = i;
                        botIndex = i;
                    }
                    else {
                        botIndex = i;
                    }
                }
            }
            if(topIndex == -1 || botIndex == -1) {
                return 0;
            }
            top = lastToFirst[topIndex];
            bot = lastToFirst[botIndex];
        }
        return 0;
    }
}