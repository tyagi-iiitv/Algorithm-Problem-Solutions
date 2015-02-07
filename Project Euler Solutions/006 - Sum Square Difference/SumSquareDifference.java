public class SumSquareDifference {
  public static void main( String[] args ) {
    int n = 100;
    System.out.println(sumSquareDifference(n));
  }
  
  public static long sumSquareDifference( int n ) {
    return squareOfSums(n) - sumOfSquares(n);
  }
  
  public static long sumOfSquares( int n ) {
    long sum = 1L;
    for(int i = 2; i <= n; ++i) {
      sum += i*i;
    }
    return sum;
  }
  
  public static long squareOfSums( int n ) {
    long sum = 1L;
    for(int i = 2; i <= n; ++i) {
      sum += i;
    }
    return sum*sum;
  }
}