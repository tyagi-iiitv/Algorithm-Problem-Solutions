public class NumberLetterCounts {
  public static String[] tensNames = {""," ten"," twenty"," thirty"," forty"," fifty"," sixty"," seventy"," eighty"," ninety"};
  public static String[] numNames = {""," one"," two"," three"," four"," five"," six"," seven"," eight"," nine"," ten"," eleven"," twelve"," thirteen"," fourteen"," fifteen"," sixteen"," seventeen"," eighteen"," nineteen"};
  
  public static void main( String[] args ) {
    int min = 1;
    int max = 1000;
    System.out.println(numberLetterCounts(min,max));
  }
  
  public static long numberLetterCounts( int min, int max ) {
    long sum = 0;
    for(int i = min; i <= max; ++i) {
      String word = numberToWord(i);
      sum += word.replaceAll("\\s","").length();
    }
    return sum;
  }
  
  // supports 0 to 1000
  public static String numberToWord( int n ) {
    if(n == 0) {
      return "zero";
    }
    if(n == 1000) {
      return "one thousand";
    }
    String orig = ""+n;
    String temp;
    if(n % 100 < 20) {
      temp = numNames[n % 100];
      n /= 100;
    }
    else {
      temp = numNames[n % 10];
      n /= 10;
      temp = tensNames[n % 10] + temp;
      n /= 10;
    }
    if(n == 0) {
      return temp;
    }
    else {
      if(orig.charAt(1) == '0' && orig.charAt(2) == '0') {
        return numNames[n] + " hundred";
      }
      else {
        return numNames[n] + " hundred and" + temp;
      }
    }
  }
}