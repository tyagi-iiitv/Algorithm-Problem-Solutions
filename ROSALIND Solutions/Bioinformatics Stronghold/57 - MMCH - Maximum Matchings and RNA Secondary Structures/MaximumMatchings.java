import java.math.*;
public class MaximumMatchings {
  public static void main( String[] args ) {
    String s = "AAGCUUUAAUACGACACCUGAAAGCUGAGUCUGAGGGAAUGACAUUAAACUUUAUUCGCACUACGCCCGGGGGUCGCGGAAUGUUUCCG";
    System.out.println(maximumMatchings(s));
  }
  
  public static BigInteger maximumMatchings( String seq ) {
    int[] counts = new int[4];
    for(int i = 0; i < seq.length(); ++i) {
      switch(seq.charAt(i)) {
        case 'A': ++counts[0]; break;
        case 'C': ++counts[1]; break;
        case 'G': ++counts[2]; break;
        case 'U': ++counts[3]; break;
      }
    }
    int maxAU = Math.max(counts[0],counts[3]);
    int minAU = Math.min(counts[0],counts[3]);
    int maxGC = Math.max(counts[1],counts[2]);
    int minGC = Math.min(counts[1],counts[2]);
    System.out.println("maxAU: " + maxAU);
    System.out.println("minAU: " + minAU);
    System.out.println("maxGC: " + maxGC);
    System.out.println("minGC: " + minGC);
    return nPk(maxAU,minAU).multiply(nPk(maxGC,minGC));
  }
  
  public static BigInteger nPk( int n, int k ) {
    BigInteger out = BigInteger.ONE;
    for(int i = n-k+1; i <= n; ++i) {
      out = out.multiply(new BigInteger(""+i));
    }
    return out;
  }
}