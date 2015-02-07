public class LargestPalindromeProduct {
  public static void main( String[] args ) {
    int digits = 3;
    System.out.println(largestPalindromeProduct(digits));
  }
  
  public static long largestPalindromeProduct( int digits ) {
    long limit = (long)Math.pow(10,digits);
    long biggest = 0L;
    for(long i = 1L; i < limit; ++i) {
      for(long j = 1L; j < limit; ++j) {
        long product = i*j;
        if(isPalindrome(""+product) && product > biggest) {
          biggest = product;
        }
      }
    }
    return biggest;
  }
  
  public static boolean isPalindrome( String s ) {
    for(int i = 0; i < s.length()/2; ++i) {
      if(s.charAt(i) != s.charAt(s.length()-1-i)) {
        return false;
      }
    }
    return true;
  }
}