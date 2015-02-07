/**
 *  IMPLANTED MOTIF PROBLEM
 *  
 *  Find all (k, d)-motifs in a collection of strings.
 *
 *  Given:  Integers k and d, followed by a collection of strings Dna.
 *  Return: All (k, d)-motifs in Dna.
 */

import java.util.*;
public class ImplantedMotifProblem {
    public static void main( String[] args ) {
        // DEFINE "k", "d", AND "dna"!!!
        int k = 0;
        int d = 0;
        String[] dna = {};
        // DEFINE "k", "d", AND "dna"!!!
        
        ArrayList<String> out = motifEnumeration(dna,k,d);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<String> motifEnumeration( String[] dna, int k, int d ) {
        ArrayList<String> temp = new ArrayList<>();
        // build kmer variants
        ArrayList<String> patternPrimes = new ArrayList<>();
        for(int i = 0; i < dna.length; ++i) {
            String curr = dna[i];
            for(int start = 0; start <= curr.length() - k; ++start) {
                String pattern = curr.substring(start,start+k);
                ArrayList<String> list = getKmerVariants(pattern,d);
                for(int j = 0; j < list.size(); ++j) {
                    if(!patternPrimes.contains(list.get(j))) {
                        patternPrimes.add(list.get(j));
                    }
                }
            }
        }
        for(int i = 0; i < patternPrimes.size(); ++i) {
            String curr = patternPrimes.get(i);
            boolean inAll = true;
            for(int j = 0; j < dna.length; ++j) {
                if(approximatePatternCount(dna[j],curr,d) == 0) {
                    inAll = false;
                    break;
                }
            }
            if(inAll) {
                temp.add(curr);
            }
        }
        
        ArrayList<String> patterns = new ArrayList<>();
        for(int i = 0; i < temp.size(); ++i) {
            String curr = temp.get(i);
            if(!patterns.contains(curr)) {
                patterns.add(curr);
            }
        }
        Collections.sort(patterns);
        return patterns;
    }
    
    public static ArrayList<String> getKmerVariants( String kmer, int d ) {
        ArrayList<String> out = new ArrayList<>();
        permute("ACGT",kmer.length(),"",out);
        for(int i = 0; i < out.size(); ++i) {
            if(hammingDistance(kmer,out.get(i)) > d) {
                out.remove(i--);
            }
        }
        return out;
    }
    
    public static int approximatePatternCount( String text, String pattern, int d ) {
        int count = 0;
        for(int i = 0; i <= text.length() - pattern.length(); ++i) {
            if(hammingDistance(pattern,text.substring(i,i+pattern.length())) <= d)
                ++count;
        }
        return count;
    }
    
    public static void permute( String alphabet, int length, String prefix, ArrayList<String> list ) {
        if(length == 0) {
            list.add(prefix);
            return;
        }
        for(int i = 0; i < alphabet.length(); ++i) {
            permute(alphabet,length - 1,prefix + alphabet.charAt(i),list);
        }
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