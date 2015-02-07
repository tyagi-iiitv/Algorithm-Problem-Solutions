public class SummationOfPrimes {
  public static void main( String[] args ) {
    int max = 2000000;
    System.out.println(sumOfPrimes(max));
  }
   
  public static long sumOfPrimes( int n ) {
    long sum = 2L;
    for(int i = 3; i < n; i += 2) {
      if(isPrime(i)) {
        sum += i;
      }
    }
    return sum;
  }
   
  public static boolean isPrime( int n ) {
    if(n%2 == 0) {
      return false;
    }
    int sqrt = (int)Math.sqrt(n);
    for(int i = 3; i <= sqrt; ++i) {
      if(n%i == 0) {
        return false;
      }
    }
    return true;
  }
}