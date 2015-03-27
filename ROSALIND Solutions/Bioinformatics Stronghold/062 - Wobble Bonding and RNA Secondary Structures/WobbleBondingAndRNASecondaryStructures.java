import java.util.*;
import java.math.*;
public class WobbleBondingAndRNASecondaryStructures {
  public static void main( String[] args ) {
    String rna = "CCGUAUGGUAAGCAGGGCUUGCUCAACCACGCUAGGUGGAUUCCAGGGAUCUUCGAUAGCUGGUUUCUCCGGUGAUCUGAUUUCAUCACAGGGUGCUACCAACCGACCCAAACGCACACCUGUGUGUGGAGGGUCCCCAAUAAGGCCAAAUUAGACUACCCUAGUAAAACAGUAAGG";
    System.out.println(noncrossing(rna,new HashMap<String,BigInteger>()));
  }
  
  // used hashmap to save time by preventing recomputing previously computed values
  public static BigInteger noncrossing( String rna, HashMap<String,BigInteger> map ) {
    if(rna.length() <= 1) {
      return BigInteger.ONE;
    }
    if(map.containsKey(rna)) {
      return map.get(rna);
    }
    BigInteger sum = BigInteger.ZERO;
    for(int i = 4; i < rna.length(); ++i) {
      if(matchings(rna.charAt(0),rna.charAt(i))) {
        sum = sum.add(noncrossing(rna.substring(1,i),map).multiply(noncrossing(rna.substring(i+1),map)));
      }
    }
    sum = sum.add(noncrossing(rna.substring(1),map));
    map.put(rna,sum);
    return sum;
  }
  
  public static boolean matchings( char a, char b ) {
    if(a == 'A' && b == 'U') {
      return true;
    }
    if(a == 'U' && b == 'A') {
      return true;
    }
    if(a == 'G' && b == 'C') {
      return true;
    }
    if(a == 'C' && b == 'G') {
      return true;
    }
    if(a == 'U' && b == 'G') {
      return true;
    }
    if(a == 'G' && b == 'U') {
      return true;
    }
    return false;
  }
}