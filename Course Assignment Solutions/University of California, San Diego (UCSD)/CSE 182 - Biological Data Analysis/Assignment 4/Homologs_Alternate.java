/**
  * Alexander Niema Moshiri; 11 November 2014; CSE 182 Assignment 4
  *
  * NOTE: Instead of an int[][] M, I'm just using a simple method that
  *       returns 2 for a match and -1 for a mismatch. For a more specific
  *       scoring matrix M, you can modify the M(a1,a2) method of this file
  *
  * USAGE: java Homologs <database_file> <family_seqs_file>
  *
  * NOTE: THIS IS USING THE ALTERNATE IMPLEMENTATION, THE ONE DESCRIBED BY YUAN
  */
import java.io.*;
import java.util.*;

public class Homologs_Alternate {

  static String alphabet = "ACDEFGHIKLMNPQRSTVWY";

  public static void main( String[] args ) {
    // Check args
    if(args.length != 2) {
      System.out.println("ERROR: Incorrect number of arguments.");
      System.out.println("USAGE: java Homologs <database_file> <family_seqs_file>");
      System.exit(-1);
    }
    
    // Set up vars
    String D = "";
    ArrayList<String> F = new ArrayList<String>();
    
    // Read D from file
    String line;
    try {
      // Read from file
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      while((line = in.readLine()) != null) {
        // If line isn't empty and isn't header
        if(line.length() > 0 && line.charAt(0) != '>') {
          D += line.replaceAll("\\s+","");
        }
      }
    }
    catch(Exception e) {
      System.out.println("Something went wrong reading database file.");
      System.out.println(e);
      System.exit(-1);
    }
    
    // Read F from file
    try {
      // Read from file
      BufferedReader in = new BufferedReader(new FileReader(args[1]));
      while((line = in.readLine()) != null) {
        // If line isn't empty and isn't header
        if(line.length() > 0 && line.charAt(0) != '>') {
          F.add(line.replaceAll("\\s+",""));
        }
      }
    }
    catch(Exception e) {
      System.out.println("Something went wrong reading family file.");
      System.out.println(e);
      System.exit(-1);
    }
    
    // Method Call to homologs(D,F)
    ArrayList<String> homs = homologs(D,F);
    for(int i = 0; i < homs.size(); ++i) {
      System.out.println(homs.get(i));
    }
  }
  
  /* The actual homolog method. D = Database, F = Family of sequences, M = scoring matrix */
  public static ArrayList<String> homologs( String D, ArrayList<String> F ) {
    int length = F.get(0).length();
  
    // Frequency Matrix (1st coordinate = amino acid, 2nd coordinate = position)
    double[][] freqs = new double[alphabet.length()][length];
    for(int seq = 0; seq < F.size(); ++seq) {
      String curr = F.get(seq);
      for(int pos = 0; pos < length; ++pos) {
        int aa = alphabet.indexOf(curr.charAt(pos));
        ++freqs[aa][pos];
      }
    }
    for(int i = 0; i < freqs.length; ++i) {
      for(int j = 0; j < freqs[0].length; ++j) {
        freqs[i][j] = freqs[i][j] / F.size();
      }
    }
    
    // Scoring Profile S(i,j) = Score aligning profile(i) to residue s_j in D
    double[][] S = new double[length][alphabet.length()]; // Mine: D.length() instead of alphabet.length()
    for(int i = 0; i < length; ++i) {
      for(int j = 0; j < alphabet.length(); ++j) {
        // S(i,j) is the sum of f_ki * M(r_k,s_j) for all amino acids k
        S[i][j] = 0;
        char s = alphabet.charAt(j); // Modified for Yuan's method
        for(int k = 0; k < alphabet.length(); ++k) { // For all amino acids k:
          double f = freqs[k][i]; // Get frequency of amino acid k at family position i
          char r = alphabet.charAt(k);
          //char s = D.charAt(j); // Get amino acid in database at position j
          
          S[i][j] += (f * M(r,s));
        }
      }
    }
    
    // Find random score (TEST)
    double avgRandScore = 0;
    double stdev = 0;
    int n = 50000; // n = # iterations (higher = better)
    for(int i = 0; i < n; ++i) {
      double temp = randomScore(freqs,S,length);
      avgRandScore += temp;
      stdev += (temp*temp);
    }
    avgRandScore = avgRandScore / n;
    stdev = (stdev / n) - (avgRandScore*avgRandScore);
    stdev = Math.sqrt(stdev);
    
    // Compute "threshold score". I use 95% (so p = 0.05). Change 1.645 to z-value of choice
    double threshold = (1.645 * stdev) + avgRandScore;
    
    // Now, try to find homologs
    ArrayList<String> homs = new ArrayList<String>();
    for(int start = 0; start <= D.length() - length; ++start) {
      double score = 0;
      for(int i = 0; i < length; ++i) {
        score += S[i][alphabet.indexOf(D.charAt(i+start))]; // Revised this to work with Yuan's method
      }
      if(score >= threshold) {
        homs.add(D.substring(start,start+length));
      }
    }
    System.err.println("Number of Homologs: " + homs.size());
    System.err.println();
    
    return homs;
  }
  
  public static double randomScore( double[][] freqs, double[][] S, int length ) {
    double randScore = 0;
    Random rand = new Random();
    for(int i = 0; i < length; ++i) {
      char s = alphabet.charAt(rand.nextInt(alphabet.length())); // random aa
      for(int k = 0; k < alphabet.length(); ++k) {
        double f = freqs[k][i];
        char r = alphabet.charAt(k);
        randScore += (f * M(r,s));
      }
    }
    return randScore;
  }
  
  /* M is a scoring matrix, but it's really "2" for match and "-1" for mismatch */
  public static int M( char a1, char a2 ) {
    if(a1 == a2) {
      return 2;
    }
    else {
      return -1;
    }
  }
}