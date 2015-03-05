import java.math.*;
public class PowerDigitSum {
  public static void main( String[] args ) {
    int base = 2;
    int exp = 1000;
    System.out.println(powerDigitSum(base,exp));
  }
  
  public static long powerDigitSum( int base, int exp ) {
    BigInteger val = new BigInteger(""+base);
    String str = val.pow(exp).toString();
    long out = 0L;
    for(int i = 0; i < str.length(); ++i) {
      out += (str.charAt(i) - '0');
    }
    return out;
  }
}