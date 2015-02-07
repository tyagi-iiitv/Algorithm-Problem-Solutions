/**
 *  Given integers L and t, a string Pattern forms an (L, t)-clump inside a
 *  (larger) string Genome if there is an interval of Genome of length L in
 *  which Pattern appears at least t times. For example, TGCA forms a
 *  (25,3)-clump in the following Genome:
 *       gatcagcataagggtcccTGCAaTGCAtgacaagccTGCAgttgttttac.
 */

/**
 *  CLUMP FINDING PROBLEM
 *
 *  Find patterns forming clumps in a string.
 *
 *  Given:  A string Genome, and integers k, L, and t.
 *  Return: All distinct k-mers forming (L, t)-clumps in Genome.
 */

import java.util.*;
public class ClumpFindingProblem {
    public static void main( String[] args ) {
        // DEFINE "Genome", "k", "L", AND "t"!!!
        String genome = "";
        int k = 0;
        int L = 0;
        int t = 0;
        // DEFINE "Genome", "k", "L", AND "t"!!!
        
        Object[] out = clumpFind(genome,k,L,t);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static Object[] clumpFind( String genome, int k, int L, int t ) {        
        HashSet<String> answer = new HashSet<>();
        for(int windowStart = 0; windowStart <= genome.length() - L; ++windowStart) {
            String window = genome.substring(windowStart,windowStart+L);
            for(int i = 0; i <= window.length() - k; ++i) {
                String kmer = window.substring(i,i+k);
                if(patternCount(window,kmer) >= t) {
                    answer.add(kmer);
                }
            }
        }
        
        return answer.toArray();
    }
    
    public static int patternCount( String text, String pattern ) {
        int count = 0;
        for(int i = 0; i <= text.length() - pattern.length(); ++i) {
            if(text.substring(i,i+pattern.length()).equals(pattern)) {
                ++count;
            }
        }
        return count;
    }
}