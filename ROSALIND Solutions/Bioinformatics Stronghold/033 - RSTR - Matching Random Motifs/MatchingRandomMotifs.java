public class MatchingRandomMotifs {
  public static void main( String[] args ) {
    int N = 81915;
    double x = 0.495783;
    String s = "ACAACCGGTA";
    System.out.println(matchingRandomMotifs(N,x,s));
  }
  
  public static double matchingRandomMotifs( int N, double gc, String s ) {
    double GorC = gc/2;
    double AorT = (1-gc)/2;
    double probRandomNotMatch = (1-probRandomString(s,gc));
    return (1-Math.pow(probRandomNotMatch,N));
  }
  
  public static double probRandomString( String s, double gc ) {
    double out = 1.0;
    for(int c = 0; c < s.length(); ++c) {
      switch(s.charAt(c)) {
        case 'A': out = out * (1-gc) * 0.5; break;
        case 'T': out = out * (1-gc) * 0.5; break;
        case 'C': out = out * gc * 0.5; break;
        case 'G': out = out * gc * 0.5; break;
      }
    }
    return out;
  }
}