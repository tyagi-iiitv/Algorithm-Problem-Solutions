import java.io.*;
public class HeapSort {
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
    int[] out = heapSort(A);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static int[] heapSort( int[] A ) {
    int[] heap = buildHeap(A);
    for(int i = heap.length-1; i >= 1; --i) {
      int temp = heap[0];
      heap[0] = heap[i];
      heap[i] = temp;
      int curr = 0;
      while(true) {
        int LC = -1;
        int RC = -1;
        if(leftChild(curr) < i) {
          LC = leftChild(curr);
        }
        if(rightChild(curr) < i) {
          RC = rightChild(curr);
        }
        int largerChild = -1;
        if(LC == -1 && RC == -1) {
          break;
        }
        else if(LC == -1) {
          largerChild = RC;
        }
        else if(RC == -1) {
          largerChild = LC;
        }
        else {
          if(heap[LC] > heap[RC]) {
            largerChild = LC;
          }
          else {
            largerChild = RC;
          }
        }
        if(heap[curr] > heap[largerChild]) {
          break;
        }
        temp = heap[curr];
        heap[curr] = heap[largerChild];
        heap[largerChild] = temp;
        curr = largerChild;
      }
    }
    return heap;
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
    return (i-1)/2;
  }
  
  public static int leftChild( int i ) {
    return 2*i+1;
  }
  
  public static int rightChild( int i ) {
    return 2*i+2;
  }
}