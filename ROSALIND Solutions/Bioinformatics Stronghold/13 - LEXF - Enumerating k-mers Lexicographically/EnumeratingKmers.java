import java.util.*;
public class EnumeratingKmers {
  public static void main( String[] args ) {
    String alphabet = "Z L K P A Q B";
    int n = 3;
    ArrayList<String> out = enumerateKmers(alphabet,n);
    for(int i = 0; i < out.size(); ++i) {
      System.out.println(out.get(i));
    }
  }
  
  public static ArrayList<String> enumerateKmers( String alphabet, int n ) {
    ArrayList<String> l = new ArrayList<>();
    permute(alphabet,n,"",l);
    return l;
  }
  
  public static void permute( String alphabet, int length, String prefix, ArrayList<String> list ) {
    if(length == 0) {
      list.add(prefix);
      return;
    }
    String[] parts = alphabet.split(" ");
    for(int i = 0; i < parts.length; ++i) {
      permute(alphabet,length-1,prefix + parts[i],list);
    }
  }
}