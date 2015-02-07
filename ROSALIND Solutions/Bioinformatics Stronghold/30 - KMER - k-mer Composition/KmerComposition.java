import java.util.*;
import java.io.*;
public class KmerComposition {
  public static void main( String[] args ) {
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> seqs = new ArrayList<>();
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String line;
      String currSeq = "";
      while((line = r.readLine()) != null || true) {
        if(line == null) {
          seqs.add(currSeq);
          break;
        }
        if(line.charAt(0) == '>') {
          ids.add(line.substring(1,line.length()));
          if(currSeq.length() != 0) {
            seqs.add(currSeq);
            currSeq = "";
          }
        }
        else {
          currSeq += line;
        }
      }
    } catch(Exception e) {}
    String s = seqs.get(0);
    int k = 4;
    int[] out = kmerComposition(s,k);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static int[] kmerComposition( String s, int k ) {
    int[] counts = new int[(int)Math.pow(4,k)];
    for(int i = 0; i <= s.length()-k; ++i) {
      ++counts[patternToNumber(s.substring(i,i+k))];
    }
    return counts;
  }
  
  public static int patternToNumber( String pattern) {
    if(pattern.length() == 0) {
      return 0;
    }
    char symbol = pattern.charAt(pattern.length()-1);
    return 4*patternToNumber(pattern.substring(0,pattern.length()-1)) + symbolToNumber(symbol);
  }
  
  public static int symbolToNumber( char symbol ) {
    if(symbol == 'A') {
      return 0;
    }
    else if(symbol == 'C') {
      return 1;
    }
    else if(symbol == 'G') {
      return 2;
    }
    else if(symbol == 'T') {
      return 3;
    }
    else {
      return -1;
    }
  }
}