import java.io.*;
import java.util.*;
public class FastqFormatIntro {
  public static void main( String[] args ) {
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> seqs = new ArrayList<>();
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String line;
      String currSeq = "";
	  boolean seq = false;
      while((line = r.readLine()) != null) {
        if(line.charAt(0) == '@') {
          System.out.println(">" + line.substring(1,line.length()));
		  System.out.println(r.readLine());
		  r.readLine();
		  r.readLine();
        }
      }
    } catch(Exception e) {}
  }
}