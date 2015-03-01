/**
 *  If A = (a1 = 0, a2, ... , an) is a set of n points on a line segment in
 *  increasing order (a1 < a2 < · · · < an), then deltaA denotes the collection
 *  of all pairwise differences between points in A. For example, if
 *  A = (0, 2, 4, 7), then:
 *
 *       deltaA = (7, 5, 4, 3, 2, 2, 0, 0, 0, 0, 2, 2, 3, 4, 5, 7)
 *
 *  The following problem asks us to reconstruct A from deltaA.
 */

/**
 *  TURNPIKE PROBLEM
 *
 *  Given all pairwise distances between points on a line segment, reconstruct the positions of those points.
 *
 *  Given:  A collection of integers L.
 *  Return: A set A such that deltaA = L.
 */

import java.util.*;
public class TurnpikeProblem {
    public static void main( String[] args ) {
        // DEFINE "L"!!!
        int[] L = {};
        // DEFINE "L"!!!
        int[] out = turnpike(L);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static int[] turnpike( int[] Lin ) {
        PriorityQueue<Integer> L = new PriorityQueue<>(Lin.length,Collections.reverseOrder());
        for(int e : Lin) {
            if(e > 0) {
                L.add(e);
            }
        }
        PriorityQueue<Integer> points = new PriorityQueue<>(L.size(),Collections.reverseOrder());
        points.add(0);
        points.add(L.poll());
        if(solveRecursive(L,points)) {
            int[] out = new int[points.size()];
            for(int i = out.length-1; i >= 0; --i) {
                out[i] = points.poll();
            }
            return out;
        }
        else {
            return null;
        }
    }
    
    public static boolean solveRecursive( PriorityQueue<Integer> dist, PriorityQueue<Integer> points ) {
        if(dist.isEmpty()) {
            return true;
        }
        int maxDist = dist.peek();
        int maxPoint = points.peek();
        if(solveRecursive(dist,points,maxDist)) {
            return true;
        }
        else if(solveRecursive(dist,points,maxPoint-maxDist)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean solveRecursive( PriorityQueue<Integer> dist, PriorityQueue<Integer> points, int pointToAdd ) {
        Iterator pit = points.iterator();
        LinkedList<Integer> distsToRemove = new LinkedList<>();
        while(pit.hasNext()) {
            int d = Math.abs((Integer)pit.next()-pointToAdd);
            if(dist.contains(d)) {
                distsToRemove.add(d);
            }
            else {
                return false;
            }
        }
        for(Integer e : distsToRemove) {
            dist.remove(e);
        }
        points.add(pointToAdd);
        if(solveRecursive(dist,points)) {
            return true;
        }
        else {
            points.remove(pointToAdd);
            for(Integer e : distsToRemove) {
                dist.add(e);
            }
            return false;
        }
    }
}