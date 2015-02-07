/**
 *  EDIT DISTANCE PROBLEM
 *
 *  Find the edit distance between two strings.
 *
 *  Given:  Two protein strings.
 *  Return: The edit distance between these strings.
 */

import java.util.*;
public class EditDistanceProblem {
    public static void main( String[] args ) {
        // DEFINE "s" AND "t"!!!
        String s = "";
        String t = "";
        // DEFINE "s" AND "t"!!!
        System.out.println(editDistance(s,t));
    }
    
    public static int editDistance( String s, String t ) {
        int cost = 0;
        int leftCell = -1;
        int topCell = -1;
        int cornerCell = -1;
        int m = s.length()+1;
        int n = t.length()+1;
        
        int[][] T = new int[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                T[i][j] = -1;
            }
        }
        
        for(int i = 0; i < m; ++i) {
            T[i][0] = i;
        }
        for(int j = 0; j < n; ++j) {
            T[0][j] = j;
        }
        
        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                leftCell = T[i][j-1] + 1;
                topCell = T[i-1][j] + 1;
                cornerCell = T[i-1][j-1];
                if(s.charAt(i-1) != t.charAt(j-1)) {
                    ++cornerCell;
                }
                
                T[i][j] = leftCell;
                if(topCell < T[i][j]) {
                    T[i][j] = topCell;
                }
                if(cornerCell < T[i][j]) {
                    T[i][j] = cornerCell;
                }
            }
        }
        return T[m-1][n-1];
    }
}