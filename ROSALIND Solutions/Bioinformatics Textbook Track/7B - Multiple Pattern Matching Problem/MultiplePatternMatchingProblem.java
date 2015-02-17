/**
 *  MULTIPLE PATTERN MATCHING PROBLEM
 *
 *  Implement TrieMatching
 *
 *  Given:  A string Text and a collection of strings Patterns.
 *  Return: All starting positions in Text where a string from Patterns appears as a substring.
 */

import java.util.*;
public class MultiplePatternMatchingProblem {
    public static void main( String[] args ) {
        // DEFINE "text" AND "patterns"!!!
        String text = "";
        String[] patterns = {};
        // DEFINE "text" AND "patterns"!!!
        ArrayList<Integer> out = trieMatching(text,patterns);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<Integer> trieMatching( String text, String[] patterns ) {
        DnaTrie trie = trieConstruction(patterns);
        ArrayList<Integer> inds = new ArrayList<>();
        for(int start = 0; start < text.length(); ++start) {
            DnaNode currNode = trie.root;
            boolean added = false;
            for(int i = start; i < text.length(); ++i) {
                int currLet = -1;
                switch(text.charAt(i)) {
                    case 'A': currLet = 0; break;
                    case 'C': currLet = 1; break;
                    case 'G': currLet = 2; break;
                    case 'T': currLet = 3; break;
                    default: System.err.println("INVALID CHARACTER!"); return null;
                }
                if(currNode.numChildren == 0) {
                    inds.add(start);
                    added = true;
                    break;
                }
                else if(currNode.children[currLet] == null) {
                    break;
                }
                else {
                    currNode = currNode.children[currLet];
                }
            }
            if(currNode != trie.root && !added && currNode.numChildren == 0) {
                inds.add(start);
            }
        }
        return inds;
    }
    
    public static DnaTrie trieConstruction( String[] patterns ) {
        DnaTrie trie = new DnaTrie();
        for(int i = 0; i < patterns.length; ++i) {
            trie.add(patterns[i]);
        }
        return trie;
    }
}

class DnaTrie {
    public DnaNode root;
    public ArrayList<DnaNode> nodes; // all DnaNodes
    public int numWords;
    
    public DnaTrie() {
        root = new DnaNode('\0',null);
        nodes = new ArrayList<>();
        nodes.add(root);
    }
    
    public boolean add( String word ) {
        DnaNode curr = root;
        for(int i = 0; i < word.length(); ++i) {
            char let = word.charAt(i);
            int c = -1;
            switch(let) {
                case 'A': c = 0; break;
                case 'C': c = 1; break;
                case 'G': c = 2; break;
                case 'T': c = 3; break;
                default: return false;
            }
            if(curr.children[c] == null) {
                curr.children[c] = new DnaNode(let,curr);
                ++curr.numChildren;
                nodes.add(curr.children[c]);
            }
            curr = curr.children[c];
        }
        return true;
    }
    
    public String[] outputTrie() {
        String[] out = new String[nodes.size()-1];
        for(int i = 0; i < out.length; ++i) {
            DnaNode curr = nodes.get(i+1); // ignore root
            out[i] = "" + curr.parent.num + "->" + curr.num + ":" + curr.letter;
        }
        Arrays.sort(out);
        return out;
    }
}

class DnaNode {
    public static int numNodes = 0;
    public char letter;
    public int num;
    public DnaNode parent;
    public DnaNode[] children = new DnaNode[4]; // A,C,G,T
    public int numChildren;
    
    public DnaNode( char l, DnaNode p ) {
        letter = l;
        parent = p;
        num = numNodes++;
        numChildren = 0;
    }
}