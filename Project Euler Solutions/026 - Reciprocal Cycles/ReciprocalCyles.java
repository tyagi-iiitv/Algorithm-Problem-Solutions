import java.math.*;
public class ReciprocalCyles {
  public static void main( String[] args ) {
    int max = 1000;
    System.out.println(reciprocalCycles(max));
  }
  
  public static int reciprocalCycles( int max ) {
    int bestN = 3;
    int bestL = 1;
    for(int i = 7; i < max; ++i) {
      if(isPrime(i)) {
        int l = decimalPeriod(i);
        if(l > bestL) {
          bestL = l;
          bestN = i;
        }
      }
    }
    return bestN;
  }
  
  // assumes n is prime
  public static int decimalPeriod( int n ) {
    BigInteger bigN = new BigInteger(""+n);
    int i = 1;
    while(true) {
      BigInteger val = BigInteger.TEN.pow(i);
      if(val.mod(bigN).equals(BigInteger.ONE)) {
        return i;
      }
      ++i;
    }
  }
  
  public static boolean isPrime( int n ) {
    if(n < 2 || n%2 == 0) {
      return false;
    }
    for(int i = 3; i*i <= n; i += 2) {
      if(n%i == 0) {
        return false;
      }
    }
    return true;
  }
}