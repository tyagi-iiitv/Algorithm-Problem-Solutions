public class HighlyDivisibleTriangularNumber {
  public static void main( String[] args ) {
    int n = 500;
    System.out.println(highlyDivisibleTriangularNumber(n));
  }
  
  public static long highlyDivisibleTriangularNumber( int n ) {
    long t = 1;
    int d = 1;
    int i = 1;
    while(d <= n) {
      t += ++i;
      d = numDivisors(t);
    }
    return t;
  }
  
  public static int numDivisors( long n ) {
    if(n == 1) {
      return 1;
    }
    int c = 2; // 1 and n
    for(int i = 2; i <= n/2; ++i) {
      if(n%i == 0) {
        ++c;
      }
    }
    return c;
  }
  
  public static long triangleNumber( int n ) {
    long out = 1;
    for(int i = 2; i <= n; ++i) {
      out += i;
    }
    return out;
  }
}