/**
 *  NUMBER OF BREAKPOINTS PROBLEM
 *
 *  Find the number of breakpoints in a permutation.
 *
 *  Given:  A signed permutation P.
 *  Return: The number of breakpoints in P.
 */

import java.util.*;
public class NumberOfBreakpointsProblem {
    public static void main( String[] args ) {
        // DEFINE "p"!!!
        String p = "";
        // DEFINE "p"!!!
        System.out.println(numBreakpoints(p));
    }
    
    public static int numBreakpoints( String p ) {
        String[] parts = p.substring(1,p.length()-1).split(" ");
        int[] a = new int[parts.length+2];
        for(int i = 0; i < parts.length; ++i) {
            a[i+1] = Integer.parseInt(parts[i]);
        }
        a[a.length-1] = a.length-1;
        int bp = 0;
        for(int i = 1; i < a.length; ++i) {
            if(a[i] - a[i-1] != 1) {
                ++bp;
            }
        }
        return bp;
    }
}