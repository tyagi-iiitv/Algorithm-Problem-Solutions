public class IntroMendel {
  public static void main( String[] args ) {
    int k = 26;
    int m = 25;
    int n = 27;
    System.out.println(dominantPhenotype(k,m,n));
  }
  
  public static double dominantPhenotype( int k, int m, int n ) {
    int tot = k+m+n;
    double recRec = ((double)n/tot) * ((double)(n-1)/(tot-1));
    double recHet = ((double)n/tot) * ((double)m/(tot-1));
    double hetHet = ((double)m/tot) * ((double)(m-1)/(tot-1)) * 0.25;
    return 1 - (recRec + recHet + hetHet);
  }
}