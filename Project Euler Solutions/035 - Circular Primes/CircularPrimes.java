import java.util.*;
public class CircularPrimes {
  public static void main( String[] args ) {
    int max = 1000000;
    System.out.println(circularPrimes(max));
  }
  
  public static int circularPrimes( int max ) {
    int count = 1;
    for(int i = 3; i < max; i += 2) {
      if(isPrime(i)) {
        String s = ""+i;
        ArrayList<String> strings = rotations(s);
        strings.remove(s);
        boolean valid = true;
        for(String p : strings) {
          if(!isPrime(Integer.parseInt(p))) {
            valid = false;
            break;
          }
        }
        if(valid) {
          ++count;
        }
      }
    }
    return count;
  }
  
  public static ArrayList<String> rotations( String s ) {
    ArrayList<String> out = new ArrayList<>();
    out.add(s);
    for(int i = 1; i < s.length(); ++i) {
      out.add(s.substring(i,s.length()) + s.substring(0,i));
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