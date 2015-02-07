import java.io.*;
public class MergeSort {
  public static void main( String[] args ) {
    int[] A = null;
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      r.readLine();
      String[] str = r.readLine().split(" ");
      A = new int[str.length];
      for(int i = 0; i < A.length; ++i) {
        A[i] = Integer.parseInt(str[i]);
      }
    } catch(Exception e) {}
    int[] out = mergeSort(A);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static int[] mergeSort( int[] A ) {
    if(A.length == 1) {
      return A;
    }
    else {
      int[] a1 = new int[A.length/2];
      int[] a2 = new int[A.length-a1.length];
      int ind = 0;
      for(int i = 0; i < a1.length; ++i) {
        a1[i] = A[ind++];
      }
      for(int i = 0; i < a2.length; ++i) {
        a2[i] = A[ind++];
      }
      int[] a1s = mergeSort(a1);
      int[] a2s = mergeSort(a2);
      return mergeSortedArrays(a1s,a2s);
    }
  }
  
  public static int[] mergeSortedArrays( int[] A, int[] B ) {
    if(A.length == 0) {
      return B;
    }
    if(B.length == 0) {
      return A;
    }
    int[] out = new int[A.length+B.length];
    int ind = 0;
    int indA = 0;
    int indB = 0;
    while(indA < A.length && indB < B.length) {
      if(A[indA] < B[indB]) {
        out[ind++] = A[indA++];
      }
      else {
        out[ind++] = B[indB++];
      }
    }
    if(indA == A.length && indB != B.length) {
      while(indB < B.length) {
        out[ind++] = B[indB++];
      }
    }
    else if(indA != A.length && indB == B.length) {
      while(indA < A.length) {
        out[ind++] = A[indA++];
      }
    }
    return out;
  }
}