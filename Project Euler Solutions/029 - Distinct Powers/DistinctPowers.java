import java.util.*;
import java.math.*;
public class DistinctPowers {
  public static void main( String[] args ) {
    int minA = 2;
    int maxA = 100;
    int minB = 2;
    int maxB = 100;
    System.out.println(distinctPowers(minA,maxA,minB,maxB));
  }
  
  public static int distinctPowers( int minA, int maxA, int minB, int maxB ) {
    HashSet<BigInteger> set = new HashSet<>();
    for(int a = minA; a <= maxA; ++a) {
      BigInteger A = new BigInteger(""+a);
      for(int b = minB; b <= maxB; ++b) {
        set.add(A.pow(b));
      }
    }
    return set.size();
  }
}