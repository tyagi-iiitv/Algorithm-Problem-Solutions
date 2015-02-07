import java.util.*;
public class LocatingRestrictionSites {
  public static void main( String[] args ) {
    String dna = "GCGCCTGTCGAGTGGCTGCGCTGGTCACGAGAGACAGTTTCCGTTAACTAGCGCATTGAGAGCGACTGATCCGAGATCTGGCCAACGTTACGTTCTTCGTTTGAGACCAATTGGGCCGATGATCCTCAGACAACTAAAGTACGGGTTAGACGACATCGGGCGCTCTTTTTCAATGTTGTGAACGGAAATGTTTACCCATTTGACCAAAACCAAATAACATGTTGCGTGGCGGTCCTCTGCAAGAATTTGATTCGACGTAATATGCCAGAAAATCCCTGGCGCATAAGTATTATGAGAGCATTTTGGGACAGCATTTGTTATATTAGTCGTCTGATTCGTAGGGATAGTATAGACTTCATTAGTTCTGCAATACTTTGGTAAGCCCCGCGGATTCCCTATCCTCGTTCTGGTCTCCGTACTCCTCCAGTCTTGCCACGCCAAGGCGATTGTGGGATAATCCGATCGTCCGATATGGAACTAGTTCCGCCTGAATTCGCGGAGTCGGGGTATTAGGACCTCGTGTGCACCCATCCCCGAAATCTAAGGCATAGCCTCGGCCGTGCAGTTTAGACGCGCGCCTAATAGATCGGTGACCGCCAGCACTGTAGATACCGACGTGGCGCGCCGTGACATGGCAACCTGTTAAATTCATCGTAATACAAGACGCATTTACATCAGAAGGGGTTCGGTGCCCACTAGCACCAAGAAGCGAGCTGTTTCAACAGCGTTAGTTCTCCACAGGACGGTTGCGTGACCACAGTTGTATCGCTTCTCTACTTCGCGCACTGAGACTCGCGAATCACAATTTTCATTGCATTCGGCGCCATGATCTGTCGGCTAAAAACTGGTTAACTGTAGGTCACTATATAAATCAAAAGTGGCGTCGGAATGCTGGAGTCGGCTGGCATCTACTGTCCATGGCCTTGTGGTAGACTGGACTTTGACGTCTGCTCGCGCG";
    ArrayList<int[]> out = findReversePalindromes(dna);
    for(int i = 0; i < out.size(); ++i) {
      int[] pair = out.get(i);
      System.out.println(pair[0] + " " + pair[1]);
    }
  }
  
  public static ArrayList<int[]> findReversePalindromes( String dna ) {
    dna = dna.toUpperCase();
    ArrayList<int[]> out = new ArrayList<>();
    for(int length = 4; length <= 12; ++length) {
      for(int i = 0; i <= dna.length() - length; ++i) {
        String window = dna.substring(i,i+length);
        if(isReversePalindrome(window)) {
          int[] add = {(i+1),length};
          out.add(add);
        }
      }
    }
    return out;
  }
  
  public static boolean isReversePalindrome( String dna ) {
    for(int i = 0, j = dna.length()-1; i < dna.length(); ++i, --j) {
      if(dna.charAt(i) != complement(dna.charAt(j))) {
        return false;
      }
    }
    return true;
  }
  
  public static char complement( char c ) {
    if(c == 'A') { return 'T'; }
    if(c == 'T') { return 'A'; }
    if(c == 'C') { return 'G'; }
    if(c == 'G') { return 'C'; }
    return '\0';
  }
}