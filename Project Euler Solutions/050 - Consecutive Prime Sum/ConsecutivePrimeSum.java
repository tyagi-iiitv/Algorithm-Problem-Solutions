import java.util.*;
public class ConsecutivePrimeSum {
  public static void main( String[] args ) {
    int max = 1000000;
    System.out.println(consecutivePrimeSum(max));
  }
  
  public static int consecutivePrimeSum( int max ) {
    int result = 0;
    ArrayList<Integer> primes = new ArrayList<>();
    primes.add(2);
    for(int i = 3; i < max; i += 2) {
      if(isPrime(i)) {
        primes.add(i);
      }
    }
    int[] primeSum = new int[primes.size()+1];
    for(int i = 0; i < primes.size(); ++i) {
      primeSum[i+1] = primeSum[i] + primes.get(i);
    }
    int numPrimes = 0;
    int out = 0;
    for(int i = numPrimes; i < primeSum.length; ++i) {
      for(int j = i - numPrimes + 1; j >= 0; --j) {
        if(primeSum[i] - primeSum[j] > max) {
          break;
        }
        if(isPrime(primeSum[i]-primeSum[j])) {
          numPrimes = i-j;
          out = primeSum[i] - primeSum[j];
        }
      }
    }
    return out;
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