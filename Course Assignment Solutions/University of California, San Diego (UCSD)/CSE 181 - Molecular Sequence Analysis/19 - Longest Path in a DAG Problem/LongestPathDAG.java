/**
 *  LONGEST PATH IN A DAG PROBLEM
 *
 *  Find a longest path between two nodes in an edge-weighted DAG.
 *
 *  Given:  An integer representing the source node of a graph, followed by an integer representing the sink node of the graph, followed by an edge-weighted graph. The graph is represented by a modified adjacency list in which the notation "0->1:7" indicates that an edge connects node 0 to node 1 with weight 7.
 *  Return: The length of a longest path in the graph, followed by a longest path. (If multiple longest paths exist, you may return any one.)
 */

import java.util.*;
public class LongestPathDAG {
    public static void main( String[] args ) {
        // DEFINE "source", "sink", AND "DAG"!!!
        int source = 0;
        int sink = 0;
        String[] dag = {};
        // DEFINE "source", "sink", AND "DAG"!!!
        String[] out = longestPathDAG(dag,source,sink);
        System.out.println(out[0]);
        System.out.println(out[1]);
    }
    
    public static String[] longestPathDAG( String[] dag, int source, int sink ) {
        // For the graph, for an edge x -> y, graph[x][y] is the cost of (x,y)
        int[][] graph = dagToIntArray(dag);
        ArrayList<Integer> bestPath = new ArrayList<>();
        bestPath.add(0);
        traverseDagForLongestPath(graph,sink,source,0,new ArrayList<Integer>(),bestPath);
        
        String[] out = new String[2];
        out[0] = ""+bestPath.get(0);
        out[1] = ""+bestPath.get(1);
        for(int i = 2; i < bestPath.size(); ++i) {
            out[1] += "->" + bestPath.get(i);
        }
        return out;
    }
    
    public static int[][] dagToIntArray( String[] dag ) {
        Arrays.sort(dag);
        int max = -1;
        for(int i = 0; i < dag.length; ++i) {
            String[] parts = dag[i].split("->");
            int source = Integer.parseInt(parts[0]);
            if(source > max) {
                max = source;
            }
        }
        int[][] graph = new int[max+1][];
        int[] maxTargets = new int[max+1];
        for(int i = 0; i < dag.length; ++i) {
            String[] split1 = dag[i].split("->");
            int source = Integer.parseInt(split1[0]);
            String[] split2 = split1[1].split(":");
            int dest = Integer.parseInt(split2[0]);
            if(dest > maxTargets[source]) {
                maxTargets[source] = dest;
            }
        }
        for(int i = 0; i < maxTargets.length; ++i) {
            graph[i] = new int[maxTargets[i]+1];
            for(int j = 0; j < graph[i].length; ++j) {
                graph[i][j] = -1;
            }
        }
        for(int i = 0; i < dag.length; ++i) {
            String[] split1 = dag[i].split("->");
            int source = Integer.parseInt(split1[0]);
            String[] split2 = split1[1].split(":");
            int dest = Integer.parseInt(split2[0]);
            int cost = Integer.parseInt(split2[1]);
            graph[source][dest] = cost;
        }
        return graph;
    }
    
    // cost of best path is bestPath.get(0)
    public static void traverseDagForLongestPath( int[][] graph, int sink, int curr, int cost, ArrayList<Integer> path, ArrayList<Integer> bestPath ) {
        if(curr >= graph.length) {
            return;
        }
        path.add(curr);
        for(int i = 0; i < graph[curr].length; ++i) {
            // If valid edge
            if(graph[curr][i] != -1) {
                // If new best path to sink:
                if(i == sink && (graph[curr][i] + cost > bestPath.get(0))) {
                    bestPath.clear();
                    bestPath.add(graph[curr][i] + cost);
                    for(int x = 0; x < path.size(); ++x) {
                        bestPath.add(path.get(x));
                    }
                    bestPath.add(sink);
                }
                
                else if(i == sink) {
                    return;
                }
                
                // Otherwise, explore this as well
                else {
                    traverseDagForLongestPath(graph,sink,i,(cost+graph[curr][i]),path,bestPath);
                }
            }
        }
        path.remove(path.size()-1);
    }
}