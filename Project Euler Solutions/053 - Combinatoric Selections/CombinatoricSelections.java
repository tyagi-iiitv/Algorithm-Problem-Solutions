import java.math.*;
public class CombinatoricSelections {
  public static void main( String[] args ) {
    int max = 100;
    int thresh = 1000000;
    System.out.println(combinatoricSelections(max,thresh));
  }
  
  public static int combinatoricSelections( int max, int threshInt ) {
    BigInteger thresh = new BigInteger(""+threshInt);
    BigInteger[] factorials = factorials(max);
    int count = 0;
    for(int n = 1; n <= max; ++n) {
      for(int r = 1; r <= n; ++r) {
        if(factorials[n].divide(factorials[r]).divide(factorials[n-r]).compareTo(thresh) > 0) {
          ++count;
        }
      }
    }
    return count;
  }
  
  public static BigInteger[] factorials( int max ) {
    BigInteger[] out = new BigInteger[max+1];
    out[0] = BigInteger.ONE;
    out[1] = BigInteger.ONE;
    for(int i = 2; i <= max; ++i) {
      out[i] = out[i-1].multiply(new BigInteger(""+i));
    }
    return out;
  }
}