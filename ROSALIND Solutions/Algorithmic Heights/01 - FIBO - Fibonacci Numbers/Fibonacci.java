public class Fibonacci {
  public static void main( String[] args ) {
    int n = 25;
    System.out.print(fibonacci(n));
  }
  
  public static long fibonacci( int n ) {
    if(n == 0) {
      return 0;
    }
    if(n == 1) {
      return 1;
    }
    long[] fib = new long[n+1];
    fib[0] = 0;
    fib[1] = 1;
    for(int i = 2; i <= n; ++i) {
      fib[i] = fib[i-1] + fib[i-2];
    }
    return fib[n];
  }
}