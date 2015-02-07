/**
 *  ALIGNMENT WITH AFFINE GAP PENALTIES PROBLEM
 *
 *  Construct a highest-scoring global alignment of two strings (with affine gap penalties).
 *
 *  Given:  Two amino acid strings v and w (each of length at most 100).
 *  Return: The maximum alignment score between v and w, followed by an alignment of v and w achieving this maximum score. Use the BLOSUM62 scoring matrix, a gap opening penalty of 11, and a gap extension penalty of 1.
 */

import java.util.*;
public class AlignmentWithAffineGapPenaltiesProblem {
    public static String aminosAlpha = "ACDEFGHIKLMNPQRSTVWY";
    static int[][] blosum62 = {{4,0,-2,-1,-2,0,-2,-1,-1,-1,-1,-2,-1,-1,-1,1,0,0,-3,-2},{0,9,-3,-4,-2,-3,-3,-1,-3,-1,-1,-3,-3,-3,-3,-1,-1,-1,-2,-2},{-2,-3,6,2,-3,-1,-1,-3,-1,-4,-3,1,-1,0,-2,0,-1,-3,-4,-3},{-1,-4,2,5,-3,-2,0,-3,1,-3,-2,0,-1,2,0,0,-1,-2,-3,-2},{-2,-2,-3,-3,6,-3,-1,0,-3,0,0,-3,-4,-3,-3,-2,-2,-1,1,3},{0,-3,-1,-2,-3,6,-2,-4,-2,-4,-3,0,-2,-2,-2,0,-2,-3,-2,-3},{-2,-3,-1,0,-1,-2,8,-3,-1,-3,-2,1,-2,0,0,-1,-2,-3,-2,2},{-1,-1,-3,-3,0,-4,-3,4,-3,2,1,-3,-3,-3,-3,-2,-1,3,-3,-1},{-1,-3,-1,1,-3,-2,-1,-3,5,-2,-1,0,-1,1,2,0,-1,-2,-3,-2},{-1,-1,-4,-3,0,-4,-3,2,-2,4,2,-3,-3,-2,-2,-2,-1,1,-2,-1},{-1,-1,-3,-2,0,-3,-2,1,-1,2,5,-2,-2,0,-1,-1,-1,1,-1,-1},{-2,-3,1,0,-3,0,1,-3,0,-3,-2,6,-2,0,0,1,0,-3,-4,-2},{-1,-3,-1,-1,-4,-2,-2,-3,-1,-3,-2,-2,7,-1,-2,-1,-1,-2,-4,-3},{-1,-3,0,2,-3,-2,0,-3,1,-2,0,0,-1,5,1,0,-1,-2,-2,-1},{-1,-3,-2,0,-3,-2,0,-3,2,-2,-1,0,-2,1,5,-1,-1,-3,-3,-2},{1,-1,0,0,-2,0,-1,-2,0,-2,-1,1,-1,0,-1,4,1,-2,-3,-2},{0,-1,-1,-1,-2,-2,-2,-1,-1,-1,-1,0,-1,-1,-1,1,5,0,-2,-2},{0,-1,-3,-2,-1,-3,-3,3,-2,1,1,-3,-2,-2,-3,-2,0,4,-3,-1},{-3,-2,-4,-3,1,-2,-2,-3,-3,-2,-1,-4,-4,-2,-3,-3,-2,-3,11,2},{-2,-2,-3,-2,3,-3,2,-1,-2,-1,-1,-2,-3,-1,-2,-2,-2,-1,2,7}};
    
    public static void main( String[] args ) {
        // DEFINE "v" AND "w"!!!
        String v = "";
        String w = "";
        // DEFINE "v" AND "w"!!!
        int go = 11;
        int ge = 1;
        String[] out = globalAlignmentAffineGapPenalties(v,w,go,ge);
        System.out.println(out[0]);
        System.out.println(out[1]);
        System.out.println(out[2]);
    }
    
    public static String[] globalAlignmentAffineGapPenalties( String s, String t, int go, int ge ) {
        // Uses BLOSUM62. PASS go AND ge AS POSITIVE INTEGER, NOT NEGATIVE!
        int[][][] S = new int[3][s.length()+1][t.length()+1]; // S[0] = lower, S[1] = middle, S[2] = upper
        int[][][] opt = new int[3][s.length()+1][t.length()+1]; // 1 = right, 2 = down, 3 = diag
        for(int i = 1; i <= s.length(); ++i) {
            S[0][i][0] = -1 * (go + (i-1)*ge);
            S[1][i][0] = -1 * (go + (i-1)*ge);
            S[2][i][0] = -10*go;
            opt[0][i][0] = 0;
            opt[1][i][0] = 0;
            opt[2][i][0] = 0;
        }
        for(int j = 1; j <= t.length(); ++j) {
            S[0][0][j] = -10*go;
            S[1][0][j] = -1 * (go + (j-1)*ge);
            S[2][0][j] = -1 * (go + (j-1)*ge);
            opt[0][0][j] = 1;
            opt[1][0][j] = 1;
            opt[2][0][j] = 1;
        }
        for(int i = 1; i <= s.length(); ++i) {
            for(int j = 1; j <= t.length(); ++j) {
                int low1 = S[0][i-1][j] - ge;
                int low2 = S[1][i-1][j] - go;
                if(low1 > low2) {
                    S[0][i][j] = low1;
                    opt[0][i][j] = 0;
                }
                else {
                    S[0][i][j] = low2;
                    opt[0][i][j] = 1;
                }
                
                int up1 = S[2][i][j-1] - ge;
                int up2 = S[1][i][j-1] - go;
                if(up1 > up2) {
                    S[2][i][j] = up1;
                    opt[2][i][j] = 0;
                }
                else {
                    S[2][i][j] = up2;
                    opt[2][i][j] = 1;
                }
            
                int opt1 = S[0][i][j];
                int opt2 = S[1][i-1][j-1] + blosum62[aminosAlpha.indexOf(s.charAt(i-1))][aminosAlpha.indexOf(t.charAt(j-1))];
                int opt3 = S[2][i][j];
                S[1][i][j] = opt1;
                opt[1][i][j] = 0;
                if(opt2 > S[1][i][j]) {
                    S[1][i][j] = opt2;
                    opt[1][i][j] = 1;
                }
                if(opt3 > S[1][i][j]) {
                    S[1][i][j] = opt3;
                    opt[1][i][j] = 2;
                }
            }
        }
        int i = s.length();
        int j = t.length();
        int bestSIJ = 0;
        int best = S[0][i][j];
        if(S[1][i][j] > best) {
            best = S[1][i][j];
            bestSIJ = 1;
        }
        if(S[2][i][j] > best) {
            best = S[2][i][j];
            bestSIJ = 2;
        }
        String[] out = new String[3];
        out[0] = "" + best;
        out[1] = "";
        out[2] = "";
        while(i > 0 && j > 0) {
            if(bestSIJ == 0) {
                if(opt[0][i][j] == 1) {
                    bestSIJ = 1;
                }
                out[1] = s.charAt(i-- - 1) + out[1];
                out[2] = '-' + out[2];
            }
            else if(bestSIJ == 1) {
                if(opt[1][i][j] == 0) {
                    bestSIJ = 0;
                }
                else if(opt[1][i][j] == 2) {
                    bestSIJ = 2;
                }
                else {
                    out[1] = s.charAt(i-- - 1) + out[1];
                    out[2] = t.charAt(j-- - 1) + out[2];
                }
            }
            else {
                if(opt[2][i][j] == 1) {
                    bestSIJ = 1;
                }
                out[1] = '-' + out[1];
                out[2] = t.charAt(j-- - 1) + out[2];
            }
        }
        if(i > 0) {
            out[1] = s.substring(0,i) + out[1];
            String add = "";
            for(int x = 0; x < i; ++x) {
                add += '-';
            }
            out[2] = add + out[2];
        }
        if(j > 0) {
            out[2] = t.substring(0,j) + out[2];
            String add = "";
            for(int x = 0; x < j; ++x) {
                add += '-';
            }
            out[1] = add + out[1];
        }
        return out;
    }
}