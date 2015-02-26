/**
 *  2-BREAK DISTANCE PROBLEM
 *
 *  Find the 2-break distance between two genomes.
 *
 *  Given:  Two genomes with circular chromosomes on the same set of synteny blocks.
 *  Return: The 2-break distance between these genomes.
 */

import java.util.*;
import java.io.*;
public class TwoBreakDistanceProblem {
    public static void main( String[] args ) {
        // DEFINE "g1" AND "g2"!!!
        String g1 = "";
        String g2 = "";
        // DEFINE "g1" AND "g2"!!!
        System.out.println(twoBreakDistance(g1,g2));
    }
    
    public static int twoBreakDistance( String g1, String g2 ) {
        int[][] bp = breakpointGraph(g1,g2);
        int c = cycles(bp);
        int n = (bp.length-1)/2;
        return n-c;
    }
    
    public static int cycles( int[][] bp ) {
        boolean[] visited = new boolean[bp.length];
        int c = 0;
        for(int i = 1; i < bp.length; ++i) {
            if(!visited[i]) {
                int curr = i;
                while(!visited[curr]) {
                    visited[curr] = true;
                    if(!visited[bp[curr][0]]) {
                        curr = bp[curr][0];
                    }
                    else {
                        curr = bp[curr][1];
                    }
                }
                ++c;
            }
        }
        return c;
    }
    
    public static int[][] breakpointGraph( String g1, String g2 ) {
        String[] g1chroms = g1.substring(1,g1.length()-1).split("\\)\\(");
        int[][] g1chromsInt = new int[g1chroms.length][];
        int n = 0;
        for(int i = 0; i < g1chroms.length; ++i) {
            String[] parts = g1chroms[i].split(" ");
            g1chromsInt[i] = new int[parts.length];
            for(int j = 0; j < parts.length; ++j) {
                g1chromsInt[i][j] = Integer.parseInt(parts[j]);
                if(Math.abs(g1chromsInt[i][j]) > n) {
                    n = Math.abs(g1chromsInt[i][j]);
                }
            }
        }
        String[] g2chroms = g2.substring(1,g2.length()-1).split("\\)\\(");
        int[][] g2chromsInt = new int[g2chroms.length][];
        for(int i = 0; i < g2chroms.length; ++i) {
            String[] parts = g2chroms[i].split(" ");
            g2chromsInt[i] = new int[parts.length];
            for(int j = 0; j < parts.length; ++j) {
                g2chromsInt[i][j] = Integer.parseInt(parts[j]);
            }
        }
        int[][] bp = new int[2*n+1][2]; // each arrow has 2 nodes (front and back) and each node has 2 edges. Front = 1 to n. Back = n+1 to 2n
        for(int i = 0; i < bp.length; ++i) {
            bp[i][0] = -1;
            bp[i][1] = -1;
        }
        for(int i = 0; i < g1chromsInt.length; ++i) {
            for(int j = 0; j < g1chromsInt[i].length; ++j) {
                int curr = Math.abs(g1chromsInt[i][j]);
                int sign = g1chromsInt[i][j] / curr; // +1 for positive, -1 for negative
                int next = -1;
                int nextSign = 0;
                if(j == g1chromsInt[i].length-1) {
                    next = Math.abs(g1chromsInt[i][0]);
                    nextSign = g1chromsInt[i][0] / next;
                }
                else {
                    next = Math.abs(g1chromsInt[i][j+1]);
                    nextSign = g1chromsInt[i][j+1] / next;
                }
                int prev = -1;
                int prevSign = 0;
                if(j == 0) {
                    prev = Math.abs(g1chromsInt[i][g1chromsInt[i].length-1]);
                    prevSign = g1chromsInt[i][g1chromsInt[i].length-1] / prev;
                }
                else {
                    prev = Math.abs(g1chromsInt[i][j-1]);
                    prevSign = g1chromsInt[i][j-1] / prev;
                }
                
                // If I'm negative and my next is negative, then my back goes to his forward
                if(sign == -1 && nextSign == -1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = next;
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = next;
                    }
                }
                // If I'm negative and my next is positive, then my back goes to his back
                else if(sign == -1 && nextSign == 1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = frontToBack(n,next);
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = frontToBack(n,next);
                    }
                }
                // If I'm positive and my next is negative, then my forward goes to his forward
                else if(sign == 1 && nextSign == -1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = next;
                    }
                    else {
                        bp[curr][1] = next;
                    }
                }
                // If I'm positive and my next is positive, then my forward goes to his back
                else if(sign == 1 && nextSign == 1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = frontToBack(n,next);
                    }
                    else {
                        bp[curr][1] = frontToBack(n,next);
                    }
                }
                
                // If I'm negative and my prev is negative, then my forward goes to his back
                if(sign == -1 && prevSign == -1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = frontToBack(n,prev);
                    }
                    else {
                        bp[curr][1] = frontToBack(n,prev);
                    }
                }
                // If I'm negative and my prev is positive, then my forward goes to his forward
                else if(sign == -1 && prevSign == 1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = prev;
                    }
                    else {
                        bp[curr][1] = prev;
                    }
                }
                // If I'm positive and my prev is negative, then my back goes to his back
                else if(sign == 1 && prevSign == -1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = frontToBack(n,prev);
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = frontToBack(n,prev);
                    }
                }
                // If I'm positive and my prev is positive, then my back goes to his forward
                else if(sign == 1 && prevSign == 1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = prev;
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = prev;
                    }
                }
            }
        }
        for(int i = 0; i < g2chromsInt.length; ++i) {
            for(int j = 0; j < g2chromsInt[i].length; ++j) {
                int curr = Math.abs(g2chromsInt[i][j]);
                int sign = g2chromsInt[i][j] / curr; // +1 for positive, -1 for negative
                int next = -1;
                int nextSign = 0;
                if(j == g2chromsInt[i].length-1) {
                    next = Math.abs(g2chromsInt[i][0]);
                    nextSign = g2chromsInt[i][0] / next;
                }
                else {
                    next = Math.abs(g2chromsInt[i][j+1]);
                    nextSign = g2chromsInt[i][j+1] / next;
                }
                int prev = -1;
                int prevSign = 0;
                if(j == 0) {
                    prev = Math.abs(g2chromsInt[i][g2chromsInt[i].length-1]);
                    prevSign = g2chromsInt[i][g2chromsInt[i].length-1] / prev;
                }
                else {
                    prev = Math.abs(g2chromsInt[i][j-1]);
                    prevSign = g2chromsInt[i][j-1] / prev;
                }
                
                // If I'm negative and my next is negative, then my back goes to his forward
                if(sign == -1 && nextSign == -1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = next;
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = next;
                    }
                }
                // If I'm negative and my next is positive, then my back goes to his back
                else if(sign == -1 && nextSign == 1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = frontToBack(n,next);
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = frontToBack(n,next);
                    }
                }
                // If I'm positive and my next is negative, then my forward goes to his forward
                else if(sign == 1 && nextSign == -1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = next;
                    }
                    else {
                        bp[curr][1] = next;
                    }
                }
                // If I'm positive and my next is positive, then my forward goes to his back
                else if(sign == 1 && nextSign == 1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = frontToBack(n,next);
                    }
                    else {
                        bp[curr][1] = frontToBack(n,next);
                    }
                }
                
                // If I'm negative and my prev is negative, then my forward goes to his back
                if(sign == -1 && prevSign == -1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = frontToBack(n,prev);
                    }
                    else {
                        bp[curr][1] = frontToBack(n,prev);
                    }
                }
                // If I'm negative and my prev is positive, then my forward goes to his forward
                else if(sign == -1 && prevSign == 1) {
                    if(bp[curr][0] == -1) {
                        bp[curr][0] = prev;
                    }
                    else {
                        bp[curr][1] = prev;
                    }
                }
                // If I'm positive and my prev is negative, then my back goes to his back
                else if(sign == 1 && prevSign == -1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = frontToBack(n,prev);
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = frontToBack(n,prev);
                    }
                }
                // If I'm positive and my prev is positive, then my back goes to his forward
                else if(sign == 1 && prevSign == 1) {
                    if(bp[frontToBack(n,curr)][0] == -1) {
                        bp[frontToBack(n,curr)][0] = prev;
                    }
                    else {
                        bp[frontToBack(n,curr)][1] = prev;
                    }
                }
            }
        }
        return bp;
    }
    
    public static int frontToBack( int n, int i ) {
        return i+n;
    }
    
    public static int backToFront( int n, int i ) {
        return i-n;
    }
    
    public static String[] readFile( String file ) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            String[] out = new String[2];
            out[0] = r.readLine();
            out[1] = r.readLine();
            return out;
        } catch(Exception e) {}
        return null;
    }
}