public class FibRabbits {
  public static void main( String[] args ) {
    int n = 5;
    int k = 3;
    System.out.println(fibRabbits(n,k));
  }
  
  public static int fibRabbits( int n, int k ) {
    int[] fib = new int[n+1];
    fib[0] = 0;
    fib[1] = 1;
    for(int i = 2; i <= n; ++i) {
      fib[i] = fib[i-1] + k*fib[i-2];
    }
    return fib[n];
  }
}