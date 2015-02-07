/**
 *  PROFILE-MOST PROBABLE KMER PROBLEM
 *
 *  Find a Profile-most probable k-mer in a string.
 *
 *  Given:  A string Text, an integer k, and a 4 × k matrix Profile.
 *  Return: A Profile-most probable k-mer in Text. (If multiple answers exist, you may return any one.)
 */

import java.util.*;
public class ProfileMostProbableKmerProblem {
    public static void main( String[] args ) {
        // DEFINE "Text", "k", AND "Profile"!!!
        String text = "";
        int k = 0;
        String profileRow1 = "";
        String profileRow2 = "";
        String profileRow3 = "";
        String profileRow4 = "";
        // DEFINE "Text", "k", AND "Profile"!!!
        
        String[] p1 = profileRow1.split(" ");
        String[] p2 = profileRow2.split(" ");
        String[] p3 = profileRow3.split(" ");
        String[] p4 = profileRow4.split(" ");
        double[][] profile = new double[4][k];
        for(int i = 0; i < k; ++i) {
            profile[0][i] = Double.parseDouble(p1[i]);
            profile[1][i] = Double.parseDouble(p2[i]);
            profile[2][i] = Double.parseDouble(p3[i]);
            profile[3][i] = Double.parseDouble(p4[i]);
        }
        System.out.println(profileMostProbableKmer(text,k,profile));
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