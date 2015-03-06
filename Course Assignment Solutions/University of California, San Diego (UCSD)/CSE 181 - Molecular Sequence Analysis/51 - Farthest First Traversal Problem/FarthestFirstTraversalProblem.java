/**
 *  FARTHEST FIRST TRAVERSAL PROBLEM
 *
 *  Farthest First Traversal Problem
 *
 *  Given:  Integers k and m followed by a set of points Data in a m-dimensional space.
 *  Return: A set Centers consisting of k points (centers) resulting from applying FarthestFirstTraversal(Data, k), where the first point from Data is chosen as the first center to initialize the algorithm.
 */

import java.util.*;
public class FarthestFirstTraversalProblem {
    public static void main( String[] args ) {
        // DEFINE "k" AND "data"!!!
        int k = 0;
        String[] data = {};
        // DEFINE "k" AND "data"!!!
        ArrayList<float[]> out = farthestFirstTraversal(k,data);
        for(int row = 0; row < out.size(); ++row) {
            float[] r = out.get(row);
            for(int col = 0; col < r.length; ++col) {
                System.out.print(r[col] + " ");
            }
            System.out.println();
        }
    }
    
    public static ArrayList<float[]> farthestFirstTraversal( int k, String[] in ) {
        float[][] data = new float[in.length][];
        for(int i = 0; i < in.length; ++i) {
            String[] p = in[i].split(" ");
            data[i] = new float[p.length];
            for(int j = 0; j < p.length; ++j) {
                data[i][j] = Float.parseFloat(p[j]);
            }
        }
        ArrayList<float[]> centers = new ArrayList<>();;
        centers.add(data[0]);
        while(centers.size() < k) {
            float[] dataPoint = data[1];
            float maxD = d(dataPoint,centers);
            for(int i = 2; i < data.length; ++i) {
                float currD = d(data[i],centers);
                if(currD > maxD) {
                    maxD = currD;
                    dataPoint = data[i];
                }
            }
            centers.add(dataPoint);
        }
        return centers;
    }
    
    public static float d( float[] dataPoint, ArrayList<float[]> centers ) {
        float min = d(dataPoint,centers.get(0));
        for(int i = 1; i < centers.size(); ++i) {
            float currD = d(dataPoint,centers.get(i));
            if(currD < min) {
                min = currD;
            }
        }
        return min;
    }
    
    public static float d( float[] dataPoint, float[] x ) {
        float d = 0;
        for(int i = 0; i < dataPoint.length; ++i) {
            float abs = Math.abs(dataPoint[i]-x[i]);
            d += (abs*abs);
        }
        return (float)(Math.sqrt(d));
    }
}