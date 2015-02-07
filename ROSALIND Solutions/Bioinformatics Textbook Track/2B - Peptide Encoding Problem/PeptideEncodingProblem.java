/**
 *  There are three different ways to divide a DNA string into codons for
 *  translation, one starting at each of the first three starting positions of
 *  the string. These different ways of dividing a DNA string into codons are
 *  called reading frames. Since DNA is double-stranded, a genome has six
 *  reading frames (three on each strand), as shown in Figure 1.
 *
 *  We say that a DNA string Pattern encodes an amino acid string Peptide if the
 *  RNA string transcribed from either Pattern or its reverse complement
 *  Pattern' translates into Peptide.
 */

/**
 *  PEPTIDE ENCODING PROBLEM
 *
 *  Find substrings of a genome encoding a given amino acid sequence.
 *
 *  Given:  A DNA string Text and an amino acid string Peptide.
 *  Return: All substrings of Text encoding Peptide (if any such substrings exist).
 */

import java.util.*;
public class PeptideEncodingProblem {
    public static void main( String[] args ) {
        // DEFINE "text" and "peptide"!!!
        String text = "";
        String peptide = "";
        // DEFINE "text" and "peptide"!!!
        ArrayList<String> out = peptideEncoding(text,peptide);
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static ArrayList<String> peptideEncoding( String pattern, String peptide ) {
        ArrayList<String> list = new ArrayList<>();
        int dnaLength = peptide.length()*3;
        for(int i = 0; i <= pattern.length() - dnaLength; ++i) {
            String test = pattern.substring(i,i+dnaLength);
            if(peptide.equals(translate(test))) {
                list.add(test);
            }
        }
        String rev = reverseComplement(pattern);
        for(int i = 0; i <= rev.length() - dnaLength; ++i) {
            String test = rev.substring(i,i+dnaLength);
            if(peptide.equals(translate(test))) {
                list.add(reverseComplement(test));
            }
        }
        return list;
    }
    
    public static String reverseComplement( String dna ) {
        String out = "";
        for(int i = dna.length() - 1; i >= 0; --i) {
            char curr = dna.charAt(i);
            if(curr == 'A')
                out += 'T';
            else if(curr == 'T')
                out += 'A';
            else if(curr == 'C')
                out += 'G';
            else if(curr == 'G')
                out += 'C';
            else {
                System.out.println("SOMETHING WENT WRONG!");
                System.exit(-1);
            }
        }
        return out;
    }
    
    public static String translate( String seq ) {
        // Prepare Strings
        String protein = "";
        seq = seq.toUpperCase().replace('U','T');
        
        // Begin parse of sequence
        for(int i = 0; i < seq.length(); i += 3) {
            // If there are not 3 bases at end, cut it off
            if(i+3 > seq.length()) {
                protein += "-";
            }
            
            // If there are 3 next bases:
            else {
                // Get next codon's amino acid
                String amino = codon2aa(seq.substring(i,i+3));
                
                // If no amino acid match, ERROR!
                if(amino.equals("-1")) {
                    protein = "ERROR: Unrecognized text in sequence!";
                    break;
                }
                
                // If reached a STOP codon, output it and stop loop
                else if(amino.equals("")) {
                    protein += "[STOP]";
                    break;
                }
                
                // If reached a valid codon, output it
                else {
                    protein += amino;
                }
            }
        }
        
        return protein;
    }
    
    public static String codon2aa( String codon ) {
        switch(codon) {
            case "GCA": return "A";
            case "GCC": return "A";
            case "GCG": return "A";
            case "GCT": return "A";
            case "TGC": return "C";
            case "TGT": return "C";
            case "GAC": return "D";
            case "GAT": return "D";
            case "GAA": return "E";
            case "GAG": return "E";
            case "TTC": return "F";
            case "TTT": return "F";
            case "GGA": return "G";
            case "GGC": return "G";
            case "GGG": return "G";
            case "GGT": return "G";
            case "CAC": return "H";
            case "CAT": return "H";
            case "ATA": return "I";
            case "ATC": return "I";
            case "ATT": return "I";
            case "AAA": return "K";
            case "AAG": return "K";
            case "CTA": return "L";
            case "CTC": return "L";
            case "CTG": return "L";
            case "CTT": return "L";
            case "TTA": return "L";
            case "TTG": return "L";
            case "ATG": return "M";
            case "AAC": return "N";
            case "AAT": return "N";
            case "CCA": return "P";
            case "CCC": return "P";
            case "CCG": return "P";
            case "CCT": return "P";
            case "CAA": return "Q";
            case "CAG": return "Q";
            case "AGA": return "R";
            case "AGG": return "R";
            case "CGA": return "R";
            case "CGC": return "R";
            case "CGG": return "R";
            case "CGT": return "R";
            case "AGC": return "S";
            case "AGT": return "S";
            case "TCA": return "S";
            case "TCC": return "S";
            case "TCG": return "S";
            case "TCT": return "S";
            case "ACA": return "T";
            case "ACC": return "T";
            case "ACG": return "T";
            case "ACT": return "T";
            case "GTA": return "V";
            case "GTC": return "V";
            case "GTG": return "V";
            case "GTT": return "V";
            case "TGG": return "W";
            case "TAC": return "Y";
            case "TAT": return "Y";
            case "TGA": return "";
            case "TAA": return "";
            case "TAG": return "";
            default:    return "-1";
        }
    }
}