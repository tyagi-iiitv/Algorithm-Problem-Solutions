import java.io.*;
import java.util.*;
public class LongestCommonSubstring {
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
    
    System.out.println(LCS(seqs));
  }
  
  public static String LCS( ArrayList<String> seqs ) {
    String best = "A";
    String first = seqs.get(0);
    for(int k = 2; k <= first.length(); ++k) {
      boolean found = false;
      for(int i = 0; i <= first.length()-k; ++i) {
        String window = first.substring(i,i+k);
        boolean valid = true;
        for(int j = 1; j < seqs.size(); ++j) {
          if(!seqs.get(j).contains(window)) {
            valid = false;
            break;
          }
        }
        if(valid) {
          best = window;
          found = true;
          break;
        }
      }
      if(!found) {
        break;
      }
    }
    return best;
  }
}