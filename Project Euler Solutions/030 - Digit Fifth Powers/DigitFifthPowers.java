public class DigitFifthPowers {
  public static void main( String[] args ) {
    int p = 5;
    System.out.println(digitPowers(p));
  }
  
  public static long digitPowers( int p ) {
    long sum = 0;
    for(int i = 10; i < 1000000; ++i) {
      if(i == sumOfDigitPowers(i,p)) {
        sum += i;
      }
    }
    return sum;
  }
  
  public static long sumOfDigitPowers( int n, int p ) {
    String s = ""+n;
    long sum = 0;
    for(int i = 0; i < s.length(); ++i) {
      sum += (long)(Math.pow(s.charAt(i)-'0',p));
    }
    return sum;
  }
}