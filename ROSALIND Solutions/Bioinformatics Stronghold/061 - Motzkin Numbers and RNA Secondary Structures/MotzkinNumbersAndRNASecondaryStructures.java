import java.util.*;
public class MotzkinNumbersAndRNASecondaryStructures {
  public static void main( String[] args ) {
    String rna = "AGUAGCUGUAUUCAUCCCAAGUGCGUCAUGUACCUAAAAGUCUAUACUUGUGAAGUGAGCAUUGUGACAUCUGUUCCAGUCGCGGGAGUCGACACCGAUCAUUGUAUGCUCAGUCUACCAAACAGCACUGGAUCUCUGAUAGCCGAUAAGGGCAUCAGGAUCUCAGACACUUUAACCACUAGAGUCAUGGACGGGAGGACGGUAGACUAGUGAGCAAAUAAGUCUUCGGGUCACCACAUGAACAGCGCUCUUUGUAUUA";
    System.out.println(noncrossing(rna,new HashMap<String,Long>()));
  }
  
  // used hashmap to save time by preventing recomputing previously computed values
  public static long noncrossing( String rna, HashMap<String,Long> map ) {
    if(rna.length() <= 1) {
      return 1;
    }
    if(map.containsKey(rna)) {
      return map.get(rna);
    }
    long sum = 0;
    for(int i = 1; i < rna.length(); ++i) {
      if(rna.charAt(0) == matchings(rna.charAt(i))) {
        sum += noncrossing(rna.substring(1,i),map)*noncrossing(rna.substring(i+1),map);
      }
    }
    sum += noncrossing(rna.substring(1),map);
    sum = sum % 1000000;
    map.put(rna,sum);
    return sum;
  }
  
  public static char matchings( char c ) {
    switch(c) {
      case 'A': return 'U';
      case 'U': return 'A';
      case 'C': return 'G';
      case 'G': return 'C';
      default: return '\0';
    }
  }
}