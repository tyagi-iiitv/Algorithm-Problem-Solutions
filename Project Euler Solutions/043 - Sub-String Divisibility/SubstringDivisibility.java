import java.util.*;
public class SubstringDivisibility {
  public static void main( String[] args ) {
    System.out.println(substringDivisibility());
  }
  
  public static long substringDivisibility() {
    ArrayList<String> perms = new ArrayList<>();
    permute("","0123456789",perms);
    long sum = 0;
    for(String p : perms) {
      if(isValid(p)) {
        sum += Long.parseLong(p);
      }
    }
    return sum;
  }
  
  public static boolean isValid( String p ) {
    if(Long.parseLong(p.substring(1,4)) % 2 != 0) {
      return false;
    }
    if(Long.parseLong(p.substring(2,5)) % 3 != 0) {
      return false;
    }
    if(Long.parseLong(p.substring(3,6)) % 5 != 0) {
      return false;
    }
    if(Long.parseLong(p.substring(4,7)) % 7 != 0) {
      return false;
    }
    if(Long.parseLong(p.substring(5,8)) % 11 != 0) {
      return false;
    }
    if(Long.parseLong(p.substring(6,9)) % 13 != 0) {
      return false;
    }
    if(Long.parseLong(p.substring(7,10)) % 17 != 0) {
      return false;
    }
    return true;
  }
  
  public static void permute( String prefix, String str, ArrayList<String> out ) {
    int n = str.length();
    if(n == 0) {
      if(prefix.charAt(0) != '0') {
        out.add(prefix);
      }
      return;
    }
    for(int i = 0; i < n; ++i) {
      permute(prefix + str.charAt(i), str.substring(0,i) + str.substring(i+1,n), out);
    }
  }
}