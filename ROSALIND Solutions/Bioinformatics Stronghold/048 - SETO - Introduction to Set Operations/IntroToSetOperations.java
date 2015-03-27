import java.io.*;
public class IntroToSetOperations {
  public static void main( String[] args ) {
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      int n = Integer.parseInt(r.readLine());
      String l2 = r.readLine();
      String l3 = r.readLine();
      String[] aParts = l2.substring(1,l2.length()-1).split("\\, ");
      int[] a = new int[aParts.length];
      for(int i = 0; i < a.length; ++i) {
        a[i] = Integer.parseInt(aParts[i]);
      }
      String[] bParts = l3.substring(1,l3.length()-1).split("\\, ");
      int[] b = new int[bParts.length];
      for(int i = 0; i < b.length; ++i) {
        b[i] = Integer.parseInt(bParts[i]);
      }
      String[] out = setOperations(n,a,b);
      for(int i = 0; i < out.length; ++i) {
        System.out.println(out[i]);
      }
    } catch(Exception e) {}
  }
  
  public static String[] setOperations( int n, int[] a, int[] b ) {
    boolean[] aBool = new boolean[n+1];
    for(int i = 0; i < a.length; ++i) {
      aBool[a[i]] = true;
    }
    boolean[] bBool = new boolean[n+1];
    for(int i = 0; i < b.length; ++i) {
      bBool[b[i]] = true;
    }
    String[] out = {"{","{","{","{","{","{"};
    
    for(int i = 1; i <= n; ++i) {
      if(aBool[i] || bBool[i]) {
        out[0] += i + ", ";
      }
      if(aBool[i] && bBool[i]) {
        out[1] += i + ", ";
      }
      if(aBool[i] && !bBool[i]) {
        out[2] += i + ", ";
      }
      if(!aBool[i] && bBool[i]) {
        out[3] += i + ", ";
      }
      if(!aBool[i]) {
        out[4] += i + ", ";
      }
      if(!bBool[i]) {
        out[5] += i + ", ";
      }
    }
    out[0] = out[0].substring(0,out[0].length()-2) + "}";
    out[1] = out[1].substring(0,out[1].length()-2) + "}";
    out[2] = out[2].substring(0,out[2].length()-2) + "}";
    out[3] = out[3].substring(0,out[3].length()-2) + "}";
    out[4] = out[4].substring(0,out[4].length()-2) + "}";
    out[5] = out[5].substring(0,out[5].length()-2) + "}";
    
    return out;
  }
}