import java.math.*;
public class FactorialDigitSum {
  public static void main( String[] args ) {
    int n = 100;
    System.out.println(factorialDigitSum(n));
  }
  
  public static long factorialDigitSum( int n ) {
    String nFact = fact(n).toString();
    long sum = 0;
    for(int i = 0; i < nFact.length(); ++i) {
      sum += (nFact.charAt(i) - '0');
    }
    return sum;
  }
  
  public static BigInteger fact( int n ) {
    BigInteger nFact = BigInteger.ONE;
    for(int i = 2; i <= n; ++i) {
      nFact = nFact.multiply(new BigInteger(""+i));
    }
    return nFact;
  }
}