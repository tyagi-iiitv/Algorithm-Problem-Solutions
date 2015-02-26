/**
 *  GREEDY REVERSAL SORTING
 *
 *  Implement GreedySorting
 *
 *  Given:  A signed permutation P.
 *  Return: The sequence of permutations corresponding to applying GreedySorting to P, ending with the identity permutation.
 */

import java.util.*;
public class GreedyReversalSorting {
    public static void main( String[] args ) {
        // DEFINE "p"!!!
        String p = "";
        // DEFINE "p"!!!
        ArrayList<String> out = greedySorting(p);
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static ArrayList<String> greedySorting( String pIn ) {
        String pTrim = pIn.substring(1,pIn.length()-1);
        String[] pArr = pTrim.split(" ");
        int[] p = new int[pArr.length];
        for(int i = 0; i < p.length; ++i) {
            p[i] = Integer.parseInt(pArr[i]);
        }
        ArrayList<String> out = new ArrayList<>();
        
        for(int k = 0; k < p.length; ++k) {
            if(p[k] != (k+1)) {
                if(p[k] == (-1*(k+1))) {
                    p[k] = k+1;
                }
                else {
                    ArrayList<Integer> hold = new ArrayList<>();
                    int index;
                    for(index = k+1; index < p.length; ++index) {
                        if(p[index] == (k+1) || p[index] == (-1*(k+1))) {
                            break;
                        }
                    }
                    for(int i = index; i >= k; --i) {
                        hold.add(-1*p[i]);
                    }
                    for(int i = 0; i < hold.size(); ++i) {
                        p[i+k] = hold.get(i);
                    }
                }
                String temp = "(";
                for(int i = 0; i < p.length; ++i) {
                    if(p[i] > 0) {
                        temp += "+";
                    }
                    temp += p[i] + " ";
                }
                temp = temp.substring(0,temp.length()-1) + ")";
                out.add(temp);
            }
            if(p[k] == (-1*(k+1))) {
                p[k] = k+1;
                String temp = "(";
                for(int i = 0; i < p.length; ++i) {
                    if(p[i] > 0) {
                        temp += "+";
                    }
                    temp += p[i] + " ";
                }
                temp = temp.substring(0,temp.length()-1) + ")";
                out.add(temp);
            }
        }
        return out;
    }
}