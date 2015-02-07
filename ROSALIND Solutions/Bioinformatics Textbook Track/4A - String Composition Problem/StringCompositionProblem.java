/**
 *  STRING COMPOSITION PROBLEM
 *
 *  Generate the k-mer composition of a string.
 *
 *  Given:  An integer k and a string Text.
 *  Return: Composition_k(Text), where the k-mers are written in lexicographic order.
 */

import java.util.*;
public class StringCompositionProblem {
    public static void main( String[] args ) {
        // DEFINE "k" AND "text"!!!
        int k = 0;
        String text = "";
        // DEFINE "k" AND "text"!!!
        String[] out = compositionK(k,text);
        for(int i = 0; i < out.length; ++i) {
            System.out.println(out[i]);
        }
    }
    
    public static String[] compositionK( int k, String text ) {
        String[] out = new String[text.length() - k + 1];
        for(int i = 0; i <= text.length() - k; ++i) {
            out[i] = text.substring(i,i+k);
        }
        Arrays.sort(out);
        return out;
    }
}