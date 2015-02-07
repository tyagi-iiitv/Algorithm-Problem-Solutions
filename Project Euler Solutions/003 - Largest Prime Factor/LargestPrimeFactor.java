public class LargestPrimeFactor {
  public static void main( String[] args ) {
    long n = 600851475143L;
    System.out.println(largestPrimeFactor(n));
  }
  
  public static long largestPrimeFactor( long n ) {
    long divisor = 2;
    while(n > 1) {
      if(n%divisor == 0) {
        n = n/divisor--;
      }
      ++divisor;
    }
    return divisor;
  }
}