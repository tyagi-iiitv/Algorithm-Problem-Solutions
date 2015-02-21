/**
 *  INVERSE BURROWS-WHEELER TRANSFORM PROBLEM
 *
 *  Reconstruct a string from its Burrows-Wheeler transform.
 *
 *  Given:  A string Transform (with a single "$" sign).
 *  Return: The string Text such that BWT(Text) = Transform.
 */

import java.util.*;
public class InverseBurrowsWheelerTransformProblem {
    public static void main( String[] args ) {
        // DEFINE "transform"!!!
        String transform = "";
        // DEFINE "transform"!!!
        System.out.println(invertBWT(transform));
    }
    
    public static String invertBWT( String bwt ) {
        char[] last = bwt.toCharArray();
        char[] first = Arrays.copyOf(last,last.length);
        Arrays.sort(first);
        int[] subsFirst = new int[first.length];
        subsFirst[0] = 1;
        for(int i = 1; i < subsFirst.length; ++i) {
            if(first[i] == first[i-1]) {
                subsFirst[i] = subsFirst[i-1] + 1;
            }
            else {
                subsFirst[i] = 1;
            }
        }
        int[] subsLast = new int[first.length];
        int[] counts = new int[256];
        for(int i = 0; i < subsLast.length; ++i) {
            subsLast[i] = ++counts[last[i]];
        }
        String wip = "";
        char currL = '\0';
        int currN = 0;
        for(int i = 0; i < last.length; ++i) {
            if(last[i] == '$') {
                currL = first[i];
                currN = subsFirst[i];
                wip += currL;
                break;
            }
        }
        while(currL != '$') {
            for(int i = 0; i < last.length; ++i) {
                if(last[i] == currL && subsLast[i] == currN) {
                    currL = first[i];
                    currN = subsFirst[i];
                    wip += currL;
                    break;
                }
            }
        }
        return wip;
    }
}