import java.io.*;
public class CountingInversions {
  public static void main( String[] args ) {
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      r.readLine();
      String[] p = r.readLine().split(" ");
      int[] arr = new int[p.length];
      for(int i = 0; i < arr.length; ++i) {
        arr[i] = Integer.parseInt(p[i]);
      }
      System.out.println(inversions(arr));
    } catch(Exception e) {}
  }
  
  public static int inversions( int[] a ) {
    int c = 0;
    for(int i = 0; i < a.length-1; ++i) {
      for(int j = i+1; j < a.length; ++j) {
        if(a[i] > a[j]) {
          ++c;
        }
      }
    }
    return c;
  }
}