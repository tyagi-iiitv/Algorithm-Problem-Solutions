/**
 *  We say that a k-mer is shared by two genomes if either the k-mer or its
 *  reverse complement appears in each genome. In Figure 1 are four pairs of
 *  3-mers that are shared by "AAACTCATC" and "TTTCAAATC".
 *
 *  A shared k-mer can be represented by an ordered pair (x, y), where x is the
 *  starting position of the k-mer in the first genome and y is the starting
 *  position of the k-mer in the second genome. For the genomes "AAACTCATC" and
 *  "TTTCAAATC", these shared k-mers are (0,4), (0,0), (4,2), and (6,6).
 */

/**
 *  SHARED K-MERS PROBLEM
 *
 *  Given two strings, find all their shared k-mers.
 *
 *  Given:  An integer k and two strings.
 *  Return: All k-mers shared by these strings, in the form of ordered pairs (x, y).
 */

import java.util.*;
import java.io.*;
public class SharedKmersProblem {
    public static void main( String[] args ) {
        // DEFINE "k", "x", AND "y"!!!
        int k = 0;
        String x = "";
        String y = "";
        // DEFINE "k", "x", AND "y"!!!
        String[] out = sharedKmers(k,x,y);
        for(int i = 0; i < out.length; ++i) {
            System.out.println(out[i]);
        }
    }
    
    public static String[] sharedKmers( int k, String x, String y ) {
        HashMap<String,ArrayList<Integer>> xKmers = new HashMap<>();
        for(int i = 0; i <= x.length()-k; ++i) {
            String sub = x.substring(i,i+k);
            String rev = reverseComplement(sub);
            ArrayList<Integer> entry = xKmers.get(sub);
            if(entry == null) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                xKmers.put(sub,temp);
            }
            else {
                entry.add(i);
            }
            entry = xKmers.get(rev);
            if(entry == null) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                xKmers.put(rev,temp);
            }
            else {
                entry.add(i);
            }
        }
        ArrayList<Integer[]> pairs = new ArrayList<>();
        for(int i = 0; i <= y.length()-k; ++i) {
            String sub = y.substring(i,i+k);
            ArrayList<Integer> xInds = xKmers.get(sub);
            if(xInds != null) {
                for(int j = 0; j < xInds.size(); ++j) {
                    Integer[] pair = new Integer[2];
                    Integer xInd = xInds.get(j);
                    pair[0] = xInd;
                    pair[1] = i;
                    pairs.add(pair);
                }
            }
        }
        xKmers = null;
        int[][] sp = sortPairs(pairs);
        String[] out = new String[sp.length];
        for(int i = 0; i < out.length; ++i) {
            out[i] = "(" + sp[i][0] + ", " + sp[i][1] + ")";
        }
        return out;
    }
    
    public static int[][] sortPairs( ArrayList<Integer[]> pairs ) {
        int[][] out = new int[pairs.size()][2];
        for(int i = 0; i < out.length; ++i) {
            Integer[] curr = pairs.get(i);
            out[i][0] = curr[0];
            out[i][1] = curr[1];
        }
        final Comparator<int[]> arrayComparator = new Comparator<int[]>() {
            @Override
            public int compare( int[] o1, int[] o2 ) {
                if(o1[1] < o2[1]) {
                    return -1;
                }
                else if(o1[1] > o2[1]) {
                    return 1;
                }
                else if(o1[0] < o2[0]) {
                    return -1;
                }
                else if(o1[0] > o2[0]) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        };
        Arrays.sort(out,arrayComparator);
        return out;
    }
    
    public static String reverseComplement( String dna ) {
        String out = "";
        for(int i = dna.length() - 1; i >= 0; --i) {
            char curr = dna.charAt(i);
            if(curr == 'A')
                out += 'T';
            else if(curr == 'T')
                out += 'A';
            else if(curr == 'C')
                out += 'G';
            else if(curr == 'G')
                out += 'C';
            else {
                System.out.println("SOMETHING WENT WRONG!");
                System.exit(-1);
            }
        }
        return out;
    }
    
    public static String[] readFile( String file ) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            String[] out = new String[3];
            out[0] = r.readLine();
            out[1] = r.readLine();
            out[2] = r.readLine();
            return out;
        } catch(Exception e) {}
        return null;
    }
}