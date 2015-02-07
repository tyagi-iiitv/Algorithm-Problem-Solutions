/**
 *  We define the convolution of a cyclic spectrum by taking all positive
 *  differences of masses in the spectrum. Figure 1 shows the convolution of the
 *  theoretical spectrum of "NQEL".
 *
 *  As predicted, some of the values in Figure 2.12 appear more frequently than
 *  others. For example, 113 (the mass of "L") appears eight times in the
 *  convolution of the theoretical spectrum of "NQEL"; we say that 113 has
 *  multiplicity 8. Six of the eight occurrences of 113 correspond to subpeptide
 *  pairs differing in an "L": "L" and ""; "LN" and "N"; "EL" and "E"; "LNQ" and
 *  "NQ"; "QEL" and "QE"; "NQEL" and "NQE".
 */

/**
 *  SPECTRAL CONVOLUTION PROBLEM
 *
 *  Compute the convolution of a spectrum.
 *
 *  Given:  A collection of integers Spectrum.
 *  Return: The list of elements in the convolution of Spectrum in decreasing order of their multiplicities. If an element has multiplicity k, it should appear exactly k times.
 */

import java.util.*;
public class SpectralConvolutionProblem {
    public static void main( String[] args ) {
        // DEFINE "spectrum"!!!
        int[] spectrum = {};
        // DEFINE "spectrum"!!!
        int[] out = spectralConvolutionDecreasingK(spectrum);
        for(int i = 0; i < out.length; ++i) {
            System.out.print(out[i] + " ");
        }
    }
    
    public static int[] spectralConvolution( int[] spectrum ) {
        int n = spectrum.length;
        int[] out = new int[(n*(n-1))/2];
        int add = 0;
        for(int i = 0; i < spectrum.length; ++i) {
            for(int j = 0; j < spectrum.length; ++j) {
                if(spectrum[j] > spectrum[i]) {
                    out[add++] = spectrum[j] - spectrum[i];
                }
            }
        }
        int[] outTrim = Arrays.copyOf(out,add);
        Arrays.sort(outTrim);
        return outTrim;
    }
    
    public static int[] spectralConvolutionDecreasingK( int[] spectrum ) {
        int[] conv = spectralConvolution(spectrum);
        ArrayList<Integer> uniq = new ArrayList<>();
        for(int i = 0; i < conv.length; ++i) {
            if(!uniq.contains(conv[i])) {
                uniq.add(conv[i]);
            }
        }
        HashMap<Integer,Integer> counts = new HashMap<>();
        for(int i = 0; i < conv.length; ++i) {
            if(!counts.containsKey(conv[i])) {
                counts.put(conv[i],1);
            }
            else {
                int prev = counts.get(conv[i]);
                counts.put(conv[i],prev+1);
            }
        }
        Integer[] vals = counts.values().toArray(new Integer[0]);
        Arrays.sort(vals);
        ArrayList<Integer> valsUniq = new ArrayList<>();
        for(int i = 0; i < vals.length; ++i) {
            if(!valsUniq.contains(vals[i])) {
                valsUniq.add(vals[i]);
            }
        }
        int[] out = new int[conv.length];
        int add = 0;
        for(int i = valsUniq.size()-1; i >= 0; --i) {
            int count = valsUniq.get(i);
            for(int j = 0; j < uniq.size(); ++j) {
                int curr = uniq.get(j);
                if(counts.get(curr) == count) {
                    for(int k = 0; k < count; ++k) {
                        out[add++] = curr;
                    }
                }
            }
        }
        
        return out;
    }
}