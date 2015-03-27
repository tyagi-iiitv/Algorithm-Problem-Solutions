import java.util.*;
public class EnumeratingOrientedGeneOrderings {
  public static void main( String[] args ) {
    int n = 6;
    ArrayList<String> genes = permuteGenes(n);
    System.out.println(genes.size());
    for(int i = 0; i < genes.size(); ++i) {
      System.out.println(genes.get(i));
    }
  }
  
  public static ArrayList<String> permuteGenes( int n ) {
    ArrayList<String> l = new ArrayList<String>();
    String alpha = "";
    for(int i = 1; i <= n; ++i) {
      alpha = alpha + " " + i;
    }
    alpha = alpha.substring(1,alpha.length());
    permuteNoRepeats(alpha,n,"",l);
    return l;
  }
  
  public static void permuteNoRepeats( String alpha, int length, String prefix, ArrayList<String> list ) {
    if(length == 0) {
      list.add(prefix.substring(1,prefix.length()));
      return;
    }
    String[] parts = alpha.split(" ");
    for(int i = 0; i < parts.length; ++i) {
      String out = "";
      for(int j = 0; j < parts.length; ++j) {
        if(j != i) {
          out += parts[j] + " ";
        }
      }
      if(out.length() > 0) {
        out = out.substring(0,out.length()-1);
      }
      permuteNoRepeats(out,length-1,prefix + " " + parts[i],list);
      permuteNoRepeats(out,length-1,prefix + " -" + parts[i],list);
    }
  }
  
  public static void permute( int n, int length, String prefix, ArrayList<String> list ) {
    if(length == 0) {
      list.add(prefix);
      return;
    }
    for(int i = 1; i <= n; ++i) {
      permute(n,length-1,prefix + " " + i,list);
      permute(n,length-1,prefix + " " + (-1*i),list);
    }
  }
}