public class SpecialPythagoreanTriplet {
  public static void main( String[] args ) {
    int n = 1000;
    System.out.println(pythagoreanTriplet(n));
  }
  
  public static long pythagoreanTriplet( int n ) {
    for(long a = 1; a < n; ++a) {
      for(long b = 1; b < n-a; ++b) {
        long c = 1000-a-b;
        if((a*a+b*b) == c*c) {
          return a*b*c;
        }
      }
    }
    return -1;
  }
}