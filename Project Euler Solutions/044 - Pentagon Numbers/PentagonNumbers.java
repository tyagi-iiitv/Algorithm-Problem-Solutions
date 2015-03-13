public class PentagonNumbers {
  public static void main( String[] args ) {
    int max = 10000;
    System.out.println(pentagonNumbers(max));
  }
  
  public static long pentagonNumbers( int max ) {
    long[] p = new long[max];
    for(int i = 1; i <= max; ++i) {
      p[i-1] = i * (3*i - 1) / 2;
    }
    long min = Long.MAX_VALUE;
    for(int j = 0; j < p.length-1; ++j) {
      for(int k = j+1; k < p.length; ++k) {
        long sum  = p[k] + p[j];
        long diff = p[k] - p[j];
        if(isPent(sum) && isPent(diff) && diff < min) {
          min = diff;
        }
      }
    }
    return min;
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