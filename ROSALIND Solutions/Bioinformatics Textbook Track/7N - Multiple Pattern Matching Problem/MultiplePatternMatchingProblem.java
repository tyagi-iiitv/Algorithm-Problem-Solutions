/**
 *  MULTIPLE PATTERN MATCHING PROBLEM
 *
 *  Find all occurrences of a collection of patterns in a text.
 *
 *  Given:  A string Text and a collection of strings Patterns.
 *  Return: All starting positions in Text where a string from Patterns appears as a substring.
 */

import java.util.*;
import java.io.*;
public class MultiplePatternMatchingProblem {
    public static void main( String[] args ) {
        // DEFINE "text" AND "patterns"!!!
        String text = "";
        String[] patterns = {};
        // DEFINE "text" AND "patterns"!!!
        int[] out = multiplePatternMatching(text,patterns);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static int[] multiplePatternMatching( String text, String[] patterns ) {
        String bwt = burrowsWheelerTransform(text);
        int[] arr = suffixArray(text);
        char[] last = bwt.toCharArray();
        CharNode[] first = new CharNode[last.length];
        for(int i = 0; i < first.length; ++i) {
            first[i] = new CharNode(text.charAt(i),i);
        }
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
            if(firstOccurrence[first[i].symbol] == -1) {
                firstOccurrence[first[i].symbol] = i;
            }
        }
        ArrayList<Integer> out = new ArrayList<>();
        for(int p = 0; p < patterns.length; ++p) {
            String pattern = patterns[p];
            int top = 0;
            int bot = last.length-1;
            while(top <= bot) {
                if(pattern.length() == 0) {
                    for(int i = top; i <= bot; ++i) {
                        out.add(i);
                    }
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
        int[] o = new int[out.size()];
        for(int i = 0; i < out.size(); ++i) {
            o[i] = arr[out.get(i)];
        }
        Arrays.sort(o);
        return o;
    }
    
    public static String burrowsWheelerTransform( String s ) {
        String[] m = burrowsWheelerMatrix(s);
        String wip = "";
        for(int i = 0; i < m.length; ++i) {
            wip += m[i].charAt(s.length()-1);
        }
        return wip;
    }
    
    public static String[] burrowsWheelerMatrix( String s ) {
        String[] m = new String[s.length()];
        for(int i = 0; i < m.length; ++i) {
            m[i] = s.substring(i,s.length()) + s.substring(0,i);
        }
        Arrays.sort(m);
        return m;
    }
    
    public static int[] suffixArray( String text ) {
        Integer[] out = new Integer[text.length()];
        for(int i = 0; i < out.length; ++i) {
            out[i] = i;
        }
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare( Integer i1, Integer i2 ) {
                String s1 = text.substring(i1,text.length());
                String s2 = text.substring(i2,text.length());
                return s1.compareTo(s2);
            }
        };
        Arrays.sort(out,comp);
        int[] o = new int[out.length];
        for(int i = 0; i < o.length; ++i) {
            o[i] = out[i];
        }
        return o;
    }
}

class CharNode implements Comparable {
    public char symbol;
    public int index;
    
    public CharNode( char s, int i ) {
        symbol = s;
        index = i;
    }
    
    public int compareTo( Object other ) {
        return symbol - ((CharNode)other).symbol;
    }
}