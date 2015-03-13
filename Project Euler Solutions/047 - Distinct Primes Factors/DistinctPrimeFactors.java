import java.util.*;
public class DistinctPrimeFactors {
  public static ArrayList<Long> primes = new ArrayList<>();
  
  public static void main( String[] args ) {
    primes.add(2L);
    primes.add(3L);
    System.out.println(fourDistinctPrimeFactors());
  }
  
  public static long fourDistinctPrimeFactors() {
    long[] nums = {2,3,4,5};
    HashSet<Long> factors1 = primeFactors(2);
    HashSet<Long> factors2 = primeFactors(3);
    HashSet<Long> factors3 = primeFactors(4);
    HashSet<Long> factors4 = primeFactors(5);
    while(true) {
      if(factors1.size() == 4 && factors2.size() == 4 && factors3.size() == 4 && factors4.size() == 4) {
        return nums[0];
      }
      nums[0]++;
      nums[1]++;
      nums[2]++;
      nums[3]++;
      factors1 = factors2;
      factors2 = factors3;
      factors3 = factors4;
      factors4 = primeFactors(nums[3]);
    }
  }
  
  public static HashSet<Long> primeFactors( long n ) {
    if(primes.get(primes.size()-1) < n) {
      expandPrimes(n);
    }
    HashSet<Long> out = new HashSet<>();
    int currPrimeIndex = 0;
    long currPrime = -1;
    while(n != 1L) {
      currPrime = primes.get(currPrimeIndex++);
      while(n%currPrime == 0) {
        out.add(currPrime);
        n /= currPrime;
      }
    }
    return out;
  }
  
  public static void expandPrimes( long n ) {
    long i = primes.get(primes.size()-1)+2;
    while(primes.get(primes.size()-1) < n) {
      if(isPrime(i)) {
        primes.add(i);
      }
      i += 2;
    }
  }
  
  public static boolean isPrime( long n ) {
    if(n < 2 || n%2 == 0) {
      return false;
    }
    for(long i = 3; i*i <= n; i += 2) {
      if(n%i == 0) {
        return false;
      }
    }
    return true;
  }
}