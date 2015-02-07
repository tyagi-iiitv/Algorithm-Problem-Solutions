/**
 *  K-UNIVERSAL CIRCULAR STRING PROBLEM
 *
 *  Find a k-universal circular binary string.
 *
 *  Given:  An integer k.
 *  Return: A k-universal circular string. (If multiple answers exist, you may return any one.)
 */

import java.util.*;
public class KUniversalCircularStringProblem {
    public static void main( String[] args ) {
        // DEFINE "k"!!!
        int k = 0;
        // DEFINE "k"!!!
        System.out.println(kUniversalCircularString(k));
    }
    
    public static String kUniversalCircularString( int k ) {
        ArrayList<String> words = new ArrayList<>();
        permute("01",k,"",words);
        String[] kmers = new String[words.size()];
        for(int i = 0; i < words.size(); ++i) {
            kmers[i] = words.get(i);
        }
        
        ArrayList<String> deBruijn = deBruijnFromKmers(kmers);
        ArrayList<String> nodes = new ArrayList<>();
        for(int i = 0; i < deBruijn.size(); ++i) {
            String[] parts = deBruijn.get(i).split(" -> ");
            String[] targets = parts[1].split("\\,");
            if(!nodes.contains(parts[0])) {
                nodes.add(parts[0]);
            }
            for(int j = 0; j < targets.length; ++j) {
                if(!nodes.contains(targets[j])) {
                    nodes.add(targets[j]);
                }
            }
        }
        
        String[] graph = new String[deBruijn.size()];
        for(int i = 0; i < deBruijn.size(); ++i) {
            String[] parts = deBruijn.get(i).split(" -> ");
            String[] targets = parts[1].split("\\,");
            graph[i] = nodes.indexOf(parts[0]) + " -> ";
            for(int j = 0; j < targets.length; ++j) {
                graph[i] += nodes.indexOf(targets[j]) + ",";
            }
            graph[i] = graph[i].substring(0,graph[i].length()-1);
        }
        
        String cycStr = eulerianCycle(graph);
        String[] cycParts = cycStr.split("->");
        String out = "";
        for(int i = 0; i < cycParts.length-1; ++i) {
            String curr = nodes.get(Integer.parseInt(cycParts[i]));
            out += curr.charAt(0);
        }
                
        return out;
    }
    
    public static String eulerianCycle( String[] list ) {
        // Find size of adjacency list
        int largest = -1;
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            int source = Integer.parseInt(edge[0]);
            if(source > largest) {
                largest = source;
            }
        }
        
        // Creade adjacency list (edges)
        int[][] edges = new int[largest+1][];
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            int source = Integer.parseInt(edge[0]);
            String[] targets = edge[1].split("\\,");
            edges[source] = new int[targets.length];
            for(int j = 0; j < targets.length; ++j) {
                edges[source][j] = Integer.parseInt(targets[j]);
            }
        }
        
        // Create boolean adjacency list mirror to track visited
        boolean[][] explored = new boolean[edges.length][];
        for(int i = 0; i < edges.length; ++i) {
            if(edges[i] != null) {
                explored[i] = new boolean[edges[i].length];
            }
        }
        
        // Create initial Cycle
        int start = 0;
        int curr = start;
        ArrayList<Integer> cycle = new ArrayList<>();
        cycle.add(start);
        do {
            int next = -1;
            for(int i = 0; i < explored[curr].length; ++i) {
                if(!explored[curr][i]) {
                    next = edges[curr][i];
                    explored[curr][i] = true;
                    break;
                }
            }
            cycle.add(next);
            curr = next;
        } while(curr != start);
        
        // Create final Cycle
        while(true) {
            boolean stop = true;
            for(int i = 0; i < explored.length; ++i) {
                for(int j = 0; explored[i] != null && j < explored[i].length; ++j) {
                    if(!explored[i][j]) {
                        start = i;
                        curr = i;
                        stop = false;
                    }
                }
                if(!stop) {
                    break;
                }
            }
            if(stop) {
                break;
            }
            
            ArrayList<Integer> cycleP = new ArrayList<>();
            List<Integer> before = new ArrayList<>(cycle.subList(0,cycle.indexOf(curr)+1));
            List<Integer> after = new ArrayList<>(cycle.subList(cycle.indexOf(curr)+1,cycle.size()));
            
            do {
                int next = -1;
                for(int i = 0; i < explored[curr].length; ++i) {
                    if(!explored[curr][i]) {
                        next = edges[curr][i];
                        explored[curr][i] = true;
                        break;
                    }
                }
                cycleP.add(next);
                curr = next;
            } while(curr != start);
            
            cycle = new ArrayList<>();
            for(int i = 0; i < before.size(); ++i) {
                cycle.add(before.get(i));
            }
            for(int i = 0; i < cycleP.size(); ++i) {
                cycle.add(cycleP.get(i));
            }
            for(int i = 0; i < after.size(); ++i) {
                cycle.add(after.get(i));
            }
        }
        
        // Output final Cycle
        String out = "";
        for(int i = 0; i < cycle.size()-1; ++i) {
            out += (cycle.get(i) + "->");
        }
        out += cycle.get(cycle.size()-1);
        return out;
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
    
    public static void permute( String alphabet, int length, String prefix, ArrayList<String> list ) {
        if(length == 0) {
            list.add(prefix);
            return;
        }
        for(int i = 0; i < alphabet.length(); ++i) {
            permute(alphabet,length - 1,prefix + alphabet.charAt(i),list);
        }
    }
}