/**
 *  MULTIPLE LONGEST COMMON SUBSEQUENCE PROBLEM
 *
 *  Find a longest common subsequence of multiple strings.
 *
 *  Given:  Three DNA strings.
 *  Return: The maximum score of a multiple alignment of these three strings, followed by a multiple alignment of the three strings achieving this maximum. Use a scoring function in which the score of an alignment column is 1 if all three symbols are identical and 0 otherwise. (If more than one multiple alignment achieve the maximum, you may return any one.)
 */

import java.util.*;
public class MultipleLongestCommonSubsequenceProblem {
    public static void main( String[] args ) {
        // DEFINE "s1", "s2", AND "s3"!!!
        String s1 = "";
        String s2 = "";
        String s3 = "";
        // DEFINE "s1", "s2", AND "s3"!!!
        String[] out = multipleSequenceAlignment(s1,s2,s3);
        System.out.println(out[0]);
        System.out.println(out[1]);
        System.out.println(out[2]);
        System.out.println(out[3]);
    }
    
    public static String[] multipleSequenceAlignment( String r, String s, String t ) {
        int[][][] S = new int[r.length()+1][s.length()+1][t.length()+1];
        int[][][] opt = new int[r.length()+1][s.length()+1][t.length()+1];
        for(int i = 1; i <= r.length(); ++i) {
            for(int j = 1; j <= s.length(); ++j) {
                for(int k = 1; k <= t.length(); ++k) {
                    int opt1 = S[i-1][j][k];     // (r_i, -,   -)
                    int opt2 = S[i][j-1][k];     // (-,   s_j, -)
                    int opt3 = S[i][j][k-1];     // (-,   -,   t_k)
                    int opt4 = S[i-1][j-1][k];   // (r_i, s_j, -)
                    int opt5 = S[i-1][j][k-1];   // (r_i, -,   t_k)
                    int opt6 = S[i][j-1][k-1];   // (-,   s_j, t_k)
                    int opt7 = S[i-1][j-1][k-1]; // (r_i, s_j, t_k)
                    if(r.charAt(i-1) == s.charAt(j-1) && s.charAt(j-1) == t.charAt(k-1)) {
                        ++opt7;
                    }
                    
                    S[i][j][k] = opt1;
                    opt[i][j][k] = 1;
                    if(opt2 > S[i][j][k]) {
                        S[i][j][k] = opt2;
                        opt[i][j][k] = 2;
                    }
                    if(opt3 > S[i][j][k]) {
                        S[i][j][k] = opt3;
                        opt[i][j][k] = 3;
                    }
                    if(opt4 > S[i][j][k]) {
                        S[i][j][k] = opt4;
                        opt[i][j][k] = 4;
                    }
                    if(opt5 > S[i][j][k]) {
                        S[i][j][k] = opt5;
                        opt[i][j][k] = 5;
                    }
                    if(opt6 > S[i][j][k]) {
                        S[i][j][k] = opt6;
                        opt[i][j][k] = 6;
                    }
                    if(opt7 > S[i][j][k]) {
                        S[i][j][k] = opt7;
                        opt[i][j][k] = 7;
                    }
                }
            }
        }
        String[] out = new String[4];
        int i = r.length();
        int j = s.length();
        int k = t.length();
        out[0] = "" + S[i][j][k];
        out[1] = "";
        out[2] = "";
        out[3] = "";
        while(i > 0 && j > 0 && k > 0) {
            switch(opt[i][j][k]) {
                case 1: out[1] = r.charAt(i-- - 1) + out[1];
                        out[2] = '-' + out[2];
                        out[3] = '-' + out[3];
                        break;
                case 2: out[1] = '-' + out[1];
                        out[2] = s.charAt(j-- - 1) + out[2];
                        out[3] = '-' + out[3];
                        break;
                case 3: out[1] = '-' + out[1];
                        out[2] = '-' + out[2];
                        out[3] = t.charAt(k-- - 1) + out[3];
                        break;
                case 4: out[1] = r.charAt(i-- - 1) + out[1];
                        out[2] = s.charAt(j-- - 1) + out[2];
                        out[3] = '-' + out[3];
                        break;
                case 5: out[1] = r.charAt(i-- - 1) + out[1];
                        out[2] = '-' + out[2];
                        out[3] = t.charAt(k-- - 1) + out[3];
                        break;
                case 6: out[1] = '-' + out[1];
                        out[2] = s.charAt(j-- - 1) + out[2];
                        out[3] = t.charAt(k-- - 1) + out[3];
                        break;
                case 7: out[1] = r.charAt(i-- - 1) + out[1];
                        out[2] = s.charAt(j-- - 1) + out[2];
                        out[3] = t.charAt(k-- - 1) + out[3];
                        break;
            }
        }
        while(i > 0) {
            out[1] = r.charAt(i-- - 1) + out[1];
            out[2] = '-' + out[2];
            out[3] = '-' + out[3];
        }
        while(j > 0) {
            out[1] = '-' + out[1];
            out[2] = s.charAt(j-- - 1) + out[2];
            out[3] = '-' + out[3];
        }
        while(k > 0) {
            out[1] = '-' + out[1];
            out[2] = '-' + out[2];
            out[3] = t.charAt(k-- - 1) + out[3];
        }
        return out;
    }
}