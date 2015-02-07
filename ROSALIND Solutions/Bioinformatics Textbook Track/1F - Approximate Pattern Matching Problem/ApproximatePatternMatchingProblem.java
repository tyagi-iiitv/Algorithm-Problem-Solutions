/**
 *  We say that position i in k-mers p_1 … p_k and q_1 … q_k is a mismatch if
 *  p_i != q_i. For example, CGAAT and CGGAC have two mismatches.
 */

/**
 *  APPROXIMATE PATTERN MATCHING PROBLEM
 *
 *  Find all approximate occurrences of a pattern in a string.
 *
 *  Given:  Strings Pattern and Text along with an integer d.
 *  Return: All starting positions where Pattern appears as a substring of Text with at most d mismatches.
 */

import java.util.*;
public class ApproximatePatternMatchingProblem {
    public static void main( String[] args ) {
        // DEFINE "Pattern", "Text", AND "d"!!!
        String pattern = "";
        String text = "";
        int d = 0;
        // DEFINE "Pattern", "Text", AND "d"!!!
        
        int[] out = approximatePatternMatching(pattern,text,d);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static int[] approximatePatternMatching( String pattern, String text, int d ) {
        ArrayList<Integer> pos = new ArrayList<>();
        for(int i = 0; i <= text.length() - pattern.length(); ++i) {
            if(hammingDistance(pattern,text.substring(i,i+pattern.length())) <= d)
                pos.add(i);
        }
        int[] posInt = new int[pos.size()];
        for(int i = 0; i < posInt.length; ++i) {
            posInt[i] = pos.get(i).intValue();
        }
        return posInt;
    }
    
    public static int hammingDistance( String p, String q ) {
        if(p.length() != q.length()) {
            System.out.println("P and Q are different lengths!");
            System.exit(-1);
        }
        int mismatch = 0;
        for(int i = 0; i < p.length(); ++i) {
            if(p.charAt(i) != q.charAt(i))
                ++mismatch;
        }
        return mismatch;
    }
}