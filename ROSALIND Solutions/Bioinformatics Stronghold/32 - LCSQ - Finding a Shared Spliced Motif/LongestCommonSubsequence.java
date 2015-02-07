import java.io.*;
import java.util.*;
public class LongestCommonSubsequence {
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
    System.out.println(longestCommonSubsequence(seqs.get(0),seqs.get(1)));
  }
  
  public static String longestCommonSubsequence( String a, String b ) {
    int[][] L = new int[a.length()+1][b.length()+1];
    for (int i = 0; i < a.length(); i++)
      for (int j = 0; j < b.length(); j++)
        if (a.charAt(i) == b.charAt(j))
          L[i+1][j+1] = L[i][j] + 1;
        else
          L[i+1][j+1] = Math.max(L[i+1][j], L[i][j+1]);
    StringBuffer sb = new StringBuffer();
    for (int x = a.length(), y = b.length(); x != 0 && y != 0;) {
      if (L[x][y] == L[x-1][y])
        x--;
      else if (L[x][y] == L[x][y-1])
        y--;
      else {
        assert a.charAt(x-1) == b.charAt(y-1);
        sb.append(a.charAt(x-1));
        x--;
        y--;
      }
    }
    return sb.reverse().toString();
  }
}