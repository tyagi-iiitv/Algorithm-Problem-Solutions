import java.util.*;
public class GoldbachsOtherConjecture {
  public static ArrayList<Long> primes = new ArrayList<>();
  public static ArrayList<Long> squares = new ArrayList<>();
  public static long add = 1;

  public static void main( String[] args ) {
    primes.add(2L);
    primes.add(3L);
    squares.add(0L);
    System.out.println(smallestGoldbachFail());
  }
  
  public static long smallestGoldbachFail() {
    long n = 25;
    while(true) {
      if(failGoldbach(n)) {
        return n;
      }
      n += 2;
    }
  }
  
  public static boolean failGoldbach( long n ) {
    if(primes.get(primes.size()-1) < n) {
      expandPrimes(n);
    }
    if(squares.get(squares.size()-1) < n) {
      expandSquares(n);
    }
    for(long prime : primes) {
      for(long square : squares) {
        if(prime + 2*square == n) {
          return false;
        }
      }
    }
    return true;
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
  
  public static void expandSquares( long n ) {
    long curr = squares.get(squares.size()-1);
    while(curr < n) {
      curr += add;
      squares.add(curr);
      add += 2;
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