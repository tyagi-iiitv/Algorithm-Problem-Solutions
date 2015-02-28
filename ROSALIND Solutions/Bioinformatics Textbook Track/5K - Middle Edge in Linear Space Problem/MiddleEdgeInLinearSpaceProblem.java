/**
 *  MIDDLE EDGE IN LINEAR SPACE PROBLEM
 *
 *  Find a middle edge in the alignment graph in linear space.
 *
 *  Given:  Two amino acid strings.
 *  Return: A middle edge in the alignment graph of these strings, where the optimal path is defined by the BLOSUM62 scoring matrix and a linear indel penalty equal to 5. Return the middle edge in the form "(i, j) (k, l)", where (i, j) connects to (k, l).
 */

import java.util.*;
public class MiddleEdgeInLinearSpaceProblem {
    public static int[][] blosum62 = {{4,0,-2,-1,-2,0,-2,-1,-1,-1,-1,-2,-1,-1,-1,1,0,0,-3,-2},{0,9,-3,-4,-2,-3,-3,-1,-3,-1,-1,-3,-3,-3,-3,-1,-1,-1,-2,-2},{-2,-3,6,2,-3,-1,-1,-3,-1,-4,-3,1,-1,0,-2,0,-1,-3,-4,-3},{-1,-4,2,5,-3,-2,0,-3,1,-3,-2,0,-1,2,0,0,-1,-2,-3,-2},{-2,-2,-3,-3,6,-3,-1,0,-3,0,0,-3,-4,-3,-3,-2,-2,-1,1,3},{0,-3,-1,-2,-3,6,-2,-4,-2,-4,-3,0,-2,-2,-2,0,-2,-3,-2,-3},{-2,-3,-1,0,-1,-2,8,-3,-1,-3,-2,1,-2,0,0,-1,-2,-3,-2,2},{-1,-1,-3,-3,0,-4,-3,4,-3,2,1,-3,-3,-3,-3,-2,-1,3,-3,-1},{-1,-3,-1,1,-3,-2,-1,-3,5,-2,-1,0,-1,1,2,0,-1,-2,-3,-2},{-1,-1,-4,-3,0,-4,-3,2,-2,4,2,-3,-3,-2,-2,-2,-1,1,-2,-1},{-1,-1,-3,-2,0,-3,-2,1,-1,2,5,-2,-2,0,-1,-1,-1,1,-1,-1},{-2,-3,1,0,-3,0,1,-3,0,-3,-2,6,-2,0,0,1,0,-3,-4,-2},{-1,-3,-1,-1,-4,-2,-2,-3,-1,-3,-2,-2,7,-1,-2,-1,-1,-2,-4,-3},{-1,-3,0,2,-3,-2,0,-3,1,-2,0,0,-1,5,1,0,-1,-2,-2,-1},{-1,-3,-2,0,-3,-2,0,-3,2,-2,-1,0,-2,1,5,-1,-1,-3,-3,-2},{1,-1,0,0,-2,0,-1,-2,0,-2,-1,1,-1,0,-1,4,1,-2,-3,-2},{0,-1,-1,-1,-2,-2,-2,-1,-1,-1,-1,0,-1,-1,-1,1,5,0,-2,-2},{0,-1,-3,-2,-1,-3,-3,3,-2,1,1,-3,-2,-2,-3,-2,0,4,-3,-1},{-3,-2,-4,-3,1,-2,-2,-3,-3,-2,-1,-4,-4,-2,-3,-3,-2,-3,11,2},{-2,-2,-3,-2,3,-3,2,-1,-2,-1,-1,-2,-3,-1,-2,-2,-2,-1,2,7}};

    public static void main( String[] args ) {
        // DEFINE "s" AND "t"!!!
        String s = "";
        String t = "";
        // DEFINE "s" AND "t"!!!
        System.out.println(middleEdge(s,t,5));
    }
    
    public static String middleEdge( String v, String w, int indel ) {
        int[] sourceToMiddle = middleColumn(v,w,indel).o1;
        Out second;
        if(w.length()%2 == 1) {
            second = middleColumn(new StringBuilder(v).reverse().toString(),new StringBuilder(w).reverse().toString() + "$",indel);
        }
        else {
            second = middleColumn(new StringBuilder(v).reverse().toString(),new StringBuilder(w).reverse().toString(),indel);
        }
        int[] middleToSink = second.o1;
        int[] backtrack = second.o2;
        int[] scores = new int[sourceToMiddle.length];
        scores[0] = sourceToMiddle[0] + middleToSink[0];
        int max = 0;
        for(int i = 1; i < scores.length; ++i) {
            scores[i] = sourceToMiddle[i] + middleToSink[i];
            if(scores[i] > scores[max]) {
                max = i;
            }
        }
        String next = "(" + max + ", " + (w.length()/2+1)+")";
        if(max != scores.length-1) {
            if(backtrack[max] == 0) {
                next = "(" + (max+1) + ", " + (w.length()/2+1) + ")";
            }
            else if(backtrack[max] == 1) {
                next = "(" + max + ", " + (w.length()/2+1) + ")";
            }
            else {
                next = "(" + (max+1) + ", " + (w.length()/2) + ")";
            }
        }
        return "(" + max + ", " + (w.length()/2) + ") " + next;
    }
    
    public static Out middleColumn( String v, String w, int indel ) {
        int[][] S = new int[2][v.length()+1];
        for(int i = 0; i <= v.length(); ++i) {
            S[0][i] = i * -1 * indel;
        }
        S[1][0] = indel;
        int[] backtrack = new int[v.length()+1];
        for(int j = 1; j <= w.length()/2; ++j) {
            for(int i = 0; i <= v.length(); ++i) {
                if(i == 0) {
                    S[1][i] = -1*j*indel;
                }
                else {
                    int opt1 = S[0][i-1] + blosum62[aaToInt(v.charAt(i-1))][aaToInt(w.charAt(j-1))];
                    int opt2 = S[0][i] - indel;
                    int opt3 = S[1][i-1] - indel;
                    S[1][i] = opt1;
                    backtrack[i] = 0;
                    if(opt2 > S[1][i]) {
                        S[1][i] = opt2;
                        backtrack[i] = 1;
                    }
                    if(opt3 > S[1][i]) {
                        S[1][i] = opt3;
                        backtrack[i] = 2;
                    }
                }
            }
            if(j != w.length()/2) {
                S[0] = S[1];
                S[1] = new int[S[0].length];
            }
        }
        return new Out(S[1],backtrack);
    }
    
    public static int aaToInt( char a ) {
        switch(a) {
            case 'A': return 0;
            case 'C': return 1;
            case 'D': return 2;
            case 'E': return 3;
            case 'F': return 4;
            case 'G': return 5;
            case 'H': return 6;
            case 'I': return 7;
            case 'K': return 8;
            case 'L': return 9;
            case 'M': return 10;
            case 'N': return 11;
            case 'P': return 12;
            case 'Q': return 13;
            case 'R': return 14;
            case 'S': return 15;
            case 'T': return 16;
            case 'V': return 17;
            case 'W': return 18;
            case 'Y': return 19;
            default: return Integer.MIN_VALUE;
        }
    }
}

class Out {
    public int[] o1;
    public int[] o2;
    
    public Out( int[] f, int[] s ) {
        o1 = f;
        o2 = s;
    }
}