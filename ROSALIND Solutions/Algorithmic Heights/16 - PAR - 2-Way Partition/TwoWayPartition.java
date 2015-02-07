import java.io.*;
public class TwoWayPartition {
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
    int[] B = twoWayPartition(A);
    for(int i = 0; i < B.length; ++i) {
      System.out.print(B[i] + " ");
    }
  }
  
  public static int[] twoWayPartition( int[] A ) {
    int q = 0;
    for(int i = 1; i < A.length; ++i) {
      if(A[0] > A[i]) {
        ++q;
      }
    }
    if(q == 0) {
      return A;
    }
    if(q == A.length-1) {
      int temp = A[0];
      A[0] = A[A.length-1];
      A[A.length-1] = A[0];
      return A;
    }
    int temp = A[0];
    A[0] = A[q];
    A[q] = temp;
    int i = 0;
    int j = A.length-1;
    boolean swapped = true;
    while(swapped) {
      swapped = false;
      boolean foundI = false;
      while(true) {
        if(i == q) {
          break;
        }
        if(A[i] > A[q]) {
          foundI = true;
          break;
        }
        else {
          ++i;
        }
      }
      boolean foundJ = false;
      while(true) {
        if(j == q) {
          break;
        }
        if(A[j] < A[q]) {
          foundJ = true;
          break;
        }
        else {
          --j;
        }
      }
      if(foundI && foundJ) {
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        swapped = true;
      }
    }
    return A;
  }
}