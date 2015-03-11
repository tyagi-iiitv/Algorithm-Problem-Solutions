public class DoubleBasePalindromes {
  public static void main( String[] args ) {
    int max = 1000000;
    System.out.println(doubleBasePalindromes(max));
  }
  
  public static long doubleBasePalindromes( int max ) {
    long sum = 0;
    for(int i = 1; i < max; ++i) {
      if(isPalindrome(""+i) && isPalindrome(Integer.toBinaryString(i))) {
        sum += i;
      }
    }
    return sum;
  }
  
  public static boolean isPalindrome( String s ) {
    for(int i = 0; i < s.length()/2; ++i) {
      if(s.charAt(i) != s.charAt(s.length()-i-1)) {
        return false;
      }
    }
    return true;
  }
}