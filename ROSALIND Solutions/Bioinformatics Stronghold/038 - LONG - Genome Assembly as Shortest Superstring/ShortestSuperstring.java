import java.io.*;
import java.util.*;
public class ShortestSuperstring {
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
    System.out.println(shortestSuperstring(seqs));
  }
  
  public static String shortestSuperstring( ArrayList<String> seqs ) {
    return findOverlaps(seqs,"");
  }
  
  public static String findOverlaps( ArrayList<String> arr, String acc ) {
    if(arr.isEmpty()) {
      return acc;
    }
    else if(acc.length() == 0) {
      String temp = arr.remove(0);
      return findOverlaps(arr,temp);
    }
    else {
      for(int i = 0; i < arr.size(); ++i) {
        String a = arr.get(i);
        int l = a.length();
        for(int p = 0; p < l/2; ++p) {
          int q = l - p;
          if(acc.startsWith(a.substring(p,l))) {
            arr.remove(i);
            return findOverlaps(arr,a.substring(0,p)+acc);
          }
          if(acc.endsWith(a.substring(0,q))) {
            arr.remove(i);
            return findOverlaps(arr,acc+a.substring(q,l));
          }
        }
      }
    }
    return null;
  }
}