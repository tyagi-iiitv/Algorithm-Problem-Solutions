public class DigitFactorials {
  public static void main( String[] args ) {
    System.out.println(digitFactorials());
  }
  
  public static long digitFactorials() {
    long sum = 0;
    for(int i = 10; i < 100000000; ++i) {
      if(isDigitFactorial(i)) {
        sum += i;
      }
    }
    return sum;
  }
  
  public static boolean isDigitFactorial( int n ) {
    String str = ""+n;
    int sum = 0;
    for(int i = 0; i < str.length(); ++i) {
      sum += fact(str.charAt(i)-'0');
    }
    return sum == n;
  }
  
  public static int fact( int n ) {
    if(n < 0) {
      return -1;
    }
    else if(n < 2) {
      return 1;
    }
    int out = 2;
    for(int i = 3; i <= n; ++i) {
      out *= i;
    }
    return out;
  }
}