/**
 *  PARTIAL SUFFIX ARRAY PROBLEM
 *
 *  Construct the partial suffix array of a string.
 *
 *  Given:  A string Text and a positive integer K.
 *  Return: SuffixArrayK(Text), in the form of a list of ordered pairs (i, SuffixArray(i)) for all nonempty entries in the partial suffix array.
 */

import java.util.*;
import java.io.*;
public class PartialSuffixArrayProblem {
    public static void main( String[] args ) {
        // DEFINE "text" AND "k"!!!
        String text = "";
        int k = 0;
        // DEFINE "text" AND "k"!!!
        ArrayList<String> out = partialSuffixArrayProblem(text,k);
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static ArrayList<String> partialSuffixArrayProblem( String s, int k ) {
        int[] arr = suffixArray(s);
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < arr.length; ++i) {
            if(arr[i] % k == 0) {
                out.add(""+i+","+arr[i]);
            }
        }
        return out;
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