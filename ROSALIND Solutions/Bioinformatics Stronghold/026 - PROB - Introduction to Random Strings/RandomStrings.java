import java.math.*;
public class RandomStrings {
  public static void main( String[] args ) {
    String s = "AAGTGCTCAGGACAGTCGGTTGGTACGTTATGGTACAGTTCACGTGTAATCAAGGTGAAAGCCCTGTAGCGTGAGCTAGACACTG";
    String nums = "0.098 0.155 0.197 0.254 0.285 0.343 0.417 0.462 0.477 0.555 0.612 0.648 0.693 0.744 0.792 0.892 0.918";
    String[] parts = nums.split(" ");
    double[] A = new double[parts.length];
    for(int i = 0; i < A.length; ++i) {
      A[i] = Double.parseDouble(parts[i]);
    }
    double[] out = logProbRandomString(s,A);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(round(out[i],3) + " ");
    }
  }
  
  public static double[] logProbRandomString( String s, double[] A ) {
    double[] out = new double[A.length];
    for(int i = 0; i < A.length; ++i) {
      double gc = A[i];
      out[i] = 1.0;
      for(int c = 0; c < s.length(); ++c) {
        switch(s.charAt(c)) {
          case 'A': out[i] = out[i] * (1-gc) * 0.5; break;
          case 'T': out[i] = out[i] * (1-gc) * 0.5; break;
          case 'C': out[i] = out[i] * gc * 0.5; break;
          case 'G': out[i] = out[i] * gc * 0.5; break;
        }
      }
      out[i] = Math.log10(out[i]);
    }
    return out;
  }
  
  public static double round(double value, int places) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}