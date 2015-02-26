/**
 *  CONSTRUCTING SUFFIX TREE FROM SUFFIX ARRAY PROBLEM
 *
 *  Construct a suffix tree from the suffix array and LCP array of a string.
 *
 *  Given:  A string Text, SuffixArray(Text), and LCP(Text).
 *  Return: The strings labelling the edges of SuffixTree(Text). (You may return these strings in any order.)
 */

import java.util.*;
public class ConstructingSuffixTreeFromSuffixArrayProblem {
    public static void main( String[] args ) {
        // DEFINE "text" AND "suffixArray"!!!
        String text = "";
        int[] suffixArray = {};
        // DEFINE "text" AND "suffixArray"!!!
        ArrayList<String> out = suffixArrayToSuffixTree(text,suffixArray,null).outputTree();
        for(String str : out) {
            if(str.length() > 0) {
                System.out.println(str);
            }
        }
    }

    public static SuffixTree suffixArrayToSuffixTree( String s, int[] arr, int[] lcp ) {
        SuffixTree tree = new SuffixTree(s);
        tree.root.children.add(new StrNode(s.substring(arr[0],s.length()),tree.root,0));
        for(int ind = 1; ind < arr.length; ++ind) {
            String sub = s.substring(arr[ind],s.length());
            StrNode curr = tree.root.children.get(tree.root.children.size()-1);
            while(curr.string.length() < sub.length() && curr.string.equals(sub.substring(0,curr.string.length()))) {
                sub = sub.substring(curr.string.length(),sub.length());
                curr = curr.children.get(curr.children.size()-1);
            }
            int overlap = 0;
            for(int i = 0; i < sub.length() && i < curr.string.length(); ++i) {
                if(sub.charAt(i) == curr.string.charAt(i)) {
                    ++overlap;
                }
                else {
                    break;
                }
            }
            if(overlap == 0) {
                curr.parent.children.add(new StrNode(sub,curr.parent,0));
            }
            else {
                StrNode mid = new StrNode(sub.substring(0,overlap),curr.parent,0);
                curr.parent.children.remove(curr.parent.children.size()-1);
                curr.parent.children.add(mid);
                curr.string = curr.string.substring(overlap,curr.string.length());
                curr.parent = mid;
                StrNode right = new StrNode(sub.substring(overlap,sub.length()),mid,0);
                mid.children.add(curr);
                mid.children.add(right);
            }
        }
        return tree;
    }
}

class SuffixTree {
    public StrNode root;
    public String word;
    
    public SuffixTree( String w ) {
        word = w;
        root = new StrNode("",null,-1);
    }
    
    public ArrayList<String> outputTree() {
        ArrayList<String> out = new ArrayList<>();
        LinkedList<StrNode> q = new LinkedList<>();
        for(int i = 0; i < root.children.size(); ++i) {
            q.add(root.children.get(i));
        }
        while(!q.isEmpty()) {
            StrNode curr = q.removeFirst();
            for(int i = 0; i < curr.children.size(); ++i) {
                q.add(curr.children.get(i));
            }
            out.add(curr.string);
        }
        return out;
    }
}

class StrNode {
    public static int numNodes = 0;
    public String string;
    public ArrayList<StrNode> children;
    public int position;
    public StrNode parent;
    public int label = -1;
    public int length = 1;
    public int height = -1;
    
    public StrNode( String s, StrNode p, int pos ) {
        string = s;
        children = new ArrayList<>();
        parent = p;
        position = pos;
    }
}