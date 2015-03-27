import java.math.*;
public class IntroToAlternativeSplicing {
  public static void main( String[] args ) {
    int n = 1721;
    int m = 932;
    System.out.println(introToAlternativeSplicing(n,m));
  }
  
  public static int introToAlternativeSplicing( int n, int m ) {
    BigInteger out = nCk(n,m);
    for(int k = m+1; k <= n; ++k) {
      out = out.add(nCk(n,k));
    }
    return out.mod(new BigInteger("1000000")).intValue();
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