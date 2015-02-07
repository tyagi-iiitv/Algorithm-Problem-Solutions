/**
 *  STRING RECONSTRUCTION FROM READ-PAIRS PROBLEM
 *
 *  Reconstruct a string from its paired composition.
 *
 *  Given:  Integers k and d followed by a collection of paired k-mers PairedReads.
 *  Return: A string Text with (k,d)-mer composition equal to PairedReads. (If multiple answers exist, you may return any one.)
 */

import java.util.*;
public class StringReconstructionFromReadPairsProblem {
    public static void main( String[] args ) {
        // DEFINE "k", "d", AND "pairedReads"!!!
        int k = 0;
        int d = 0;
        String[] pairedReads = {};
        // DEFINE "k", "d", AND "pairedReads"!!!
        System.out.println(stringReconstructionReadPairs(k,d,pairedReads));
    }
    
    public static String stringReconstructionReadPairs( int k, int d, String[] pairs ) {
        ArrayList<String> debruijn = deBruijnFromReadPairs(pairs);
        
        // Map strings to numbers
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
        for(int i = 0; i < debruijn.size(); ++i) {
            String[] parts = debruijn.get(i).split(" -> ");
            String[] targets = parts[1].split("\\,");
            String wip = nodes.indexOf(parts[0]) + " -> ";
            for(int j = 0; j < targets.length; ++j) {
                String currT = targets[j];
                wip += (nodes.indexOf(currT) + ",");
            }
            graph[i] = wip.substring(0,wip.length()-1);
        }
        
        // Find eulerian path
        String pathNums = eulerianPath(graph);
        String[] nums = pathNums.split("->");
        
        // Convert path to single string
        String[][] steps = new String[nums.length][2];
        for(int i = 0; i < nums.length; ++i) {
            steps[i] = nodes.get(Integer.parseInt(nums[i])).split("\\|");
        }
        String part1 = steps[0][0];
        String part2 = steps[0][1];
        for(int i = 1; i < steps.length; ++i) {
            part1 += steps[i][0].charAt(steps[i][0].length()-1);
            part2 += steps[i][1].charAt(steps[i][1].length()-1);
        }
        
        return (part1 + part2.substring(k+d,part2.length()));
    }
    
    public static ArrayList<String> deBruijnFromReadPairs( String[] pairs ) {
        String[] paths = new String[pairs.length];
        for(int i = 0; i < paths.length; ++i) {
            String[] curr = pairs[i].split("\\|");
            String preTop = curr[0].substring(0,curr[0].length()-1);
            String preBot = curr[1].substring(0,curr[1].length()-1);
            String postTop = curr[0].substring(1,curr[0].length());
            String postBot = curr[1].substring(1,curr[0].length());
            paths[i] = preTop+"|"+preBot + "." + postTop+"|"+postBot;
        }
        Arrays.sort(paths);
        
        ArrayList<String> out = new ArrayList<>();
        String start = "";
        ArrayList<String> ends = new ArrayList<>();
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
        
        // Find sink node
        int sinkNode = -1;
        for(int t = 0; t < targets.size(); ++t) {
            if(!sources.contains(targets.get(t))) {
                sinkNode = targets.get(t);
            }
        }
        if(sinkNode == -1) {
            System.err.println("NO SINK NODE!");
            System.exit(-1);
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
        int sinkDest = -1;
        for(int i = 0; i < sources.size(); ++i) {
            if(numOut[i] > numIn[i]) {
                sinkDest = i;
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