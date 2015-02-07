/**
 *  In "Counting Peptides with Given Mass Problem", we first encountered the
 *  problem of reconstructing a cyclic peptide from its theoretical spectrum;
 *  this problem is called the Cyclopeptide Sequencing Problem and is given
 *  below.
 */

/**
 *  CYCLOPEPTIDE SEQUENCING PROBLEM
 *
 *  Given an ideal experimental spectrum, find a cyclic peptide whose theoretical spectrum matches the experimental spectrum.
 *
 *  Given:  A collection of (possibly repeated) integers Spectrum corresponding to an ideal experimental spectrum.
 *  Return: An amino acid string Peptide such that Cyclospectrum(Peptide) = Spectrum (if such a string exists).
 */

import java.util.*;
public class CyclopeptideSequencingProblem {
    public static char[] aminos = {'G','A','S','P','V','T','C','I','L','N','D','K','Q','E','M','H','F','R','Y','W'};
    
    public static void main( String[] args ) {
        // DEFINE "spectrum"!!!
        int[] spectrum = {};
        // DEFINE "spectrum"!!!
        ArrayList<String> out = cyclopeptideSequencing(spectrum);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i)+" ");
        }
    }
    
    public static ArrayList<String> cyclopeptideSequencing( int[] spectrum ) {
        ArrayList<String> peptides = new ArrayList<>();
        ArrayList<String> tempOut = new ArrayList<>();
        peptides.add("");
        while(!peptides.isEmpty()) {
            peptides = expandPeps(peptides);
            for(int i = 0; i < peptides.size(); ++i) {
                String peptide = peptides.get(i);
                int[] currSpec = cyclospectrum(peptide);
                if(peptideToMass(peptide) == parentMass(spectrum)) {
                    if(Arrays.equals(currSpec,spectrum)) {
                        tempOut.add(peptide);
                    }
                    peptides.remove(i--);
                }
                else if(!isConsistent(currSpec,spectrum)) {
                    peptides.remove(i--);
                }
            }
        }
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < tempOut.size(); ++i) {
            ArrayList<Integer> pep = peptideToIntegers(tempOut.get(i));
            String temp = "" + pep.get(0);
            for(int j = 1; j < pep.size(); ++j) {
                temp += ("-" + pep.get(j));
            }
            if(!out.contains(temp)) {
                out.add(temp);
            }
        }
        return out;
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
    
    public static boolean isConsistent( int[] linear, int[] spectrum ) {
        for(int i = 0; i < linear.length; ++i) {
            int val = linear[i];
            if(count(val,linear) > count(val,spectrum)) {
                return false;
            }
        }
        return true;
    }
    
    public static int count( int val, int[] arr ) {
        int sum = 0;
        for(int i = 0; i < arr.length; ++i) {
            if(arr[i] == val) {
                ++sum;
            }
        }
        return sum;
    }
    
    public static int parentMass( int[] spectrum ) {
        return spectrum[spectrum.length-1];
    }
    
    public static ArrayList<Integer> peptideToIntegers( String pep ) {
        ArrayList<Integer> out = new ArrayList<>();
        for(int i = 0; i < pep.length(); ++i) {
            out.add(aaToMass(pep.charAt(i)));
        }
        return out;
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