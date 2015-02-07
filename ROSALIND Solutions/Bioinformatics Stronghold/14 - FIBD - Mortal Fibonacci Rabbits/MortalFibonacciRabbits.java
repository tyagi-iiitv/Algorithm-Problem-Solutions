import java.util.*;
public class MortalFibonacciRabbits {
  public static void main( String[] args ) {
    int n = 91;
    int m = 16;
    System.out.println(mortalFibRabbits(n,m));
  }
  
  public static long mortalFibRabbits( int n, int m ) {
    long[] arr = new long[m];
    arr[m-1] = 1;
    
    for(int generation = 1; generation < n; ++generation) {
      long newR = 0;
      for(int i = 0; i < m-1; ++i) {
        newR += arr[i];
      }
      for(int i = 0; i < m-1; ++i) {
        arr[i] = arr[i+1];
      }
      arr[m-1] = newR;
    }
    
    long sum = 0;
    for(int i = 0; i < arr.length; ++i) {
      sum += arr[i];
    }
    return sum;
  }
}