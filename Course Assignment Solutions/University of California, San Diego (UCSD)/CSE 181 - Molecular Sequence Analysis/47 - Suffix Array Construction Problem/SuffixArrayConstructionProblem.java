/**
 *  SUFFIX ARRAY CONSTRUCTION PROBLEM
 *
 *  Construct the suffix array of a string.
 *
 *  Given:  A string Text.
 *  Return: SuffixArray(Text).
 */

import java.util.*;
import java.io.*;
public class SuffixArrayConstructionProblem {
    public static void main( String[] args ) {
        // DEFINE "text"!!!
        String text = "";
        // DEFINE "text"!!!
        int[] out = suffixArray(text);
        System.out.print(out[0]);
        for(int i = 1; i < out.length; ++i) {
            System.out.print(", "+out[i]);
        }
    }
    
    public static int[] suffixArray( String text ) {
        Integer[] out = new Integer[text.length()];
        for(int i = 0; i < out.length; ++i) {
            out[i] = i;
        }
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare( Integer i1, Integer i2 ) {
                String s1 = text.substring(i1,text.length());
                String s2 = text.substring(i2,text.length());
                return s1.compareTo(s2);
            }
        };
        Arrays.sort(out,comp);
        int[] o = new int[out.length];
        for(int i = 0; i < o.length; ++i) {
            o[i] = out[i];
        }
        return o;
    }
}