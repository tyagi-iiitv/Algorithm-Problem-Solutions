import java.io.*;
import java.util.*;
public class ConsensusProfile {
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
    
    System.out.println(matrixToProfile(profileMatrix(seqs)));
  }
  
  public static String matrixToProfile( int[][] matrix ) {
    String out = "";
    for(int i = 0; i < matrix[0].length; ++i) {
      int best = 0;
      if(matrix[1][i] > matrix[best][i]) {
        best = 1;
      }
      if(matrix[2][i] > matrix[best][i]) {
        best = 2;
      }
      if(matrix[3][i] > matrix[best][i]) {
        best = 3;
      }
      out += numToNucleo(best);
    }
    return out;
  }
  
  public static int[][] profileMatrix( ArrayList<String> seqs ) {
    int[][] out = new int[4][seqs.get(0).length()];
    for(int seq = 0; seq < seqs.size(); ++seq) {
      String curr = seqs.get(seq);
      for(int i = 0; i < curr.length(); ++i) {
        char c = curr.charAt(i);
        ++out[nucleoToNum(c)][i];
      }
    }
    return out;
  }
  
  public static char numToNucleo( int n ) {
    if(n == 0) { return 'A'; }
    if(n == 1) { return 'C'; }
    if(n == 2) { return 'G'; }
    if(n == 3) { return 'T'; }
    return '\0';
  }
  
  public static int nucleoToNum( char c ) {
    if(c == 'A') { return 0; }
    if(c == 'C') { return 1; }
    if(c == 'G') { return 2; }
    if(c == 'T') { return 3; }
    return -1;
  }
}