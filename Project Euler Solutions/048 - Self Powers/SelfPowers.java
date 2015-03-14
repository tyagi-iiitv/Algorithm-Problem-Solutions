import java.math.*;
public class SelfPowers {
  public static void main( String[] args ) {
    int max = 1000;
    System.out.println(lastTenDigits(selfPowers(max)));
  }
  
  public static BigInteger selfPowers( int max ) {
    BigInteger out = BigInteger.ZERO;
    for(int i = 1; i <= max; ++i) {
      BigInteger bigI = new BigInteger(""+i);
      out = out.add(bigI.pow(i));
    }
    return out;
  }
  
  public static String lastTenDigits( BigInteger n ) {
    String s = n.toString();
    return s.substring(s.length()-10,s.length());
  }
}