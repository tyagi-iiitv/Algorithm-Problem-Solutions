/**
 *  CONTIG GENERATION PROBLEM
 *
 *  Generate the contigs from a collection of reads (with imperfect coverage).
 *
 *  Given:  A collection of k-mers Patterns.
 *  Return: All contigs in DeBruijn(Patterns). (You may return the strings in any order.)
 */

import java.util.*;
public class ContigGenerationProblem {
    public static void main( String[] args ) {
        // DEFINE "patterns"!!!
        String[] patterns = {};
        // DEFINE "patterns"!!!
        ArrayList<String> out = contigGeneration(patterns);
        for(int i = 0; i < out.size(); ++i) {
            System.out.print(out.get(i) + " ");
        }
    }
    
    public static ArrayList<String> contigGeneration( String[] kmers ) {
        int k = kmers[0].length();
        ArrayList<String> debruijn = deBruijnFromKmers(kmers);
        ArrayList<String> maxNonbranchingPaths = maximalNonBranchingPaths(debruijn);
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < maxNonbranchingPaths.size(); ++i) {
            String[] parts = maxNonbranchingPaths.get(i).split(" -> ");
            String wip = parts[0];
            for(int j = 1; j < parts.length; ++j) {
                wip += parts[j].charAt(parts[j].length()-1);
            }
            out.add(wip);
        }
        return out;
    }
    
    public static ArrayList<String> maximalNonBranchingPaths( ArrayList<String> debruijn ) {
        ArrayList<String> nodes = new ArrayList<>();
        for(int i = 0; i < debruijn.size(); ++i) {
            String currNode = debruijn.get(i);
            String[] parts = currNode.split(" -> ");
            String source = parts[0];
            if(!nodes.contains(source)) {
                nodes.add(source);
            }
            String[] targets = parts[1].split("\\,");
            for(int j = 0; j < targets.length; ++j) {
                String curr = targets[j];
                if(!nodes.contains(curr)) {
                    nodes.add(curr);
                }
            }
        }
        Collections.sort(nodes);
        
        // Convert de Bruijn to numbers (correct)
        String[] graph = new String[debruijn.size()];
        int[] in = new int[nodes.size()];
        int[] out = new int[in.length];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < in.length; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < debruijn.size(); ++i) {
            String[] parts = debruijn.get(i).split(" -> ");
            String[] targets = parts[1].split("\\,");
            int ind = nodes.indexOf(parts[0]);
            String wip = ind + " -> ";
            for(int j = 0; j < targets.length; ++j) {
                String currT = targets[j];
                int index = nodes.indexOf(currT);
                ++in[index];
                ++out[ind];
                edges.get(ind).add(index);
                wip += (index + ",");
            }
            graph[i] = wip.substring(0,wip.length()-1);
        }
        ArrayList<String> paths = new ArrayList<>();
        boolean[] used = new boolean[nodes.size()];
        for(int v = 0; v < nodes.size(); ++v) {
            if(in[v] != 1 || out[v] != 1) {
                if(out[v] > 0) {
                    for(int i = 0; i < edges.get(v).size(); ++i) {
                        int w = edges.get(v).get(i);
                        String nonbranchingPath = "" + nodes.get(v) + " -> " + nodes.get(w);
                        used[v] = true;
                        used[w] = true;
                        while(in[w] == 1 && out[w] == 1) {
                            int u = edges.get(w).get(0);
                            nonbranchingPath += " -> " + nodes.get(u);
                            w = u;
                            used[w] = true;
                        }
                        paths.add(nonbranchingPath);
                    }
                }
            }
        }
        
        for(int i = 0; i < nodes.size(); ++i) {
            if(!used[i]) {
                String wip = "" + nodes.get(i);
                int curr = i;
                while(out[curr] > 0 && !used[curr]) {
                    int u = edges.get(curr).get(0);
                    wip += " -> " + nodes.get(u);
                    curr = u;
                    used[curr] = true;
                }
                used[curr] = true;
                if(wip.contains("->")) {
                    wip += " -> " + nodes.get(i); 
                }
                paths.add(wip);
            }
        }
        return paths;
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