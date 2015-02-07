/**
 *  In DNA strings, symbols 'A' and 'T' are complements of each other, as are
 *  'C' and 'G'. Given a nucleotide p, we denote its complementary nucleotide as
 *  p. The reverse complement of a DNA string Pattern = p1…pn is the string
 *  Pattern* = pn* … p1* formed by taking the complement of each nucleotide in
 *  Pattern, then reversing the resulting string.
 *
 *  For example, the reverse complement of Pattern = "GTCA" is Pattern* = "TGAC".
 */

/**
 *  REVERSE COMPLEMENT PROBLEM
 *
 *  Find the reverse complement of a DNA string.
 *
 *  Given:  A DNA string Pattern.
 *  Return: Pattern*, the reverse complement of Pattern.
 */
 
public class ReverseComplementProblem {
    public static void main( String[] args ) {
        // DEFINE "dna"!!!
        String dna = "";
        // DEFINE "dna"!!!
        
        System.out.println(reverseComplement(dna));
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
                System.out.println("ERROR: Input is not a DNA Sequence.");
                System.exit(-1);
            }
        }
        return out;
    }
}