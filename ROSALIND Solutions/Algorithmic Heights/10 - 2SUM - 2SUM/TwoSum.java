import java.io.*;
public class TwoSum {
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
    String[] out = twoSum(arrs);
    for(int i = 0; i < out.length; ++i) {
      System.out.println(out[i]);
    }
  }
  
  public static String[] twoSum( int[][] arrs ) {
    String[] out = new String[arrs.length];
    for(int a = 0; a < arrs.length; ++a) {
      boolean found = false;
      for(int i = 0; i < arrs[a].length - 1; ++i) {
        for(int j = i+1; j < arrs[a].length; ++j) {
          if(arrs[a][i] == (-1*arrs[a][j])) {
            out[a] = "" + (i+1) + " " + (j+1);
            found = true;
            break;
          }
        }
        if(found) {
          break;
        }
      }
      if(!found) {
        out[a] = "-1";
      }
    }
    return out;
  }
}