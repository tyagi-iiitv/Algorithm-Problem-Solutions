import java.io.*;
import java.util.*;
public class ErrorCorrectionInReads {
  public static void main( String[] args ) {
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
    ArrayList<String> out = errorCorrection(seqs);
    for(int i = 0; i < out.size(); ++i) {
      System.out.println(out.get(i));
    }
  }
  
  public static ArrayList<String> errorCorrection( ArrayList<String> seqs ) {
    ArrayList<String> good = new ArrayList<>();
    for(int i = 0; i < seqs.size() - 1; ++i) {
      String a = seqs.get(i);
      boolean found = false;
      for(int j = i+1; j < seqs.size(); ++j) {
        String b = seqs.get(j);
        if(a.equals(b) || a.equals(reverseComplement(b))) {
          seqs.remove(j--);
          found = true;
        }
      }
      if(found) {
        good.add(seqs.remove(i--));
      }
    }
    ArrayList<String> out = new ArrayList<>();
    for(int i = 0; i < seqs.size(); ++i) {
      String a = seqs.get(i);
      for(int j = 0; j < good.size(); ++j) {
        String b = good.get(j);
        if(hamming(a,b) == 1) {
          out.add(a + "->" + b);
          break;
        }
        String r = reverseComplement(b);
        if(hamming(a,r) == 1) {
          out.add(a + "->" + r);
        }
      }
    }
    return out;
  }
  
  public static int hamming( String s, String t ) {
    int d = 0;
    for(int i = 0; i < s.length(); ++i) {
      if(s.charAt(i) != t.charAt(i)) {
        ++d;
      }
    }
    return d;
  }
  
  public static String reverseComplement( String in ) {
    in = in.toUpperCase();
    String out = "";
    for(int i = in.length()-1; i >= 0; --i) {
      char c = in.charAt(i);
           if(c == 'A') { out += 'T'; }
      else if(c == 'T') { out += 'A'; }
      else if(c == 'C') { out += 'G'; }
      else if(c == 'G') { out += 'C'; }
    }
    return out;
  }
}