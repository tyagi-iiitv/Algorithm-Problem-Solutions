import java.io.*;
import java.util.*;
public class CreatingDistanceMatrix {
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
    double[][] out = distanceMatrix(seqs);
    for(int i = 0; i < out.length; ++i) {
      for(int j = 0; j < out[i].length; ++j) {
        System.out.print(out[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public static double[][] distanceMatrix( ArrayList<String> seqs ) {
    int n = seqs.size();
    double[][] m = new double[n][n];
    for(int i = 0; i < n-1; ++i) {
      for(int j = i; j < n; ++j) {
        double d = pDistance(seqs.get(i),seqs.get(j));
        m[i][j] = d;
        m[j][i] = d;
      }
    }
    return m;
  }
  
  public static double pDistance( String s, String t ) {
    double count = 0;
    for(int i = 0; i < s.length(); ++i) {
      if(s.charAt(i) != t.charAt(i)) {
        ++count;
      }
    }
    count = count / s.length();
    return count;
  }
}