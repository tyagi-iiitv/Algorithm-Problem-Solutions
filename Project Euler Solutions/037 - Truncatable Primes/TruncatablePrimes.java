public class TruncatablePrimes {
  public static void main( String[] args ) {
    System.out.println(truncatablePrimes());
  }
  
  public static long truncatablePrimes() {
    long sum = 0;
    int found = 0;
    int i = 11;
    while(found < 11) {
      if(isTruncatablePrime(i)) {
        sum += i;
        ++found;
      }
      i += 2;
    }
    return sum;
  }
  
  public static boolean isTruncatablePrime( int n ) {
    if(n == 23) {
      return true;
    }
    if(hasEvenDigit(n) || !isPrime(n)) {
      return false;
    }
    String s = ""+n;
    for(int i = 1; i < s.length(); ++i) {
      if(!isPrime(Integer.parseInt(s.substring(i,s.length())))) {
        return false;
      }
      if(!isPrime(Integer.parseInt(s.substring(0,i)))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean hasEvenDigit( int n ) {
    if(n == 0) {
      return true;
    }
    while(n != 0) {
      if(n%2 == 0) {
        return true;
      }
      n /= 10;
    }
    return false;
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