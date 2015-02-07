/**
 *  LONGEST COMMON SUBSEQUENCE PROBLEM
 *
 *  Given:  Two strings.
 *  Return: A longest common subsequence of these strings.
 */

import java.util.*;
public class LongestCommonSubsequenceProblem {
    public static void main( String[] args ) {
        // DEFINE "s1" AND "s2"!!!
        String s1 = "";
        String s2 = "";
        // DEFINE "s1" AND "s2"!!!
        System.out.println(longestCommonSubsequence(s1,s2));
    }
    
    public static String longestCommonSubsequence( String v, String w ) {
        int[][] s = new int[v.length()+1][w.length()+1];
        int[][] backtrack = new int[v.length()+1][w.length()+1];
        
        // LCS Backtrack
        for(int i = 0; i <= v.length(); ++i) {
            s[i][0] = 0;
        }
        for(int j = 0; j <= w.length(); ++j) {
            s[0][j] = 0;
        }
        for(int i = 1; i <= v.length(); ++i) {
            for(int j = 1; j <= w.length(); ++j) {
                int opt1 = s[i-1][j];
                int opt2 = s[i][j-1];
                int opt3 = Integer.MIN_VALUE;
                s[i][j] = Math.max(opt1,opt2);
                if(v.charAt(i-1) == w.charAt(j-1)) {
                    opt3 = s[i-1][j-1] + 1;
                    if(opt3 > s[i][j]) {
                        s[i][j] = opt3;
                    }
                }
                if(s[i][j] == opt1) {
                    backtrack[i][j] = 1;
                }
                else if(s[i][j] == opt2) {
                    backtrack[i][j] = 2;
                }
                else if(s[i][j] == opt3) {
                    backtrack[i][j] = 3;
                }
            }
        }
        
        //OutputLCS
        ArrayList<Character> out = new ArrayList<>();
        outputLCS(out,backtrack,v,v.length(),w.length());
        String o = "";
        for(int i = 0; i < out.size(); ++i) {
            o += out.get(i);
        }
        return o;
    }
    
    public static void outputLCS( ArrayList<Character> out, int[][] backtrack, String v, int i, int j ) {
        if(i == 0 || j == 0) {
            return;
        }
        if(backtrack[i][j] == 1) {
            outputLCS(out,backtrack,v,i-1,j);
        }
        else if(backtrack[i][j] == 2) {
            outputLCS(out,backtrack,v,i,j-1);
        }
        else {
            outputLCS(out,backtrack,v,i-1,j-1);
            out.add(v.charAt(i-1));
        }
    }
}