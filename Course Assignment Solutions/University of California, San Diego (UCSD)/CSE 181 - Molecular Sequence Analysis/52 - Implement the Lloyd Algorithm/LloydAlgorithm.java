/**
 *  LLOYD ALGORITHM
 *
 *  Implement the Lloyd Algorithm
 *
 *  Given:  Integers k and m followed by a set of points Data in m-dimensional space.
 *  Return: A set Centers consisting of k points (centers) resulting from applying the Lloyd algorithm to Data and Centers, where the first k points from Data are selected as the first k centers.
 */

import java.util.*;
public class LloydAlgorithm {
    public static void main( String[] args ) {
        // DEFINE "k" AND "data"!!!
        int k = 0;
        String[] data = {};
        // DEFINE "k" AND "data"!!!
        float[][] out = lloydAlgorithm(k,data);
        for(int row = 0; row < out.length; ++row) {
            for(int col = 0; col < out[row].length; ++col) {
                System.out.print(out[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    // my code iterates 1000 times. ideally, I should use the "improved" method to repeat until it stops improving
    public static float[][] lloydAlgorithm( int k, String[] in ) {
        float[][] data = new float[in.length][];
        for(int i = 0; i < in.length; ++i) {
            String[] p = in[i].split(" ");
            data[i] = new float[p.length];
            for(int j = 0; j < p.length; ++j) {
                data[i][j] = Float.parseFloat(p[j]);
            }
        }
        float[][] centers = new float[k][data[0].length];
        for(int i = 0; i < k; ++i) {
            centers[i] = data[i];
        }
        float[][] centersOri = centers;
        for(int it = 0; it < 1000; ++it) {
            centersOri = centers;
            // Centers to Clusters
            ArrayList<ArrayList<float[]>> clusters = new ArrayList<>();
            for(int i = 0; i < k; ++i) {
                clusters.add(new ArrayList<>());
            }
            for(int i = 0; i < data.length; ++i) {
                int cluster = closest(data[i],centers);
                clusters.get(cluster).add(data[i]);
            }
            // Clusters to Centers
            for(int c = 0; c < k; ++c) {
                ArrayList<float[]> cluster = clusters.get(c);
                float[] avg = new float[data[0].length];
                for(float[] point : cluster) {
                    for(int i = 0; i < point.length; ++i) {
                        avg[i] += point[i];
                    }
                }
                for(int i = 0; i < avg.length; ++i) {
                    avg[i] = avg[i] / cluster.size();
                }
                centers[c] = avg;
            }
        }
        return centers;
    }
    
    public static int closest( float[] dataPoint, float[][] centers ) {
        int best = 0;
        float min = d(dataPoint,centers[0]);
        for(int i = 1; i < centers.length; ++i) {
            float d = d(dataPoint,centers[i]);
            if(d < min) {
                min = d;
                best = i;
            }
        }
        return best;
    }
    
    public static boolean improved( float[][] c1, float[][] c2, float[][] data ) {
        float sum1 = 0;
        float sum2 = 0;
        for(int i = 0; i < data.length; ++i) {
            sum1 += d(data[i],c1);
            sum2 += d(data[i],c2);
        }
        return sum1 < sum2;
    }
    
    public static float d( float[] dataPoint, float[][] centers ) {
        float min = d(dataPoint,centers[0]);
        for(int i = 1; i < centers.length; ++i) {
            float currD = d(dataPoint,centers[i]);
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