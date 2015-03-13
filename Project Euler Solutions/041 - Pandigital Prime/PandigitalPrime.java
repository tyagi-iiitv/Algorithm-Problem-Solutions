import java.util.*;
public class PandigitalPrime {
  public static void main( String[] args ) {
    System.out.println(pandigitalPrime());
  }
  
  public static int pandigitalPrime() {
    int best = 2143;
    for(int i = 2145; i < 1000000000; i += 2) {
      if(isPandigital(i) && isPrime(i) && i > best) {
        best = i;
      }
    }
    return best;
  }
  
  public static boolean isPandigital( int n ) {
    String str = ""+n;
    boolean[] has = new boolean[str.length()];
    for(int i = 0; i < str.length(); ++i) {
      int c = str.charAt(i)-'0';
      if(c == 0 || c > str.length()) {
        return false;
      }
      has[c-1] = true;
    }
    for(boolean v : has) {
      if(!v) {
        return false;
      }
    }
    return true;
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