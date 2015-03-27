import java.io.*;
import java.util.*;
public class OverlapGraphs {
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
    
    ArrayList<String> out = O(3,ids,seqs);
    for(int i = 0; i < out.size(); ++i) {
      System.out.println(out.get(i));
    }
  }
  
  public static ArrayList<String> O( int k, ArrayList<String> ids, ArrayList<String> seqs ) {
    ArrayList<String> out = new ArrayList<>();
    for(int i = 0; i < ids.size(); ++i) {
      for(int j = 0; j < ids.size(); ++j) {
        if(i != j) {
          String first = seqs.get(i);
          String second = seqs.get(j);
          if(first.substring(first.length()-k,first.length()).equals(second.substring(0,k))) {
            out.add(ids.get(i) + " " + ids.get(j));
          }
        }
      }
    }
    return out;
  }
}