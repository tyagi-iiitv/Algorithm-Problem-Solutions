/**
 *  GREEDY MOTIF SEARCH
 *
 *  Implement GreedyMotifSearch
 *
 *  Given:  Integers k and t, followed by a collection of strings Dna.
 *  Return: A collection of strings BestMotifs resulting from running GreedyMotifSearch(Dna, k, t). If at any step you find more than one Profile-most probable k-mer in a given string, select the one occurring first in the string.
 */

import java.util.*;
public class GreedyMotifSearch {
    public static void main( String[] args ) {
        // DEFINE "k", "t", AND "dna"!!!
        int k = 0;
        int t = 0;
        String[] dna = {};
        // DEFINE "k", "t", AND "dna"!!!
        
        String[] out = greedyMotifSearch(k,t,dna);
        for(int i = 0; i < out.length; ++i) {
            System.out.println(out[i]);
        }
    }
    
    public static String[] greedyMotifSearch( int k, int t, String[] dna ) {
        String[] bestMotifs = null;
        int bestScore = -1;
        for(int m = 0; m <= dna[0].length()-k; ++m) {
            String[] motifs = new String[t];
            motifs[0] = dna[0].substring(m,m+k);
            for(int i = 1; i < t; ++i) {
                String[] prevMotifs = new String[i];
                for(int x = 0; x < prevMotifs.length; ++x) {
                    prevMotifs[x] = motifs[x];
                }
                double[][] profile = profileFromMotifs(prevMotifs);
                
                motifs[i] = profileMostProbableKmer(dna[i],k,profile);
            }
            int currScore = scoreMotifs(motifs);
            if(currScore > bestScore) {
                bestScore = currScore;
                bestMotifs = motifs;
            }
        }
        return bestMotifs;
    }
    
    public static double[][] profileFromMotifs( String[] motifs ) {
        double[][] profile = new double[4][motifs[0].length()];
        for(int seq = 0; seq < motifs.length; ++seq) {
            String curr = motifs[seq];
            for(int i = 0; i < curr.length(); ++i) {
                char c = curr.charAt(i);
                ++profile[nucleoToInt(c)][i];
            }
        }
        for(int i = 0; i < profile.length; ++i) {
            for(int j = 0; j < profile[0].length; ++j) {
                profile[i][j] = profile[i][j] / (double)motifs.length;
            }
        }
        return profile;
    }
    
    public static String profileMostProbableKmer( String sequence, int k, double[][] profile ) {
        String best = null;
        double bestP = -1.0;
        for(int i = 0; i <= sequence.length() - k; ++i) {
            String sub = sequence.substring(i,i+k).toUpperCase();
            double prob = 1;
            for(int j = 0; j < sub.length(); ++j) {
                prob = prob * profile[nucleoToInt(sub.charAt(j))][j];
            }
            if(prob > bestP) {
                best = sub;
                bestP = prob;
            }
        }
        return best;
    }
    
    public static int scoreMotifs( String[] motifs ) {
        double[][] profile = profileFromMotifs(motifs);
        String consensus = "";
        for(int i = 0; i < motifs[0].length(); ++i) {
            double maxProb = -1;
            char maxChar = '\0';
            for(int j = 0; j < 4; ++j) {
                if(profile[j][i] > maxProb) {
                    maxProb = profile[j][i];
                    maxChar = intToNucleo(j);
                }
            }
            consensus += maxChar;
        }
        
        int score = 0;
        for(int i = 0; i < consensus.length(); ++i) {
            char c = consensus.charAt(i);
            for(int j = 0; j < motifs.length; ++j) {
                if(motifs[j].charAt(i) == c) {
                    ++score;
                }
            }
        }
        return score;
    }
    
    public static char intToNucleo( int i ) {
        if(i == 0) {
            return 'A';
        }
        else if(i == 1) {
            return 'C';
        }
        else if(i == 2) {
            return 'G';
        }
        else if(i == 3) {
            return 'T';
        }
        else {
            return '\0';
        }
    }
    
    public static int nucleoToInt( char n ) {
        if(n > 96) {
            n -= 32;
        }
        if(n == 'A') {
            return 0;
        }
        else if(n == 'C') {
            return 1;
        }
        else if(n == 'G') {
            return 2;
        }
        else if(n == 'T') {
            return 3;
        }
        else {
            return -1;
        }
    }
}