import java.io.*;
public class BuildHeap {
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
    int[] out = buildHeap(A);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static int[] buildHeap( int[] A ) {
    int[] heap = new int[A.length];
    heap[0] = A[0];
    for(int i = 1; i < A.length; ++i) {
      heap[i] = A[i];
      int curr = i;
      while(curr != 0 && heap[parent(curr)] < heap[curr]) {
        int temp = heap[parent(curr)];
        heap[parent(curr)] = heap[curr];
        heap[curr] = temp;
        curr = parent(curr);
      }
    }
    return heap;
  }
  
  public static int parent( int i ) {
    return (int)Math.ceil(i/2.0) - 1;
  }
  
  public static int leftChild( int i ) {
    return (2*i);
  }
  
  public static int rightChild( int i ) {
    return (2*i + 1);
  }
}