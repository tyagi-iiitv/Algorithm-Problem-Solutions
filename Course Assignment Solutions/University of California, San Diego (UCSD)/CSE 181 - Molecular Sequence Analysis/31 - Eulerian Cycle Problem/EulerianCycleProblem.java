/**
 *  EULERIAN CYCLE PROBLEM
 *
 *  Find an Eulerian cycle in a graph.
 *
 *  Given:  An Eulerian directed graph, in the form of an adjacency list.
 *  Return: An Eulerian cycle in this graph.
 */

import java.util.*;
public class EulerianCycleProblem {
    public static void main( String[] args ) {
        // DEFINE "graph"!!!
        String[] graph = {};
        // DEFINE "graph"!!!
        System.out.println(eulerianCycle(graph));
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
}