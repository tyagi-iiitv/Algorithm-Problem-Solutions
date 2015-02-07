public class ExpectedNumberOfRestrictionSites {
  public static void main( String[] args ) {
    int n = 885537;
    String s = "TAGTGCCC";
    double[] A = {0.000,0.065,0.131,0.210,0.273,0.287,0.343,0.406,0.492,0.547,0.561,0.660,0.673,0.749,0.808,0.885,0.940,1.000};
    double[] out = expectedNumber(n,s,A);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static double[] expectedNumber( int n, String s, double[] A ) {
    double[] out = new double[A.length];
    for(int p = 0; p < A.length; ++p) {
      double gc = A[p];
      double prob = 1.0;
      for(int i = 0; i < s.length(); ++i) {
        switch(s.charAt(i)) {
          case 'A': prob *= ((1-gc)/2); break;
          case 'T': prob *= ((1-gc)/2); break;
          case 'G': prob *= (gc/2); break;
          case 'C': prob *= (gc/2); break;
        }
      }
      out[p] = prob * (n - s.length() + 1);
    }
    return out;
  }
}