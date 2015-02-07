public class NthPrime {
  public static void main( String[] args ) {
    int n = 10001;
    System.out.println(nthPrime(n));
  }
  
  public static long nthPrime( int n ) {
    int count = 1;
    long best = 2L;
    long curr = best;
    while(count < n) {
      if(isPrime(++curr)) {
        best = curr;
        ++count;
      }
    }
    return best;
  }
  
  public static boolean isPrime( long n ) {
    if(n%2 == 0) {
      return false;
    }
    long sqrt = (long)Math.sqrt(n);
    for(long i = 3; i <= sqrt; ++i) {
      if(n%i == 0) {
        return false;
      }
    }
    return true;
  }
}