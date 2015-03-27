import java.util.*;
import java.io.*;
public class FailureArray {
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
    int[] out = failureArray(seqs.get(0));
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static int[] failureArray( String s ) {
    int[] P = new int[s.length()];
    int k = 0;
    for(int i = 2; i <= s.length(); ++i) {
        while(k > 0 && s.charAt(k) != s.charAt(i-1)) {
            k = P[k-1];
        }
        if(s.charAt(k) == s.charAt(i-1)) {
            ++k;
        }
        P[i-1] = k;
    }
    return P;
  }
}