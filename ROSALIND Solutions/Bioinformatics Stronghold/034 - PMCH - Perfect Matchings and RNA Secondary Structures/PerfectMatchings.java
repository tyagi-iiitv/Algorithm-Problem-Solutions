import java.math.*;
public class PerfectMatchings {
  public static void main( String[] args ) {
    String s = "AGGAGAACGCCGCAUAGAUCGUACGCCUUCAUCGGGGCUCGUCUUCUGUGCUCACUUAUGAUGGCGAUAAACAAUA";
    System.out.println(perfectMatchings(s));
  }
  
  public static BigInteger perfectMatchings( String s ) {
    int a = 0;
    int c = 0;
    for(int i = 0; i < s.length(); ++i) {
      switch(s.charAt(i)) {
        case 'A': ++a; break;
        case 'C': ++c; break;
      }
    }
    return factorial(a).multiply(factorial(c));
  }
  
  public static BigInteger factorial( int n ) {
    BigInteger out = BigInteger.ONE;
    for(int i = 2; i <= n; ++i) {
      out = out.multiply(new BigInteger(""+i));
    }
    return out;
  }
}