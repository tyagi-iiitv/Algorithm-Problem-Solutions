/**
 *  DE BRUIJN GRAPH FROM A STRING PROBLEM
 *
 *  Construct the de Bruijn graph of a string.
 *
 *  Given:  An integer k and a string Text.
 *  Return: DeBruijn_k(Text), in the form of an adjacency list.
 */

import java.util.*;
public class DeBruijnGraphFromKmersProblem {
    public static void main( String[] args ) {
        // DEFINE "patterns"!!!
        String[] patterns = {};
        // DEFINE "patterns"!!!
        ArrayList<String> out = deBruijnFromKmers(patterns);
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static ArrayList<String> deBruijnFromKmers( String[] kmers ) {
        String[] paths = new String[kmers.length];
        for(int i = 0; i < paths.length; ++i) {
            String curr = kmers[i];
            paths[i] = (curr.substring(0,curr.length()-1) + "." + curr.substring(1,curr.length()));
        }
        Arrays.sort(paths);
        
        ArrayList<String> out = new ArrayList<>();
        String start = "";
        ArrayList<String> ends = new ArrayList<String>();
        for(int i = 0; i < paths.length; ++i) {
            String[] edge = paths[i].split("\\.");
            if(start.equals(edge[0])) {
                ends.add(edge[1]);
            }
            else {
                if(ends.size() > 0) {
                    String add = start + " -> ";
                    for(int j = 0; j < ends.size(); ++j) {
                        add += (ends.get(j) + ",");
                    }
                    out.add(add.substring(0,add.length()-1));
                }
                
                start = edge[0];
                ends = new ArrayList<>();
                ends.add(edge[1]);
            }
        }
        String add = start + " -> ";
        for(int j = 0; j < ends.size(); ++j) {
            add += (ends.get(j) + ",");
        }
        out.add(add.substring(0,add.length()-1));
        return out;
    }
}