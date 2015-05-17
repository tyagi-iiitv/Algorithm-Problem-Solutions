/**
 *  EULERIAN PATH PROBLEM
 *
 *  Find an Eulerian path in a graph.
 *
 *  Given:  A directed graph that contains an Eulerian path, where the graph is given in the form of an adjacency list.
 *  Return: An Eulerian path in this graph.
 */

import java.util.*;
public class EulerianPathProblem {
    public static void main( String[] args ) {
        // DEFINE "graph"!!!
        String[] graph = {};
        // DEFINE "graph"!!!
        System.out.println(eulerianPath(graph));
    }
    
    public static String eulerianPath( String[] list ) {
        // Find sources and targets
        ArrayList<Integer> sources = new ArrayList<>();
        ArrayList<Integer> targets = new ArrayList<>();
        int largest = -1;
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            String[] dests = edge[1].split("\\,");
            int source = Integer.parseInt(edge[0]);
            sources.add(source);
            if(source > largest) {
                largest = source;
            }
            for(int j = 0; j < dests.length; ++j) {
                int currDest = Integer.parseInt(dests[j]);
                if(currDest > largest) {
                    largest = currDest;
                }
                if(!targets.contains(currDest)) {
                    targets.add(currDest);
                }
            }
        }
        
        // Find numIn and numOut
        int[] numIn = new int[largest+1];
        int[] numOut = new int[largest+1];
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            int currSource = Integer.parseInt(edge[0]);
            String[] dests = edge[1].split("\\,");
            numOut[currSource] += dests.length;
            for(int j = 0; j < dests.length; ++j) {
                int currDest = Integer.parseInt(dests[j]);
                ++numIn[currDest];
            }
        }
        int sinkNode = -1;
        int sinkDest = -1;
        for(int i = 0; i < sources.size(); ++i) {
            if(numOut[i] == numIn[i] + 1) {
                sinkDest = i;
            }
            else if(numOut[i] == numIn[i] - 1) {
                sinkNode = i;
            }
        }
        
        String[] newList = new String[list.length+1];
        for(int i = 0; i < list.length; ++i) {
            newList[i] = list[i];
        }
        newList[list.length] = sinkNode + " -> " + sinkDest;
        
        String temp = eulerianCycle(newList);
        String[] parts = temp.split("->");
        int[] numParts = new int[parts.length-1];
        for(int i = 0; i < numParts.length; ++i) {
            numParts[i] = Integer.parseInt(parts[i]);
        }
        int endInd = -1;
        for(int i = 0; i < numParts.length; ++i) {
            if(numParts[i] == sinkNode) {
                endInd = i;
            }
        }
        String out = "";
        for(int i = endInd+1; i < numParts.length; ++i) {
            out += (numParts[i] + "->");
        }
        for(int i = 0; i < endInd+1; ++i) {
            out += (numParts[i] + "->");
        }
        
        return out.substring(0,out.length()-2);
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