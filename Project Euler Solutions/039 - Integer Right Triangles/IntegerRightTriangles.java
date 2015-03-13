public class IntegerRightTriangles {
  public static void main( String[] args ) {
    int max = 1000;
    System.out.println(integerRightTriangles(max));
  }
  
  public static int integerRightTriangles( int max ) {
    int[] ps = new int[max+1];
    for(int a = 1; a < max; ++a) {
      for(int b = 1; b < max; ++b) {
        int cSquare = a*a + b*b;
        if(isPerfectSquare(cSquare)) {
          int p = (int)(Math.sqrt(cSquare)) + a + b;
          if(p <= max) {
            ++ps[p];
          }
        }
      }
    }
    int bestP = 0;
    for(int i = 0; i <= max; ++i) {
      if(ps[i] > ps[bestP]) {
        bestP = i;
      }
    }
    return bestP;
  }
  
  public static boolean isPerfectSquare( int n ) {
    int sqrt = (int)(Math.sqrt(n));
    return n == sqrt*sqrt;
  }
}