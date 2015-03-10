public class QuadraticPrimes {
  public static void main( String[] args ) {
    int maxA = 1000;
    int maxB = 1000;
    System.out.println(quadraticPrimes(maxA,maxB));
  }
  
  public static int quadraticPrimes( int maxA, int maxB ) {
    int bestA = Integer.MIN_VALUE;
    int bestB = Integer.MIN_VALUE;
    int best = 0;
    for(int a = (-1*maxA)+1; a < maxA; ++a) {
      for(int b = (-1*maxB)+1; b < maxB; ++b) {
        int consec = 0;
        int n = 0;
        while(true) {
          int y = n*n + a*n + b;
          if(isPrime(y)) {
            ++consec;
            ++n;
          }
          else {
            break;
          }
        }
        if(consec > best) {
          best = consec;
          bestA = a;
          bestB = b;
        }
      }
    }
    return bestA*bestB;
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