import java.util.*;
public class DigitCancellingFractions {
  public static void main( String[] args ) {
    System.out.println(digitCancellingFractions());
  }
  
  public static int digitCancellingFractions() {
    int[][] out = new int[4][2];
    int add = 0;
    for(int num = 11; num < 99; ++num) {
      if(num%10 == 0) {continue;}
      for(int den = num+1; den < 100; ++den) {
        if(den%10 == 0 || (num%10 == num/10 && den%10 == den/10)) {
          continue;
        }
        if(den%10 != num/10 && den/10 != num%10) {
          continue;
        }
        if(equalFractions(num,den,num/10,den%10) || equalFractions(num,den,num%10,den/10)) {
          out[add][0] = num;
          out[add++][1] = den;
        }
      }
    }
    int num = 1;
    int den = 1;
    for(int[] pair : out) {
      num *= pair[0];
      den *= pair[1];
    }
    return den / gcd(num,den);
  }
  
  public static boolean equalFractions( int num1, int den1, int num2, int den2 ) {
    if(den2 == 0) {
      return false;
    }
    int gcd1 = gcd(num1,den1);
    int gcd2 = gcd(num2,den2);
    return (num1/gcd1 == num2/gcd2) && (den1/gcd1 == den2/gcd2);
  }
  
  public static int gcd( int a, int b ) {
    if(b == 0) {
      return Math.abs(a);
    }
    return gcd(b,a%b);
  }
}