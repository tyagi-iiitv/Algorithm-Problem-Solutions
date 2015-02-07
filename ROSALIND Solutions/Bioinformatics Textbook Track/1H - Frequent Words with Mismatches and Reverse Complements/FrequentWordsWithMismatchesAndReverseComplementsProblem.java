/**
 *  We now extend "Frequent Words with Mismatches Problem" to handle both
 *  mismatches and reverse complements. Recall that Pattern* refers to the
 *  reverse complement of Pattern.
 */

/**
 *  FREQUENT WORDS WITH MISMATCHES AND REVERSE COMPLEMENTS PROBLEM
 *
 *  Find the most frequent k-mers (with mismatches and reverse complements) in a
 *  DNA string.
 *
 *  Given:  A string Text as well as integers k and d.
 *  Return: All k-mers Pattern maximizing the sum Count_d(Text, Pattern) + Count_d(Text, Pattern*) over all possible k-mers.
 */

import java.util.*;
public class FrequentWordsWithMismatchesAndReverseComplementsProblem {
    public static void main( String[] args ) {
        // DEFINE "Text", "k", AND "d"!!!
        String text = "";
        int k = 0;
        int d = 0;
        // DEFINE "Text", "k", AND "d"!!!
        
        ArrayList<String> out = frequentWordsWithMismatchesAndReverseComplements(text,k,d);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<String> frequentWordsWithMismatchesAndReverseComplements( String text, int k, int d ) {
        ArrayList<String> frequentPatterns = new ArrayList<>();
        ArrayList<String> neighborhoods = new ArrayList<>();
        for(int i = 0; i <= text.length() - k; ++i) {
            ArrayList<String> n = neighbors(text.substring(i,i+k),d);
            for(int j = 0; j < n.size(); ++j) {
                neighborhoods.add(n.get(j));
            }
        }
        String rev = reverseComplement(text);
        for(int i = 0; i <= rev.length() - k; ++i) {
            ArrayList<String> n = neighbors(rev.substring(i,i+k),d);
            for(int j = 0; j < n.size(); ++j) {
                neighborhoods.add(n.get(j));
            }
        }
        long[] nums = new long[neighborhoods.size()];
        for(int i = 0; i < neighborhoods.size(); ++i) {
            nums[i] = patternToNumber(neighborhoods.get(i));
        }
        Arrays.sort(nums);
        int count = 1;
        int maxCount = -1;
        ArrayList<Long> numsUniq = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();
        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] == nums[i-1]) {
                ++count;
            }
            else {
                numsUniq.add(nums[i-1]);
                counts.add(count);
                if(count > maxCount) {
                    maxCount = count;
                }
                count = 1;
            }
        }
        numsUniq.add(nums[nums.length-1]);
        counts.add(count);
        if(count > maxCount) {
            maxCount = count;
        }
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < numsUniq.size(); ++i) {
            if(counts.get(i) == maxCount) {
                out.add(numberToPattern((int)(long)numsUniq.get(i),k));
            }
        }
        return out;
    }
    
    public static ArrayList<String> neighbors( String pattern, int d ) {
        ArrayList<String> out = new ArrayList<>();
        if(d == 0) {
            out.add(pattern);
            return out;
        }
        if(pattern.length() == 1) {
            out.add("A");
            out.add("C");
            out.add("G");
            out.add("T");
            return out;
        }
        String suf = pattern.substring(1,pattern.length());
        ArrayList<String> suffixNeighbors = neighbors(suf,d);
        for(int i = 0; i < suffixNeighbors.size(); ++i) {
            String text = suffixNeighbors.get(i);
            if(hammingDistance(suf,text) < d) {
                out.add("A" + text);
                out.add("C" + text);
                out.add("G" + text);
                out.add("T" + text);
            }
            else {
                out.add(pattern.charAt(0) + text);
            }
        }
        return out;
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
    
    public static String numberToPattern( int index, int k ) {
        if(k == 1) {
            return (""+numberToSymbol(index));
        }
        int prefixIndex = index / 4;
        int r = index % 4;
        String prefixPattern = numberToPattern(prefixIndex,k-1);
        char symbol = numberToSymbol(r);
        return (prefixPattern + symbol);
    }
    
    public static long patternToNumber( String pattern) {
        if(pattern.length() == 0) {
            return 0;
        }
        char symbol = pattern.charAt(pattern.length()-1);
        return 4*patternToNumber(pattern.substring(0,pattern.length()-1)) + symbolToNumber(symbol);
    }
    
    public static int symbolToNumber( char symbol ) {
        if(symbol == 'A') {
            return 0;
        }
        else if(symbol == 'C') {
            return 1;
        }
        else if(symbol == 'G') {
            return 2;
        }
        else if(symbol == 'T') {
            return 3;
        }
        else {
            return -1;
        }
    }
    
    public static char numberToSymbol( int num ) {
        if(num == 0) {
            return 'A';
        }
        else if(num == 1) {
            return 'C';
        }
        else if(num == 2) {
            return 'G';
        }
        else if(num == 3) {
            return 'T';
        }
        else {
            return '\0';
        }
    }
}