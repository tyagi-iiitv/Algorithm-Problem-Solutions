import java.util.*;
public class NonAbundantSums {
  public static void main( String[] args ) {
    System.out.println(nonAbundantSums());
  }
  
  public static long nonAbundantSums() {
    boolean[] isAbundant = new boolean[28124];
    ArrayList<Integer> abundant = new ArrayList<>();
    for(int i = 12; i <= 28123; ++i) {
      if(d(i) > i) {
        isAbundant[i] = true;
        abundant.add(i);
      }
    }
    long sum = 0;
    for(int i = 1; i <= 28123; ++i) {
      boolean success = true;
      for(int curr : abundant) {
        if(i-curr >= 0 && isAbundant[i-curr]) {
          success = false;
          break;
        }
      }
      if(success) {
        sum += i;
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