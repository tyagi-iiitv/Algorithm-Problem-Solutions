public class NumberSpiralDiagonals {
  public static void main( String[] args ) {
    int n = 1001;
    System.out.println(numberSpiralDiagonals(n));
  }
  
  public static long numberSpiralDiagonals( int n ) {
    if(n == 1) {
      return 1;
    }
    long sum = 1;    
    for(int i = 3; i <= n; i += 2) {
      int square = i*i;
      sum += square; // expand up-right
      square -= (i-1);
      sum += square; // expand up-left
      square -= (i-1);
      sum += square; // expand down-left
      square -= (i-1);
      sum += square; // expand down-right
    }
    return sum;
  }
}