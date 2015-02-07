/**
 *  We have thus far worked with theoretical spectra of cyclic peptides, in
 *  which the mass of every subpeptide is given. This inflexibility presents a
 *  practical barrier, since mass spectrometers generate spectra that are far
 *  from ideal — they are characterized by having both false masses and missing
 *  masses. A false mass is present in the experimental spectrum but absent from
 *  the theoretical spectrum; a missing mass is present in the theoretical
 *  spectrum but absent from the experimental spectrum (see Figure 1).
 *
 *  To generalize "Cyclopeptide Sequencing Problem" to handle "noisy" spectra
 *  having false and missing masses, we need to relax the requirement that a
 *  candidate peptide's theoretical spectrum must match the experimental
 *  spectrum exactly, and instead incorporate a scoring function that will
 *  select the peptide whose theoretical spectrum matches the given
 *  experimental spectrum the most closely. Given a cyclic peptide Peptide and a
 *  spectrum Spectrum, we define Score(Peptide, Spectrum) as the number of
 *  masses shared between Cyclospectrum(Peptide) and Spectrum. Recalling
 *  Figure 1, if:
 *
 *  Spectrum = {0, 99, 113, 114, 128, 227, 257, 299, 355, 356, 370, 371, 484}
 *
 *  then Score("NQEL", Spectrum) = 11.
 *
 *  To limit the number of candidate peptides under consideration, we will use a
 *  Leaderboard, which holds the N highest scoring candidate peptides for
 *  further extension. At each step, we will expand all candidate peptides found
 *  in Leaderboard by adding every possible amino acid to the end. Then, we will
 *  eliminate those peptides whose newly calculated scores are not high enough
 *  to keep them on the Leaderboard. This idea is similar to the notion of a
 *  "cut" in a golf tournament; after the cut, only the top N golfers are
 *  allowed to play in the next round, since they are the only players who have
 *  a reasonable chance of winning.
 *
 *  To be fair, a cut should include anyone who is tied with the Nth-place
 *  competitor. Thus, Leaderboard should be trimmed down to the "N highest-
 *  scoring peptides including ties", which may include more than N peptides.
 *  Given a list of peptides Leaderboard, a spectrum Spectrum, and an integer N,
 *  Cut(Leaderboard, Spectrum, N) returns the top N highest-scoring peptides in
 *  <Leaderboard (including ties) with respect to Spectrum. We now introduce
 *  LeaderboardCyclopeptideSequencing. In what follows, the 0-peptide is the
 *  peptide "" containing no amino acids.
 */

/**
 *  LEADERBOARD CYCLOPEPTIDE SEQUENCING PROBLEM
 *
 *  Implement LeaderboardCyclopeptideSequencing
 *
 *  Given:  An integer N and a collection of integers Spectrum.
 *  Return: LeaderPeptide after running LeaderboardCyclopeptideSequencing(Spectrum, N).
 */

import java.util.*;
public class LeaderboardCyclopeptideSequencingProblem {
    public static char[] aminos = {'G','A','S','P','V','T','C','I','L','N','D','K','Q','E','M','H','F','R','Y','W'};

    public static void main( String[] args ) {
        // DEFINE "n" AND "spectrum"!!!
        int n = 0;
        int[] spectrum = {};
        // DEFINE "n" AND "spectrum"!!!
        System.out.println(leaderboardCyclopeptideSequencing(n,spectrum));
    }
    
    public static String leaderboardCyclopeptideSequencing( int n, int[] spectrum ) {
        String leaderPeptide = "";
        int leaderScore = 0;
        ArrayList<String> leaderboard = new ArrayList<>();
        leaderboard.add(leaderPeptide);
        while(!leaderboard.isEmpty()) {
            leaderboard = expandPeps(leaderboard);
            for(int i = 0; i < leaderboard.size(); ++i) {
                String peptide = leaderboard.get(i);
                int currMass = peptideToMass(peptide);
                if(currMass == parentMass(spectrum)) {
                    int currScore = score(peptide,spectrum);
                    if(currScore > leaderScore) {
                        leaderPeptide = peptide;
                        leaderScore = currScore;
                    }
                }
                else if(currMass > parentMass(spectrum)) {
                    leaderboard.remove(i--);
                }
            }
            leaderboard = cut(leaderboard,spectrum,n);
        }
        ArrayList<Integer> pep = peptideToIntegers(leaderPeptide);
        String out = "" + pep.get(0);
        for(int i = 1; i < pep.size(); ++i) {
            out += ("-" + pep.get(i));
        }
        return out;
    }
    
    public static ArrayList<String> cut( ArrayList<String> leaderboard, int[] spectrum, int n ) {
        int[] scores = new int[leaderboard.size()];
        for(int i = 0; i < scores.length; ++i) {
            scores[i] = score(leaderboard.get(i),spectrum);
        }
        Arrays.sort(scores);
        int nthHighestScore = -1;
        if(scores.length == 0) {
            nthHighestScore = 0;
        }
        else if(scores.length >= n) {
            nthHighestScore = scores[scores.length-n];
        }
        else {
            nthHighestScore = scores[0];
        }
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < leaderboard.size(); ++i) {
            String curr = leaderboard.get(i);
            if(score(curr,spectrum) >= nthHighestScore) {
                out.add(curr);
            }
        }
        return out;
    }
    
    public static int score( String peptide, int[] spectrum ) {
        int[] cyclo = cyclospectrum(peptide);
        int sum = 0;
        for(int i = 0; i < cyclo.length; ++i) {
            int val = cyclo[i];
            boolean contains = false;
            for(int j = 0; j < spectrum.length; ++j) {
                if(spectrum[j] == val) {
                    contains = true;
                    break;
                }
            }
            if(contains) {
                ++sum;
            }
        }
        return sum;
    }
    
    public static int[] cyclospectrum( String peptide ) {
        if(peptide.length() == 1) {
            int[] out = new int[1];
            out[0] = aaToMass(peptide.charAt(0));
            return out;
        }
        int[] answers = new int[peptide.length() * (peptide.length() - 1) + 2];
        answers[1] = peptideToMass(peptide);
        int index = 2;
        
        for(int i = 0; i < peptide.length(); ++i) {
            answers[index++] = peptideToMass(""+peptide.charAt(i));
        }
        for(int length = 2; length < peptide.length(); ++length) {
            for(int i = 0; i < peptide.length(); ++i) {
                String test = "";
                if(i > peptide.length() - length) {
                    test += peptide.substring(i,peptide.length());
                    test += peptide.substring(0,(i+length)%peptide.length());
                }
                else {
                    test = peptide.substring(i,i+length);
                }
                answers[index++] = peptideToMass(test);
            }
        }
        Arrays.sort(answers);
        return answers;
    }
    
    public static ArrayList<String> expandPeps( ArrayList<String> peptides ) {
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < peptides.size(); ++i) {
            String curr = peptides.get(i);
            for(int j = 0; j < aminos.length; ++j) {
                out.add(curr + aminos[j]);
            }
        }
        return out;
    }
    
    public static ArrayList<Integer> peptideToIntegers( String pep ) {
        ArrayList<Integer> out = new ArrayList<>();
        for(int i = 0; i < pep.length(); ++i) {
            out.add(aaToMass(pep.charAt(i)));
        }
        return out;
    }
    
    public static int parentMass( int[] spectrum ) {
        return spectrum[spectrum.length-1];
    }
    
    public static int peptideToMass( String p ) {
        int mass = 0;
        for(int i = 0; i < p.length(); ++i) {
            mass += aaToMass(p.charAt(i));
        }
        return mass;
    }
    
    public static int aaToMass( char c ) {
        switch(c) {
            case 'G': return 57;
            case 'A': return 71;
            case 'S': return 87;
            case 'P': return 97;
            case 'V': return 99;
            case 'T': return 101;
            case 'C': return 103;
            case 'I': return 113;
            case 'L': return 113;
            case 'N': return 114;
            case 'D': return 115;
            case 'K': return 128;
            case 'Q': return 128;
            case 'E': return 129;
            case 'M': return 131;
            case 'H': return 137;
            case 'F': return 147;
            case 'R': return 156;
            case 'Y': return 163;
            case 'W': return 186;
            default:  return -1;
        }
    }
}