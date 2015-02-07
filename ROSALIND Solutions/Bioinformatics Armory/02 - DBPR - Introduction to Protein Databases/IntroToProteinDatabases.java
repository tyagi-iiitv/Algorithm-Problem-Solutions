import java.net.*;
import java.io.*;
import java.util.*;
public class IntroToProteinDatabases {
  public static void main( String[] args ) {
    String uniprot = "Q9LDH1";
    ArrayList<String> lines = new ArrayList<>();
    try {
      URL url = new URL("http://www.uniprot.org/uniprot/" + uniprot + ".txt");
      BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while((line = r.readLine()) != null) {
        lines.add(line);
      }
    } catch(Exception e) {}
    ArrayList<String> out = listBiologicalProcesses(lines);
    for(int i = 0; i < out.size(); ++i) {
      System.out.println(out.get(i));
    }
  }
  public static ArrayList<String> listBiologicalProcesses( ArrayList<String> txt ) {
    ArrayList<String> GOlines = new ArrayList<>();
    for(int i = 0; i < txt.size(); ++i) {
      String line = txt.get(i);
      if(line.contains("GO; GO:")) {
        String[] split = line.split(";");
        String part = split[2];
        if(part.charAt(1) == 'P') {
          GOlines.add(part.substring(3));
        }
      }
    }
    return GOlines;
  }
}