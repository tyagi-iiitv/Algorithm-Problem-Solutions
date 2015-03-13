public class TriangularPentagonalHexagonal {
  public static void main( String[] args ) {
    long start = 286;
    System.out.println(triangularPentagonalHexagonal(start));
  }
  
  public static long triangularPentagonalHexagonal( long start ) {
    long t = start * (start + 1) / 2;
    long add = start+1;
    while(true) {
      if(isPent(t) && isHex(t)) {
        return t;
      }
      t += add++;
    }
  }
  
  public static boolean isHex( long h ) {
    long under = 8*h + 1;
    long sqrtUnder = (long)(Math.sqrt(under));
    if(sqrtUnder*sqrtUnder != under) {
      return false;
    }
    return (1 + sqrtUnder) % 4 == 0;
  }
  
  public static boolean isPent( long p ) {
    long under = 24*p + 1;
    long sqrtUnder = (long)(Math.sqrt(under));
    if(sqrtUnder*sqrtUnder != under) {
      return false;
    }
    return (1 + sqrtUnder) % 6 == 0;
  }
}