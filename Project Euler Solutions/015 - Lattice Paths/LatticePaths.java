import java.math.*;
public class LatticePaths {
  public static void main( String[] args ) {
    int s = 20;
    System.out.println(latticePaths(s));
  }
  
  public static BigInteger latticePaths( int n ) {
    // answer is 2n choose n
    return nCk(2*n,n);
  }
  
  public static BigInteger nCk( int n, int k ) {
    BigInteger kFact = BigInteger.ONE;
    for(int i = 2; i <= k; ++i) {
      kFact = kFact.multiply(new BigInteger(""+i));
    }
    return nPk(n,k).divide(kFact);
  }
  
  public static BigInteger nPk( int n, int k ) {
    BigInteger out = BigInteger.ONE;
    for(int i = n-k+1; i <= n; ++i) {
      out = out.multiply(new BigInteger(""+i));
    }
    return out;
  }
}