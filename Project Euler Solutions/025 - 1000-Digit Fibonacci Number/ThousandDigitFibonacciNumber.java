import java.util.*;
import java.math.*;
public class ThousandDigitFibonacciNumber {
  public static void main( String[] args ) {
    int n = 1000;
    System.out.println(fibWithDigits(n));
  }
  
  public static int fibWithDigits( int n ) {
    ArrayList<BigInteger> fib = new ArrayList<>();
    fib.add(BigInteger.ONE);
    fib.add(BigInteger.ONE);
    while(true) {
      if(fib.get(fib.size()-1).toString().length() == n) {
        return fib.size();
      }
      fib.add(fib.get(fib.size()-2).add(fib.get(fib.size()-1)));
    }
  }
}