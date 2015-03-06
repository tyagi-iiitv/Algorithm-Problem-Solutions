import java.util.*;
public class LexicographicPermutations {
  public static void main( String[] args ) {
    String s = "0123456789";
    int n = 1000000;
    System.out.println(lexicographicPermutations(s,n));
  }
  
  public static String lexicographicPermutations( String s, int n ) {
    ArrayList<String> permutations = permutation(s);
    return permutations.get(n-1);
  }
  
  public static ArrayList<String> permutation( String str ) {
    ArrayList<String> perms = new ArrayList<>();
    permute("",str,perms);
    Collections.sort(perms);
    return perms;
  }
  
  public static void permute( String prefix, String str, ArrayList<String> perms ) {
    if(str.length() == 0) {
      perms.add(prefix);
    }
    else {
      for(int i = 0; i < str.length(); ++i) {
        permute(prefix + str.charAt(i), str.substring(0,i) + str.substring(i+1,str.length()), perms);
      }
    }
  }
}