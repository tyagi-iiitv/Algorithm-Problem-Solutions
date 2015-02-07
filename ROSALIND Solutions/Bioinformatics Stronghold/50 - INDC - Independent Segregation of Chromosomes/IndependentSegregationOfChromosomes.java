import java.math.*;
import java.text.*;
public class IndependentSegregationOfChromosomes {
  public static double e = 2.71828182845904523536028747135266249775724709369995957496696762772407663035354;
  
  public static void main( String[] args ) {
    int n = 49;
    BigDecimal[] out = probs(n);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(log10(out[i]) + " ");
    }
  }
  
  public static BigDecimal[] probs( int n ) {
    BigDecimal[] out = new BigDecimal[2*n];
    out[out.length-1] = new BigDecimal("0.5").pow(2*n);
    for(int k = out.length-2; k >= 0; --k) {
      out[k] = out[k+1].add(new BigDecimal("0.5").pow(2*n).multiply(new BigDecimal(""+nCk(2*n,k+1))));
    }
    return out;
  }
  
  public static double log10( BigDecimal n ) {
    String nStr = sciNotation(n,76);
    String[] parts = nStr.split("E");
    double a = Double.parseDouble(parts[0]);
    int b = Integer.parseInt(parts[1]);
    return Math.log10(a) + b;
  }
  
  private static String sciNotation( BigDecimal x, int scale ) {
    NumberFormat formatter = new DecimalFormat("0.0E0");
    formatter.setRoundingMode(RoundingMode.HALF_UP);
    formatter.setMinimumFractionDigits(scale);
    return formatter.format(x);
  }
  
  public static BigInteger nCk( int n, int k ) {
    BigInteger kFact = BigInteger.ONE;
    for(int i = 2; i <= k; ++i) {
      kFact = kFact.multiply(new BigInteger(""+i));
    }
    return nPk(n,k).divide(kFact);
  }
  
  public static BigInteger nPk( int n, int k ) {
    BigInteger out = BigInteger.ONE;
    for(int i = n-k+1; i <= n; ++i) {
      out = out.multiply(new BigInteger(""+i));
    }
    return out;
  }
}