import java.io.*;
import java.util.*;
public class ThreeWayPartition {
  public static void main( String[] args ) {
    int[] A = null;
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      r.readLine();
      String[] p = r.readLine().split(" ");
      A = new int[p.length];
      for(int i = 0; i < A.length; ++i) {
        A[i] = Integer.parseInt(p[i]);
      }
    } catch(Exception e) {}
    int[] B = threeWayPartition(A);
    for(int i = 0; i < B.length; ++i) {
      System.out.print(B[i] + " ");
    }
  }
  
  public static int[] threeWayPartition( int[] A ) {
    int val = A[0];
    int c = 1;
    ArrayList<Integer> pre = new ArrayList<>();
    ArrayList<Integer> post = new ArrayList<>();
    for(int i = 1; i < A.length; ++i) {
      if(A[i] == val) {
        ++c;
      }
      else if(A[i] > val) {
        post.add(A[i]);
      }
      else {
        pre.add(A[i]);
      }
    }
    int[] B = new int[A.length];
    int add = 0;
    for(int i = 0; i < pre.size(); ++i) {
      B[add++] = pre.get(i);
    }
    for(int i = 0; i < c; ++i) {
      B[add++] = val;
    }
    for(int i = 0; i < post.size(); ++i) {
      B[add++] = post.get(i);
    }
    return B;
  }
}