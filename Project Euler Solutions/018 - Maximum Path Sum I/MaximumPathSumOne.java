import java.util.*;
import java.io.*;
public class MaximumPathSumOne {
  public static void main( String[] args ) {
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String l;
      ArrayList<String> in = new ArrayList<>();
      while((l = r.readLine()) != null) {
        in.add(l.trim());
      }
      System.out.println(maximumPathSumOne(in));
    } catch(Exception e) {}
  }
  
  public static int maximumPathSumOne( ArrayList<String> in ) {
    int[][] triangle = inputToTriangle(in);
    int[] row = triangle[0];
    for(int i = 1; i < triangle.length; ++i) {
      int[] next = new int[triangle[i].length];
      next[0] = row[0] + triangle[i][0];
      next[next.length-1] = row[row.length-1] + triangle[i][triangle[i].length-1];
      for(int j = 1; j < next.length-1; ++j) {
        next[j] = Integer.max(row[j-1],row[j]) + triangle[i][j];
      }
      row = next;
    }
    int max = 0;
    for(int i = 0; i < row.length; ++i) {
      if(row[i] > max) {
        max = row[i];
      }
    }
    return max;
  }
  
  public static int[][] inputToTriangle( ArrayList<String> in ) {
    int[][] triangle = new int[in.size()][];
    for(int i = 0; i < triangle.length; ++i) {
      String[] parts = in.get(i).split(" ");
      triangle[i] = new int[parts.length];
      for(int j = 0; j < parts.length; ++j) {
        triangle[i][j] = Integer.parseInt(parts[j]);
      }
    }
    return triangle;
  }
}