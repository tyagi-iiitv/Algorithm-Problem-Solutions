/**
 *  FITTING ALIGNMENT PROBLEM
 *
 *  Construct a highest-scoring fitting alignment between two strings.
 *
 *  Given:  Two DNA strings v and w, where v has length at most 10000 and w has length at most 1000.
 *  Return: The maximum score of a fitting alignment of v and w, followed by a fitting alignment achieving this maximum score. Use the simple scoring method in which matches count +1 and both the mismatch and indel penalties are equal to 1. (If multiple fitting alignments achieving the maximum score exist, you may return any one.)
 */

import java.util.*;
public class FittingAlignmentProblem {
    public static void main( String[] args ) {
        // DEFINE "v" AND "w"!!!
        String v = "";
        String w = "";
        // DEFINE "v" AND "w"!!!
        String[] out = fittingAlignment(v,w);
        System.out.println(out[0]);
        System.out.println(out[1]);
        System.out.println(out[2]);
    }
    
    public static String[] fittingAlignment( String s1, String s2 ) {
        // s is longer, t is shorter
        String s = null;
        String t = null;
        if(s1.length() > s2.length()) {
            s = s1;
            t = s2;
        }
        else {
            s = s2;
            t = s1;
        }
        int[][] S = new int[s.length()+1][t.length()+1];
        int[][] backtrack = new int[s.length()+1][t.length()+1];
        
        for(int i = 1; i <= s.length(); ++i) {
            for(int j = 1; j <= t.length(); ++j) {
                int opt1 = S[i-1][j] - 1;
                int opt2 = S[i][j-1] - 1;
                int opt3 = S[i-1][j-1];
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    ++opt3;
                }
                else {
                    --opt3;
                }
                
                S[i][j] = opt1;
                backtrack[i][j] = 1;
                if(opt2 > S[i][j]) {
                    S[i][j] = opt2;
                    backtrack[i][j] = 2;
                }
                if(opt3 > S[i][j]) {
                    S[i][j] = opt3;
                    backtrack[i][j] = 3;
                }
            }
        }
        
        int j = t.length();
        int max = Integer.MIN_VALUE;
        int i = -1;
        for(int x = t.length(); x < S.length; ++x) {
            if(S[x][j] > max) {
                max = S[x][j];
                i = x;
            }
        }
        String[] out = new String[3];
        out[0] = "" + max;
        out[1] = "";
        out[2] = "";
        while(j > 0) {
            if(backtrack[i][j] == 1) {
                out[1] = s.charAt(i-- - 1) + out[1];
                out[2] = '-' + out[2];
            }
            else if(backtrack[i][j] == 2) {
                out[1] = '-' + out[1];
                out[2] = t.charAt(j-- - 1) + out[2];
            }
            else if(backtrack[i][j] == 3) {
                out[1] = s.charAt(i-- - 1) + out[1];
                out[2] = t.charAt(j-- - 1) + out[2];
            }
        }
        return out;
    }
}