/**
 *  TRIE CONSTRUCTION PROBLEM
 *
 *  Construct a trie on a collection of patterns.
 *
 *  Given:  A collection of strings Patterns.
 *  Return: The adjacency list corresponding to Trie(Patterns), in the following format. If Trie(Patterns) has n nodes, first label the root with 1 and then label the remaining nodes with the integers 2 through n in any order you like. Each edge of the adjacency list of Trie(Patterns) will be encoded by a triple: the first two members of the triple must be the integers labeling the initial and terminal nodes of the edge, respectively; the third member of the triple must be the symbol labeling the edge.
 */

import java.util.*;
public class TrieConstructionProblem {
    public static void main( String[] args ) {
        // DEFINE "patterns"!!!
        String[] patterns = {};
        // DEFINE "patterns"!!!
        String[] out = trieConstruction(patterns);
        for(int i = 0; i < out.length; ++i) {
            System.out.println(out[i]);
        }
    }
    
    public static String[] trieConstruction( String[] patterns ) {
        DnaTrie trie = new DnaTrie();
        for(int i = 0; i < patterns.length; ++i) {
            trie.add(patterns[i]);
        }
        return trie.outputTrie();
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
    
    public DnaNode( char l, DnaNode p ) {
        letter = l;
        parent = p;
        num = ++numNodes;
    }
}