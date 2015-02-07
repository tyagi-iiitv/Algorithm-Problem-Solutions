import java.util.*;
public class ShortestCommonSupersequence {
  public static void main( String[] args ) {
    String s = "TAGTGCGAGCATCGTACTGTGGGTGGAGCATACGAGTCAGTCTCGGTACCCAGCGGGAGCGATAATCTTCCTTTAAGGTAGACT";
    String t = "TCCCGCCGGACGATTGCTTGCCTGGACCTACAGTCTTCGCCAGAAGCGCGGAACGTATGTAGTAAATACGGCCATCACAGCTAGCAACTGT";
    System.out.println(shortestCommonSupersequence(s,t));
  }
  
  public static String shortestCommonSupersequence( String s, String t ) {
    // perform global alignment without mismatches allowed and then reconstruct supersequence
    String[] alignment = globalAlignment(s,t); // special global alignment, don't allow mismatches but allow gaps
    String wip = "";
    for(int i = 0; i < alignment[0].length(); ++i) {
      char a = alignment[0].charAt(i);
      char b = alignment[1].charAt(i);
      if(a == '-') {
        wip += b;
      }
      else {
        wip += a;
      }
    }
    return wip;
  }
  
  public static String[] globalAlignment( String s, String t ) {
    int[][] S = new int[s.length()+1][t.length()+1];
    int[][] opt = new int[s.length()+1][t.length()+1]; // 1 = right, 2 = down, 3 = diag
    S[0][0] = 0;
    for(int i = 1; i <= s.length(); ++i) {
      S[i][0] = S[i-1][0]; // 0 indel cost
      opt[i][0] = 1;
    }
    for(int j = 1; j <= t.length(); ++j) {
      S[0][j] = S[0][j-1]; // 0 indel cost
      opt[0][j] = 2;
    }
    for(int i = 1; i <= s.length(); ++i) {
      for(int j = 1; j <= t.length(); ++j) {
        int opt1 = S[i][j-1]; // 0 indel cost
        int opt2 = S[i-1][j]; // 0 indel cost
        int opt3 = S[i-1][j-1]; // diag
        if(s.charAt(i-1) == t.charAt(j-1)) {
          ++opt3;
        }
        else {
          opt3 -= 10; // don't allow mismatches
        }
        S[i][j] = opt1;
        opt[i][j] = 1;
        if(opt2 > S[i][j]) {
          S[i][j] = opt2;
          opt[i][j] = 2;
        }
        if(opt3 > S[i][j]) {
          S[i][j] = opt3;
          opt[i][j] = 3;
        }
      }
    }
    String[] out = new String[2];
    out[0] = "";
    out[1] = "";
    int i = s.length();
    int j = t.length();
    while(i > 0 && j > 0) {
        if(opt[i][j] == 1) { // right
            out[0] = '-' + out[0];
            out[1] = t.charAt(j-- - 1) + out[1];
        }
        else if(opt[i][j] == 2) { // down
            out[0] = s.charAt(i-- - 1) + out[0];
            out[1] = '-' + out[1];
        }
        else if(opt[i][j] == 3) { // diag
            out[0] = s.charAt(i-- - 1) + out[0];
            out[1] = t.charAt(j-- - 1) + out[1];
        }
    }
    if(i > 0) {
        out[0] = s.substring(0,i) + out[0];
        String add = "";
        for(int x = 0; x < i; ++x) {
            add += '-';
        }
        out[1] = add + out[1];
    }
    if(j > 0) {
        out[1] = t.substring(0,j) + out[1];
        String add = "";
        for(int x = 0; x < j; ++x) {
            add += '-';
        }
        out[0] = add + out[0];
    }
    return out;
  }
}