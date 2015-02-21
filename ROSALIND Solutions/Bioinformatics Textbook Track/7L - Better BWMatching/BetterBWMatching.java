/**
 *  BETTER BWMATCHING
 *
 *  Implement BetterBWMatching
 *
 *  Given:  A string BWT(Text), followed by a collection of strings Patterns.
 *  Return: A list of integers, where the i-th integer corresponds to the number of substring matches of the i-th member of Patterns in Text.
 */

import java.util.*;
public class BetterBWMatching {
    public static void main( String[] args ) {
        // DEFINE "bwt" AND "patterns"!!!
        String bwt = "";
        String[] patterns = {};
        // DEFINE "bwt" AND "patterns"!!!
        int[] out = multiBetterBwMatching(bwt,patterns);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static int[] multiBetterBwMatching( String bwt, String[] patterns ) {
        char[] last = bwt.toCharArray();
        char[] first = Arrays.copyOf(last,last.length);
        Arrays.sort(first);
        int[][] count = new int[last.length+1][256];
        for(int i = 1; i < count.length; ++i) {
            char c = last[i-1];
            for(int j = 0; j < 256; ++j) {
                count[i][j] = count[i-1][j];
            }
            ++count[i][c];
        }
        int[] firstOccurrence = new int[256];
        for(int i = 0; i < 256; ++i) {
            firstOccurrence[i] = -1;
        }
        for(int i = 0; i < first.length; ++i) {
            if(firstOccurrence[first[i]] == -1) {
                firstOccurrence[first[i]] = i;
            }
        }
        int[] out = new int[patterns.length];
        for(int p = 0; p < patterns.length; ++p) {
            String pattern = patterns[p];
            int top = 0;
            int bot = last.length-1;
            while(top <= bot) {
                if(pattern.length() == 0) {
                    out[p] = bot - top + 1;
                    break;
                }
                char symbol = pattern.charAt(pattern.length()-1);
                pattern = pattern.substring(0,pattern.length()-1);
                boolean found = false;
                for(int i = top; i <= bot; ++i) {
                    if(last[i] == symbol) {
                        top = firstOccurrence[symbol] + count[top][symbol];
                        bot = firstOccurrence[symbol] + count[bot+1][symbol] - 1;
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    break;
                }
            }
        }
        return out;
    }
}