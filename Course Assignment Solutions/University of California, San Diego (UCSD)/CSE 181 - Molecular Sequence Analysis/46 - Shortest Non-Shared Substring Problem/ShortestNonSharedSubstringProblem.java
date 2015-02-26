/**
 *  SHORTEST NON-SHARED SUBSTRING PROBLEM
 *
 *  Find the shortest substring of one string that does not appear in another string.
 *
 *  Given:  Strings Text1 and Text2.
 *  Return: The shortest substring of Text1 that does not appear in Text2.
 */

import java.util.*;
public class ShortestNonSharedSubstringProblem {
    public static void main( String[] args ) {
        // DEFINE "text1" AND "text2"!!!
        String text1 = "";
        String text2 = "";
        // DEFINE "text1" AND "text2"!!!
        System.out.println(shortestNonSharedSubstringUsingTrie(text1,text2));
    }
    
    // This method was too memory-intensive for the absurdly large dataset in class, but worked on Stepic
    public static String shortestNonSharedSubstringUsingTrie( String s, String t ) {
        // Create suffix trie from t
        DnaNode root = new DnaNode();
        for(int st = 0; st < t.length(); ++st) {
            DnaNode curr = root;
            for(int i = st; i < t.length(); ++i) {
                int v = -1;
                switch(t.charAt(i)) {
                    case 'A': v = 0; break;
                    case 'C': v = 1; break;
                    case 'G': v = 2; break;
                    case 'T': v = 3; break;
                    default: System.exit(-1);
                }
                if(curr.children[v] == null) {
                    curr.children[v] = new DnaNode();
                }
                curr = curr.children[v];
            }
        }
        
        // Pattern match all kmers of s until find one that doesn't exist in t
        for(int k = 1; k < s.length(); ++k) {
            for(int st = 0; st <= s.length() - k; ++st) {
                DnaNode curr = root;
                for(int i = st; i < st + k; ++i) {
                    int v = -1;
                    switch(s.charAt(i)) {
                        case 'A': v = 0; break;
                        case 'C': v = 1; break;
                        case 'G': v = 2; break;
                        case 'T': v = 3; break;
                        default: System.exit(-1);
                    }
                    if(curr.children[v] == null) {
                        return s.substring(st,st+k);
                    }
                    else {
                        curr = curr.children[v];
                    }
                }
            }
        }
        return null; // null = not found (all substrings of s occur in t)
    }
    
    // only used this brute force method on Rosalind, not Stepic
    public static String shortestNonSharedSubstring( String s, String t ) {
        for(int k = 10; k < s.length(); ++k) { // start at 10 to speed things up
            System.err.println("K: " + k);
            for(int st = 0; st <= s.length()-k; ++st) {
                String kmer = s.substring(st,st+k);
                if(!t.contains(kmer)) {
                    return kmer;
                }
            }
        }
        return null;
    }
}

class DnaNode {
    DnaNode[] children = new DnaNode[4]; // A = 0, C = 1; G = 2; T = 3
}