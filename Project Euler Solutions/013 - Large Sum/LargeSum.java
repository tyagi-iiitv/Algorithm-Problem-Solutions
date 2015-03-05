import java.io.*;
import java.math.*;
import java.util.*;
public class LargeSum {
  public static void main( String[] args ) {
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      ArrayList<BigInteger> in = new ArrayList<>();
      String l;
      while((l = r.readLine()) != null) {
        in.add(new BigInteger(l));
      }
      System.out.println(largeSum(in).toString().substring(0,10));
    } catch(Exception e) {}
  }
  
  public static BigInteger largeSum( ArrayList<BigInteger> nums ) {
    BigInteger sum = BigInteger.ZERO;
    for(BigInteger i : nums) {
        sum = sum.add(i);
    }
    return sum;
  }
}