import java.io.*;
import java.util.*;
public class GCContent {
  public static void main( String[] args ) {
    String best = null;
    double bestGC = -1;
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
    
    for(int i = 0; i < ids.size(); ++i) {
      double gc = gcContent(seqs.get(i));
      if(gc > bestGC) {
        bestGC = gc;
        best = ids.get(i);
      }
    }
    System.out.println(best);
    System.out.println(bestGC);
  }
  
  public static double gcContent( String dna ) {
    dna = dna.toUpperCase();
    double gc = 0.0;
    for(int i = 0; i < dna.length(); ++i) {
      char c = dna.charAt(i);
      if(c == 'G' || c == 'C') { ++gc; };
    }
    return (gc/dna.length())*100;
  }
}