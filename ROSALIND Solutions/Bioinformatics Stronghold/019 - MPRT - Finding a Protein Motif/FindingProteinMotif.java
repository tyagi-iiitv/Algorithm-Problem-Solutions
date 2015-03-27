import java.util.*;
import java.io.*;
public class FindingProteinMotif {
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
    
    ArrayList<String> out = findProteinMotif(ids,seqs);
    for(int i = 0; i < out.size(); ++i) {
      String[] parts = out.get(i).split(""+'\n');
      System.out.println(parts[0]);
      System.out.println(parts[1]);
    }
  }
  
  public static ArrayList<String> findProteinMotif( ArrayList<String> ids, ArrayList<String> seqs ) {
    ArrayList<String> out = new ArrayList<>();
    for(int i = 0; i < ids.size(); ++i) {
      String id = ids.get(i);
      String[] idSplit = id.split("\\|");
      String seq = seqs.get(i);
      ArrayList<Integer> inds = findNglyco(seq);
      if(inds.size() != 0) {
        String indStr = "";
        for(int ind = 0; ind < inds.size(); ++ind) {
          indStr += inds.get(ind) + " ";
        }
        indStr = indStr.trim();
        String temp = idSplit[1] + '\n' + indStr;
        out.add(temp);
      }
    }
    return out;
  }
  
  public static ArrayList<Integer> findNglyco( String seq ) {
    ArrayList<Integer> out = new ArrayList<>();
    for(int i = 0; i <= seq.length()-4; ++i) {
      String window = seq.substring(i,i+4);
      if(window.charAt(0) == 'N' &&
         window.charAt(1) != 'P' &&
        (window.charAt(2) == 'S' || window.charAt(2) == 'T') &&
         window.charAt(3) != 'P') {
        out.add(i);
      }
    }
    return out;
  }
}