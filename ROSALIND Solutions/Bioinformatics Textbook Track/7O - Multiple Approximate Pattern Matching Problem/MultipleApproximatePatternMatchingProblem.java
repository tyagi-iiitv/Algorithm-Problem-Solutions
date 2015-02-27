/**
 *  MULTIPLE APPROXIMATE PATTERN MATCHING PROBLEM
 *
 *  Find all approximate occurrences of a collection of patterns in a text.
 *
 *  Given:  A string Text, a collection of strings Patterns, and an integer d.
 *  Return: All starting positions in Text where a string from Patterns appears as a substring with at most d mismatches.
 */

import java.util.*;
public class MultipleApproximatePatternMatchingProblem {
    public static void main( String[] args ) {
        // DEFINE "text", "patterns", AND "d"!!!
        String text = "";
        String[] patterns = {};
        int d = 0;
        // DEFINE "text", "patterns", AND "d"!!!
        ArrayList<Integer> out = multipleApproximatePatternMatching(text,patterns,d);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<Integer> multipleApproximatePatternMatching( String text, String[] patterns, int d ) {
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
        ArrayList<Integer> matches = new ArrayList<>();
        for(int num = 0; num < patterns.length; ++num) {
            String pattern = patterns[num];
            HashSet<Integer> seedLocations = seedDetection(last,arr,firstOccurrence,count,text,pattern,d);
            Iterator it = seedLocations.iterator();
            while(it.hasNext()) {
                Integer seedIndex = (Integer)it.next();
                if(seedExtension(text,pattern,seedIndex,d)) {
                    matches.add(seedIndex);
                }
            }
        }
        Collections.sort(matches);
        return matches;
    }
    
    public static boolean seedExtension( String word, String pattern, int seedIndex, int d ) {
        int count = 0;
        for(int i = 0; i < pattern.length(); ++i) {
            if(seedIndex + i >= word.length() || seedIndex + i < 0) {
                return false;
            }
            if(pattern.charAt(i) != word.charAt(seedIndex+i)) {
                if(++count > d) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static HashSet<Integer> seedDetection( char[] bwt, int[] suffixArray, int[] firstOccurrence, int[][] count, String word, String pattern, int d ) {
        int k = pattern.length() / (d+1);
        String[] seeds = new String[pattern.length()-k+1];
        for(int i = 0; i <= pattern.length()-k; ++i) {
            seeds[i] = pattern.substring(i,i+k);
        }
        HashSet<Integer> seedLocations = new HashSet<>();
        for(int i = 0; i < seeds.length; ++i) {
            String seed = seeds[i];
            ArrayList<Integer> shiftedMatches = multiPatternMatchBW(bwt,suffixArray,firstOccurrence,count,seed);
            for(int j = 0; j < shiftedMatches.size(); ++j) {
                int mod = shiftedMatches.get(j).intValue() - (k*i);
                shiftedMatches.set(j,mod);
                if(i < 0 || i + pattern.length() > word.length()) {
                    shiftedMatches.remove(j--);
                }
            }
            for(Integer m : shiftedMatches) {
                seedLocations.add(m);
            }
        }
        return seedLocations;
    }
    
    public static ArrayList<Integer> multiPatternMatchBW( char[] last, int[] arr, int[] firstOccurrence, int[][] count, String pattern ) {
        int top = 0;
        int bot = last.length-1;
        ArrayList<Integer> out = new ArrayList<>();
        while(top <= bot) {
            if(pattern.length() == 0) {
                for(int i = top; i <= bot; ++i) {
                    out.add(arr[i]);
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
        return out;
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