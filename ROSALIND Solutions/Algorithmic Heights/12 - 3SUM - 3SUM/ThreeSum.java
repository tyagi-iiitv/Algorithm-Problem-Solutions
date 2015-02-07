import java.io.*;
import java.util.*;
public class ThreeSum {
  public static void main( String[] args ) {
    int[][] arrs = null;
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String[] l1 = r.readLine().split(" ");
      arrs = new int[Integer.parseInt(l1[0])][];
      for(int i = 0; i < arrs.length; ++i) {
        String[] split = r.readLine().split(" ");
        arrs[i] = new int[split.length];
        for(int j = 0; j < split.length; ++j) {
          arrs[i][j] = Integer.parseInt(split[j]);
        }
      }
    } catch(Exception e) {}
    String[] out = threeSum(arrs);
    for(int i = 0; i < out.length; ++i) {
      System.out.println(out[i]);
    }
  }
  
  public static String[] threeSum( int[][] arrs ) {
    String[] out = new String[arrs.length];
    for(int arr = 0; arr < arrs.length; ++arr) {
      int[] S = Arrays.copyOf(arrs[arr],arrs[arr].length);
      Arrays.sort(S);
      boolean found = false;
      for(int i = 0; i < S.length-2; ++i) {
        int a = S[i];
        int start = i+1;
        int end = S.length-1;
        while(start < end) {
          int b = S[start];
          int c = S[end];
          int sum = a+b+c;
          if(sum == 0) {
            int[] inds = new int[3];
            for(int ind = 0; ind < S.length; ++ind) {
              if(arrs[arr][ind] == S[i]) {
                inds[0] = ind+1;
              }
            }
            for(int ind = 0; ind < S.length; ++ind) {
              if(arrs[arr][ind] == S[start]) {
                inds[1] = ind+1;
              }
            }
            for(int ind = 0; ind < S.length; ++ind) {
              if(arrs[arr][ind] == S[end]) {
                inds[2] = ind+1;
              }
            }
            Arrays.sort(inds);
            out[arr] = "" + inds[0] + " " + inds[1] + " " + inds[2];
            found = true;
            break;
          }
          else if(sum > 0) {
            --end;
          }
          else {
            ++start;
          }
        }
        if(found) {
          break;
        }
      }
      if(!found) {
        out[arr] = "-1";
      }
    }
    return out;
  }
}