/**
 *  The workhorse of peptide sequencing is the mass spectrometer, an expensive
 *  molecular scale that shatters molecules into pieces and then weighs the
 *  resulting fragments. The mass spectrometer measures the mass of a molecule
 *  in daltons (Da); 1 Da is approximately equal to the mass of a single
 *  nuclear particle (i.e., a proton or neutron).
 *
 *  We will approximate the mass of a molecule by simply adding the number of
 *  protons and neutrons found in the molecule’s constituent atoms, which yields
 *  the molecule’s integer mass. For example, the amino acid "Gly", which has
 *  chemical formula C2H3ON, has an integer mass of 57, since 2*12 + 3*1 + 1*16
 *  + 1·14 = 57. Yet 1 Da is not exactly equal to the mass of a proton/neutron,
 *  and we may need to account for different naturally occurring isotopes of
 *  each atom when weighing a molecule. As a result, amino acids typically have
 *  non-integer masses (e.g., "Gly" has total mass equal to approximately 57.02
 *  Da); for simplicity, however, we will work with the integer mass table given
 *  in Figure 1.
 *
 *  The theoretical spectrum of a cyclic peptide Peptide, denoted
 *  Cyclospectrum(Peptide), is the collection of all of the masses of its
 *  subpeptides, in addition to the mass 0 and the mass of the entire peptide.
 *  We will assume that the theoretical spectrum can contain duplicate elements,
 *  as is the case for "NQEL" (shown in Figure 2), where "NQ" and "EL" have the
 *  same mass.
 */

/**
 *  GENERATING THEORETICAL SPECTRUM PROBLEM
 *
 *  Generate the theoretical spectrum of a cyclic peptide.
 *
 *  Given:  An amino acid string Peptide.
 *  Return: Cyclospectrum(Peptide).
 */

import java.util.*;
public class GeneratingTheoreticalSpectrumProblem {
    public static void main( String[] args ) {
        // DEFINE "peptide"!!!
        String peptide = "GTKHGCYLCHEP";
        // DEFINE "peptide"!!!
        int[] out = cyclospectrum(peptide);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static int[] cyclospectrum( String peptide ) {
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