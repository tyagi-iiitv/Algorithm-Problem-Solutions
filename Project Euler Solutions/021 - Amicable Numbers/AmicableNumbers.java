import java.util.*;
public class AmicableNumbers {
  public static void main( String[] args ) {
    int max = 10000;
    System.out.println(sumAmicableNumbers(max));
  }
  
  public static long sumAmicableNumbers( int max ) {
    long sum = 0;
    for(int i = 2; i <= max; ++i) {
      int dI = d(i);
      if(dI > i && dI <= max) {
        int dJ = d(dI);
        if(dJ == i) {
          sum += i + dI;
        }
      }
    }
    return sum;
  }
  
  public static int d( int n ) {
    int sqrt = (int)(Math.sqrt(n));
    int sum = 0;
    for(int i = 1; i <= sqrt; ++i) {
      if(n%i == 0) {
        sum += i;
        if(i != 1 && i != (n/i)) {
          sum += (n/i);
        }
      }
    }
    return sum;
  }
}