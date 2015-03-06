/**
 *  BURROWS-WHEELER TRANSFORM CONSTRUCTION PROBLEM
 *
 *  Construct the Burrows-Wheeler transform of a string.
 *
 *  Given:  A string Text.
 *  Return: BWT(Text).
 */

import java.util.*;
public class BurrowsWheelerTransformConstructionProblem {
    public static void main( String[] args ) {
        // DEFINE "text"!!!
        String text = "";
        // DEFINE "text"!!!
        System.out.println(burrowsWheelerTransform(text));
    }
    
    public static String burrowsWheelerTransform( String s ) {
        String[] m = burrowsWheelerMatrix(s);
        String wip = "";
        for(int i = 0; i < m.length; ++i) {
            wip += m[i].charAt(s.length()-1);
        }
        return wip;
    }
    
    public static String[] burrowsWheelerMatrix( String s ) {
        String[] m = new String[s.length()];
        for(int i = 0; i < m.length; ++i) {
            m[i] = s.substring(i,s.length()) + s.substring(0,i);
        }
        Arrays.sort(m);
        return m;
    }
}