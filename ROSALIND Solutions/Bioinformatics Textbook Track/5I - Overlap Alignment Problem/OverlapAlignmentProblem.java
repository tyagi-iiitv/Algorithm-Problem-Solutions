/**
 *  OVERLAP ALIGNMENT PROBLEM
 *
 *  Construct a highest-scoring overlap alignment between two strings.
 *
 *  Given:  Two protein strings s and t, each of length at most 1000.
 *  Return: The score of an optimal overlap alignment of v and w, followed by an alignment of a suffix v’ of v and a prefix w’ of w achieving this maximum score. Use an alignment score in which matches count +1 and both the mismatch and indel penalties are 2. (If multiple overlap alignments achieving the maximum score exist, you may return any one.)
 */

import java.util.*;
public class OverlapAlignmentProblem {
    public static void main( String[] args ) {
        // DEFINE "s" AND "t"!!!
        String s = "";
        String t = "";
        // DEFINE "s" AND "t"!!!
        int indel = 2;
        String[] out = overlapAlignment(s,t,indel);
        System.out.println(out[0]);
        System.out.println(out[1]);
        System.out.println(out[2]);
    }
    
    public static String[] overlapAlignment( String s, String t, int indel ) {
        int[][] S = new int[s.length()+1][t.length()+1];
        int[][] opt = new int[s.length()+1][t.length()+1]; // 1 = right, 2 = down, 3 = diag, 4 = STOP
        int bi = -1;
        int bj = -1;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= s.length(); ++i) {
            for(int j = 1; j <= t.length(); ++j) {
                int opt1 = S[i][j-1] - indel; // right
                int opt2 = S[i-1][j] - indel; // down
                int opt3 = S[i-1][j-1]; // diag
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    ++opt3;
                }
                else {
                    opt3 -= 2;
                }
                S[i][j] = opt1;
                opt[i][j] = 1;
                if(opt2 > S[i][j]) {
                    S[i][j] = opt2;
                    opt[i][j] = 2;
                }
                if(opt3 > S[i][j]) {
                    S[i][j] = opt3;
                    opt[i][j] = 3;
                }
                
                if(i == s.length() || j == t.length()) {
                    if(S[i][j] > max) {
                        max = S[i][j];
                        bi = i;
                        bj = j;
                    }
                }
            }
        }
        String[] out = new String[3];
        out[1] = "";
        out[2] = "";
        out[0] = "" + max;
        while(bi > 0 && bj > 0) {
            if(opt[bi][bj] == 1) { // right
                out[1] = '-' + out[1];
                out[2] = t.charAt(bj-- - 1) + out[2];
            }
            else if(opt[bi][bj] == 2) { // down
                out[1] = s.charAt(bi-- - 1) + out[1];
                out[2] = '-' + out[2];
            }
            else if(opt[bi][bj] == 3) { // diag
                out[1] = s.charAt(bi-- - 1) + out[1];
                out[2] = t.charAt(bj-- - 1) + out[2];
            }
        }
        return out;
    }
}