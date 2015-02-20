/**
 *  SUFFIX TREE CONSTRUCTION PROBLEM
 *
 *  Construct the suffix tree of a string.
 *
 *  Given:  A string Text.
 *  Return: The strings labeling the edges of SuffixTree(Text). (You may return these strings in any order.)
 */

import java.util.*;
public class SuffixTreeConstructionProblem {
    public static void main( String[] args ) {
        // DEFINE "text"!!!
        String text = "$";
        // DEFINE "text"!!!
        SuffixTree tree = suffixTreeConstruction(text);
        ArrayList<String> out = tree.outputTree();
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static SuffixTree suffixTreeConstruction( String text ) {
        SuffixTree tree = new SuffixTree(text);
        for(int i = 0; i < text.length(); ++i) {
            StrNode currNode = tree.root;
            for(int j = i; j < text.length(); ++j) {
                String currSym = ""+text.charAt(j);
                boolean found = false;
                for(int k = 0; k < currNode.children.size(); ++k) {
                    StrNode checkNode = currNode.children.get(k);
                    if(checkNode.string.equals(currSym)) {
                        currNode = checkNode;
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    StrNode newNode = new StrNode(currSym,currNode,j);
                    currNode.children.add(newNode);
                    currNode = newNode;
                }
            }
            if(currNode.children.isEmpty()) {
                currNode.label = i;
            }
        }
        
        LinkedList<StrNode> q = new LinkedList<>();
        for(int i = 0; i < tree.root.children.size(); ++i) {
            q.addLast(tree.root.children.get(i));
        }
        while(!q.isEmpty()) {
            StrNode curr = q.removeFirst();
            ArrayList<StrNode> path = new ArrayList<>();
            path.add(curr);
            while(curr.children.size() == 1) {
                curr = curr.children.get(0);
                path.add(curr);
            }
            for(int i = 0; i < curr.children.size(); ++i) {
                q.add(curr.children.get(i));
            }
            if(path.size() > 0) {
                String wip = path.get(0).string;
                for(int i = 1; i < path.size(); ++i) {
                    wip += path.get(i).string;
                }
                StrNode swap = new StrNode(wip,path.get(0).parent,path.get(0).position);
                swap.length = path.size();
                StrNode par = path.get(0).parent;
                for(int i = 0; i < par.children.size(); ++i) {
                    if(par.children.get(i).equals(path.get(0))) {
                        par.children.set(i,swap);
                    }
                }
                swap.children = path.get(path.size()-1).children;
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