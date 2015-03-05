import java.io.*;
import java.util.*;
public class NamesScores {
  public static void main( String[] args ) {
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String[] names = r.readLine().split("\"\\,\"");
      names[0] = names[0].substring(1);
      names[names.length-1] = names[names.length-1].substring(0,names[names.length-1].length()-1);
      System.out.println(namesScores(names));
    } catch(Exception e) {}
  }
  
  public static long namesScores( String[] names ) {
    Arrays.sort(names);
    long sum = 0;
    for(int i = 0; i < names.length; ++i) {
      sum += (long)(alphaValue(names[i]) * (i+1));
    }
    return sum;
  }
  
  public static int alphaValue( String name ) {
    int val = 0;
    for(int i = 0; i < name.length(); ++i) {
      val += (name.charAt(i) - 'A' + 1);
    }
    return val;
  }
}