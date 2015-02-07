/**
 *  In "Generating Theoretical Spectrum Problem", we generated the theoretical
 *  spectrum of a known cyclic peptide. Although this task is relatively easy,
 *  our aim in mass spectrometry is to solve the reverse problem: we must
 *  reconstruct an unknown peptide from its experimental spectrum. We will start
 *  by assuming that a biologist is lucky enough to generate an ideal
 *  experimental spectrum Spectrum, which is one coinciding with the peptide’s
 *  theoretical spectrum. Can we reconstruct a peptide whose theoretical
 *  spectrum is Spectrum?
 *
 *  Denote the total mass of an amino acid string Peptide as Mass(Peptide). In
 *  mass spectrometry experiments, whereas the peptide that generated a spectrum
 *  is unknown, the peptide’s mass is typically known and is denoted
 *  ParentMass(Spectrum). Of course, given an ideal experimental spectrum,
 *  Mass(Peptide) is given by the largest mass in the spectrum.
 *
 *  A brute force approach to reconstructing a peptide from its theoretical
 *  spectrum would generate all possible peptides whose mass is equal to
 *  ParentMass(Spectrum) and then check which of these peptides has theoretical
 *  spectra matching Spectrum. However, we should be concerned about the running
 *  time of such an approach: how many peptides are there having mass equal to
 *  ParentMass(Spectrum)?
 */

/**
 *  COUNTING PEPTIDES WITH GIVEN MASS PROBLEM
 *
 *  Compute the number of peptides of given total mass.
 *
 *  Given:  An integer m.
 *  Return: The number of linear peptides having integer mass m.
 */

import java.util.*;
public class CountingPeptidesWithGivenMassProblem {
    static int[] peptideMasses = {57,71,87,97,99,101,103,113,114,115,128,129,131,137,147,156,163,186};
    
    public static void main( String[] args ) {
        // DEFINE "m"!!!
        int m = 1474;
        // DEFINE "m"!!!
        System.out.println(countPeptidesWithMass(m));
    }
    
    public static long countPeptidesWithMass( int m ) {
        long[] masses = new long[m+1];
        masses[0] = 1;
        for(int i = 0; i <= m; ++i) {
            for(int j = 0; j < peptideMasses.length; ++j) {
                if(i >= peptideMasses[j]) {
                    masses[i] += masses[i-peptideMasses[j]];
                }
            }
        }
        return masses[m];
    }
}