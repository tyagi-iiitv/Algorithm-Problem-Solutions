import java.math.*;
public class CountingSubsets {
  public static void main( String[] args ) {
    int n = 992;
    System.out.println(numSubsets(n));
  }
  
  public static int numSubsets( int n ) {
    return new BigInteger("2").pow(n).mod(new BigInteger("1000000")).intValue();
  }
}