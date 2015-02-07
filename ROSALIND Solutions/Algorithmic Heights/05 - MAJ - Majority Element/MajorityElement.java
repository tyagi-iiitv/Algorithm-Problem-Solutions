import java.util.*;
import java.io.*;
public class MajorityElement {
  public static void main( String[] args ) {
    int[][] arrs = null;
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String line;
      String currSeq = "";
      String[] l1 = r.readLine().split(" ");
      arrs  = new int[Integer.parseInt(l1[0])][];
      for(int i = 0; i < arrs.length; ++i) {
        line = r.readLine();
        String[] split = line.split(" ");
        arrs[i] = new int[split.length];
        for(int j = 0; j < split.length; ++j) {
          arrs[i][j] = Integer.parseInt(split[j]);
        }
      }
    } catch(Exception e) {}
    int[] out = majorityElements(arrs);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static int[] majorityElements( int[][] arrs ) {
    int[] out = new int[arrs.length];
    for(int i = 0; i < arrs.length; ++i) {
      ArrayList<Integer> nums = new ArrayList<>();
      int[] counts = new int[arrs[i].length];
      for(int j = 0; j < arrs[i].length; ++j) {
        int curr = arrs[i][j];
        int ind = nums.indexOf(curr);
        if(ind == -1) {
          ++counts[nums.size()];
          nums.add(curr);
        }
        else {
          ++counts[ind];
        }
      }
      int max = -1;
      int best = -1;
      for(int j = 0; j < counts.length; ++j) {
        if(counts[j] > max) {
          max = counts[j];
          best = nums.get(j);
        }
      }
      if(max > (double)arrs[i].length/2) {
        out[i] = best;
      }
      else {
        out[i] = -1;
      }
    }
    return out;
  }
}