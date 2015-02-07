import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class DataFormats {
  public static void main( String[] args ) {
    String[] ids = {"JX462669","JX428803","BT149870","JQ712977","JN573266","NM_000641","JX317645","NM_001265803"};
	ArrayList<String> out = shortestFastaFromIds(ids);
	for(int i = 0; i < out.size(); ++i) {
	  System.out.println(out.get(i));
	}
  }
  
  public static ArrayList<String> shortestFastaFromIds( String[] ids ) {
    ArrayList<String> best = new ArrayList<>();
	int bestLength = Integer.MAX_VALUE;
	for(int i = 0; i < ids.length; ++i) {
	  try {
	    URL url = new URL("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nucleotide&id=" + ids[i] + "&retmode=text&rettype=fasta");
		BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		ArrayList<String> curr = new ArrayList<>();
		curr.add(r.readLine());
		int currLength = 0;
		while((line = r.readLine()) != null) {
		  curr.add(line);
		  currLength += line.length();
		}
		if(currLength < bestLength) {
		  bestLength = currLength;
		  best = curr;
		}
	  } catch(Exception e) {}
	}
	return best;
  }
}