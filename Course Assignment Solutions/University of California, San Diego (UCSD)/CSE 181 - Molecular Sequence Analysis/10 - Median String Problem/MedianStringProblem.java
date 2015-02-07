/**
 *  MEDIAN STRING PROBLEM
 *
 *  Find a median string.
 *
 *  Given:  An integer k and a collection of strings Dna.
 *  Return: A k-mer Pattern that minimizes d(Pattern, Dna) over all k-mers Pattern. (If multiple answers exist, you may return any one.)
 */

import java.util.*;
public class MedianStringProblem {
    public static void main( String[] args ) {
        // DEFINE "k" AND "dna"!!!
        int k = 0;
        String[] dna = {};
        // DEFINE "k" AND "dna"!!!
        
        System.out.println(medianString(dna,k));
    }
    
    public static String medianString( String[] dna, int k ) {
        int distance = Integer.MAX_VALUE;
        String median = null;
        for(int i = 0; i < Math.pow(4,k); ++i) {
            String pattern = numberToPattern(i,k);
            int d = d(pattern,dna);
            if(d <= distance) {
                distance = d;
                median = pattern;
            }
        }
        return median;
    }
    
    public static String numberToPattern( int index, int k ) {
        if(k == 1) {
            return (""+numberToSymbol(index));
        }
        int prefixIndex = index / 4;
        int r = index % 4;
        String prefixPattern = numberToPattern(prefixIndex,k-1);
        char symbol = numberToSymbol(r);
        return (prefixPattern + symbol);
    }
    
    public static char numberToSymbol( int num ) {
        if(num == 0) {
            return 'A';
        }
        else if(num == 1) {
            return 'C';
        }
        else if(num == 2) {
            return 'G';
        }
        else if(num == 3) {
            return 'T';
        }
        else {
            return '\0';
        }
    }
    
    public static int d( String pattern, String[] dna ) {
        int sum = 0;
        for(int i = 0; i < dna.length; ++i) {
            sum += d(pattern,dna[i]);
        }
        return sum;
    }
    
    public static int d( String pattern, String text ) {
        int k = pattern.length();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= text.length() - k; ++i) {
            String curr = text.substring(i,i+k);
            int hamming = hammingDistance(pattern,curr);
            if(hamming < min) {
                min = hamming;
            }
        }
        return min;
    }
    
    public static int hammingDistance( String p, String q ) {
        if(p.length() != q.length()) {
            System.out.println("P and Q are different lengths!");
            System.exit(-1);
        }
        int mismatch = 0;
        for(int i = 0; i < p.length(); ++i) {
            if(p.charAt(i) != q.charAt(i))
                ++mismatch;
        }
        return mismatch;
    }
}