import java.util.*;
import java.math.*;
public class CatalanNumbers {
  public static void main( String[] args ) {
    String s = "CUUCCGUGCACGCUUCCGGAUAAUUCGACGCGACGUCGACGGGCAGCUCGCGCAUGAAUUGUUAGCCGGCCAUGAUAAUUAUAGCACUAUAUAUAUAACCGGCGUGGUGCUAACGCUGCAGCAAGAUAUCCCGAAUUGCUAGGAUCGCUUUGAUCAACGGGGCCGCGGGGGCCAUGGCGCCCUUAACCAAAUAGCUUUAAUUGUAGCCCGACCGCGGUAGCUCGGCGUACCAUGCAUGGCAUUA";
    System.out.println(noncrossingPerfectMatchings(s));
  }
  
  public static int noncrossingPerfectMatchings( String seq ) {
    HashMap<String,BigInteger> noncrossingBondings = new HashMap<>();
    return countNoncrossing(seq,noncrossingBondings).mod(new BigInteger("1000000")).intValue();
  }
  
  public static BigInteger countNoncrossing( String seq, HashMap<String,BigInteger> noncrossingBondings ) {
    if(seq.length() <= 2) {
      return BigInteger.ONE;
    }
    else if(noncrossingBondings.containsKey(seq)) {
      return noncrossingBondings.get(seq);
    }
    ArrayList<Integer> splits = new ArrayList<>();
    for(int i = 1; i < seq.length(); i += 2) {
      if(seq.charAt(0) == bonding(seq.charAt(i)) && checkSubinterval(seq.substring(1,i))) {
        splits.add(i);
      }
    }
    BigInteger sum = BigInteger.ZERO;
    for(int ind = 0; ind < splits.size(); ++ind) {
      int i = splits.get(ind);
      sum = sum.add(countNoncrossing(seq.substring(1,i),noncrossingBondings).multiply(countNoncrossing(seq.substring(i+1,seq.length()),noncrossingBondings)));
    }
    noncrossingBondings.put(seq,sum);
    return sum;
  }
  
  public static boolean checkSubinterval( String s ) {
    int[] counts = new int[4]; // A = 0; C = 1; G = 2; U = 3
    for(int i = 0; i < s.length(); ++i) {
      switch(s.charAt(i)) {
        case 'A': ++counts[0]; break;
        case 'C': ++counts[1]; break;
        case 'G': ++counts[2]; break;
        case 'U': ++counts[3]; break;
      }
    }
    return counts[0] == counts[3] && counts[1] == counts[2];
  }
  
  public static char bonding( char n ) {
    switch(n) {
      case 'A': return 'U';
      case 'U': return 'A';
      case 'C': return 'G';
      case 'G': return 'C';
      default: return '\0';
    }
  }
}