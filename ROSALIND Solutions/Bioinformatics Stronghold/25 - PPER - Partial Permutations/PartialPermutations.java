import java.math.*;
public class PartialPermutations {
  public static void main( String[] args ) {
    int n = 84;
    int k = 9;
    System.out.println(nPk(n,k).mod(new BigInteger("1000000")));
  }
  
  public static BigInteger nPk( int n, int k ) {
    BigInteger out = BigInteger.ONE;
    for(int i = n-k+1; i <= n; ++i) {
      out = out.multiply(new BigInteger(""+i));
    }
    return out;
  }
}