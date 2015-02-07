import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class GenBankIntro {
  public static void main( String[] args ) {
    String genus = "Austrophasma";
    String date1 = "2003/06/10";
    String date2 = "2004/08/25";
    SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy/M/d");
    ArrayList<String> lines = new ArrayList<>();
    try {
      URL url = new URL("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=nucleotide&term=\"" + genus + "\"[title]&datetype=pdat&mindate=" + date1 + "&maxdate=" + date2);
      BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while((line = r.readLine()) != null) {
        lines.add(line);
      }
      System.out.println(genBankEntries(lines,parserSDF.parse(date1),parserSDF.parse(date2)));
    } catch(Exception e) {}
  }
  
  public static int genBankEntries( ArrayList<String> lines, Date first, Date second ) {
    int count = 0;
    for(int i = 0; i < lines.size(); ++i) {
      if(lines.get(i).contains("<Id>")) {
        ++count;
      }
    }
    return count;
  }
}