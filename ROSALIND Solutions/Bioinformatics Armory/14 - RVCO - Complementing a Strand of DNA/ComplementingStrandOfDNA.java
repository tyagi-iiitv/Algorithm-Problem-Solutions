import java.io.*;
import java.util.*;
public class ComplementingStrandOfDNA {
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
    System.out.println(complementingDNA(seqs));
  }
  
  public static int complementingDNA( ArrayList<String> seqs ) {
    int count = 0;
    for(int i = 0; i < seqs.size(); ++i) {
      if(reverseComplement(seqs.get(i)).equals(seqs.get(i))) {
        ++count;
      }
    }
    return count;
  }
  
  public static String reverseComplement( String dna ) {
    String out = "";
    for(int i = dna.length() - 1; i >= 0; --i) {
      char curr = dna.charAt(i);
      if(curr == 'A')
        out += 'T';
      else if(curr == 'T')
        out += 'A';
      else if(curr == 'C')
        out += 'G';
      else if(curr == 'G')
        out += 'C';
      else {
        System.out.println("ERROR: Input is not a DNA Sequence.");
        System.exit(-1);
      }
    }
    return out;
  }
}