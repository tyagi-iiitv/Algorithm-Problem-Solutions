/**
 *  Define the skew of a DNA string Genome, denoted Skew(Genome), as the
 *  difference between the total number of occurrences of G and C in Genome. Let
 *  Prefix_i (Genome) denote the prefix (i.e., initial substring) of Genome of
 *  length i. For example, the values of Skew(Prefixi ("CATGGGCATCGGCCATACGCC"))
 *  are: 0 -1 -1 -1 0 1 2 1 1 1 0 1 2 1 0 0 0 0 -1 0 -1 -2
 */

/**
 *  MINIMUM SKEW PROBLEM
 *
 *  Find a position in a genome minimizing the skew.
 *
 *  Given:  A DNA string Genome.
 *  Return: All integer(s) i minimizing Skew(Prefix_i (Text)) over all values of i (from 0 to |Genome|).
 */

import java.util.*;
public class MinimumSkewProblem {
    public static void main( String[] args ) {
        // DEFINE "Genome"!!!
        String genome = "";
        // DEFINE "Genome"!!!
        
        int[] skews = skew(genome);
        int minSkew = Integer.MAX_VALUE;
        ArrayList<Integer> inds = new ArrayList<>();
        for(int i = 0; i < skews.length; ++i) {
            if(skews[i] == minSkew) {
                inds.add(i);
            }
            else if(skews[i] < minSkew) {
                inds = new ArrayList<>();
                inds.add(i);
                minSkew = skews[i];
            }
        }
        for(int i = 0; i < inds.size(); ++i) {
            System.out.print(inds.get(i) + " ");
        }
    }
    
    public static int[] skew( String genome ) {
        int[] skews = new int[genome.length() + 1];
        
        for(int i = 0; i < genome.length(); ++i) {
            if(genome.charAt(i) == 'C')
                skews[i+1] = skews[i] - 1;
            else if(genome.charAt(i) == 'G')
                skews[i+1] = skews[i] + 1;
            else
                skews[i+1] = skews[i];
        }
        
        return skews;
    }
}