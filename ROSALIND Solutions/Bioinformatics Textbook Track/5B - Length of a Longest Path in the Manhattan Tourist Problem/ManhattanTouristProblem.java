/**
 *  LENGTH OF A LONGEST PATH IN THE MANHATTAN TOURIST PROBLEM
 *
 *  Find the length of a longest path in a rectangular city.
 *
 *  Given:  Integers n and m, followed by an n x (m+1) matrix Down and an (n+1) x m matrix Right. The two matrices are separated by the "-" symbol.
 *  Return: The length of a longest path from source (0, 0) to sink (n, m) in the n x m rectangular grid whose edges are defined by the matrices Down and Right.
 */

import java.util.*;
public class ManhattanTouristProblem {
    public static void main( String[] args ) {
        // DEFINE "n", "m", "Down", AND "Right"!!!
        int n = 0;
        int m = 0;
        int[][] down = {};
        int[][] right = {};
        // DEFINE "n", "m", "Down", AND "Right"!!!
        System.out.println(manhattanTourist(n,m,down,right));
    }
    
    public static int manhattanTourist( int n, int m, int[][] down, int[][] right ) {
        int[][] S = new int[n+1][m+1];
        for(int i = 1; i <= n; ++i) {
            S[i][0] = S[i-1][0] + down[i-1][0];
        }
        for(int j = 1; j <= m; ++j) {
            S[0][j] = S[0][j-1] + right[0][j-1];
        }
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                S[i][j] = Math.max(S[i-1][j] + down[i-1][j], S[i][j-1] + right[i][j-1]);
            }
        }
        return S[n][m];
    }
}