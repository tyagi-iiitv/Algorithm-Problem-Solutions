import java.util.*;
public class PandigitalProducts {
  public static void main( String[] args ) {
    System.out.println(pandigitalProducts());
  }
  
  public static long pandigitalProducts() {
    ArrayList<String> perms = new ArrayList<>();
    permute("","123456789",perms);
    Collections.sort(perms);
    HashSet<Integer> set = new HashSet<>();
    for(String curr : perms) {
      for(int i = 1; i < 8; ++i) {
        for(int j = i+1; j < 9; ++j) {
          int first = Integer.parseInt(curr.substring(0,i));
          int second = Integer.parseInt(curr.substring(i,j));
          int third = Integer.parseInt(curr.substring(j,curr.length()));
          if(first*second == third) {
            set.add(third);
          }
        }
      }
    }
    long sum = 0;
    for(Integer i : set) {
      sum += i;
    }
    return sum;
  }
  
  public static void permute( String prefix, String str, ArrayList<String> out ) {
    int n = str.length();
    if(n == 0) {
      out.add(prefix);
      return;
    }
    for(int i = 0; i < n; ++i) {
      permute(prefix + str.charAt(i), str.substring(0,i) + str.substring(i+1,n), out);
    }
  }
}