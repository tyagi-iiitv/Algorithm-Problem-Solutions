public class SmallestMultiple {
  public static void main( String[] args ) {
    int n = 20;
    System.out.println(smallestMultiple(n));
  }
  
  public static long smallestMultiple( int n ) {
    long curr = n;
    while(!isDivisible(curr,n)) {
      curr += n;
    }
    return curr;
  }
  
  public static boolean isDivisible( long curr, int n ) {
    for(int i = 1; i <= n; ++i) {
      if(curr%i != 0) {
        return false;
      }
    }
    return true;
  }
}