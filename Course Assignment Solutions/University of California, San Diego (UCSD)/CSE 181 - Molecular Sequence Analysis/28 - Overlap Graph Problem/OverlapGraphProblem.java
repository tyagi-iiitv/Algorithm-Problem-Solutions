/**
 *  OVERLAP GRAPH PROBLEM
 *
 *  Construct the overlap graph of a collection of k-mers.
 *
 *  Given:  A collection Patterns of k-mers.
 *  Return: The overlap graph Overlap(Patterns), in the form of an adjacency list.
 */

import java.util.*;
public class OverlapGraphProblem {
    public static void main( String[] args ) {
        // DEFINE "patterns"!!!
        String[] patterns = {};
        // DEFINE "patterns"!!!
        ArrayList<String> out = overlap(patterns);
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static ArrayList<String> overlap( String[] patterns ) {
        ArrayList<String> paths = new ArrayList<>();
        for(int i = 0; i < patterns.length; ++i) {
            String test1 = patterns[i].substring(1,patterns[i].length());
            for(int j = 0; j < patterns.length; ++j) {
                String test2 = patterns[j].substring(0,patterns[j].length()-1);
                if(test1.equals(test2)) {
                    String path = patterns[i] + " -> " + patterns[j];
                    paths.add(path);
                }
            }
        }
        Collections.sort(paths);
        return paths;
    }
}