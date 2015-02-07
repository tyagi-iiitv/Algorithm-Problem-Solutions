/**
  * Alexander Niema Moshiri; 14 October 2014; CSE 182 Assignment 3
  *
  * USAGE: java locAL <seq file1> <seq file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>
  *
  * <seq file1> and <seq file2> MUST be the FIRST TWO PARAMETERS!
  * -m, -s, -go, and -ge can be in any order
  *
  * NOTE: This version supports multiple sequences per file, but <file1> and
  *       <file2> MUST have an equal number of sequences!!! This is to help
  *       automate batch aligning. Any given ith sequence in <file1> will be
  *       paired with the ith sequence in <file2> for alignment.
  *
  *       To set linear gap penalties, just use a gap-open penalty of 0
  */
import java.io.*;
import java.util.*;

public class locAL {
  // Initialize instance vars
  static ArrayList<String> f1heads = new ArrayList<>();
  static ArrayList<String> f1seqs = new ArrayList<>();
  static ArrayList<String> f2heads = new ArrayList<>();
  static ArrayList<String> f2seqs = new ArrayList<>();
  static int match       = -1;
  static int mismatch    = -1;
  static int go          = -1;
  static int ge          = -1;
  
  // instance var for getting alignment from score
  static int[] best;

  public static void main( String[] args ) {
    // Check args for validity
    if(args.length != 10) {
      System.out.println("ERROR: Incorrect number of arguments. Correct usage:");
      System.out.println("java locAL <seq file1> <seq file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>");
      System.exit(-1);
    }
    
    // Make parameter vars
    String fileSeq1 = null;
    String fileSeq2 = null;
    
    // Parse args
    try {
      fileSeq1 = args[0];
      fileSeq2 = args[1];
      for(int i = 2; i < args.length; ++i) {
        if(args[i].equals("-m")) {
          match = Integer.parseInt(args[i+1]);
          ++i;
        }
        else if(args[i].equals("-s")) {
          mismatch = Integer.parseInt(args[i+1]);
          ++i;
        }
        else if(args[i].equals("-go")) {
          go = Integer.parseInt(args[i+1]);
          ++i;
        }
        else if(args[i].equals("-ge")) {
          ge = Integer.parseInt(args[i+1]);
          ++i;
        }
      }
    } catch(Exception e) {
      System.out.println("ERROR: Arguments are incorrect. Correct usage:");
      System.out.println("java locAL <seq file1> <seq file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>");
      System.exit(-1);
    }
    
    // Begin file parse for seqs
    try {
      // Read File 1
      String line = null;
      BufferedReader readSeq1 = new BufferedReader(new FileReader(fileSeq1));
      String tempSeq = "";
      f1heads.add(readSeq1.readLine()); // read first line (header)
      while((line = readSeq1.readLine()) != null) {
        if(line.length() != 0) {
          // If header line:
          if(line.charAt(0) == '>') {
            f1heads.add(line);
            f1seqs.add(tempSeq);
            tempSeq = "";
          }
          // If sequence line
          else if(line.length() != 0) {
            tempSeq += line;
          }
        }
      }
      f1seqs.add(tempSeq);

      // Read File 2
      line = null;
      BufferedReader readSeq2 = new BufferedReader(new FileReader(fileSeq2));
      tempSeq = "";
      f2heads.add(readSeq2.readLine()); // read first line (header)
      while((line = readSeq2.readLine()) != null) {
        if(line.length() != 0) {
          // If header line:
          if(line.charAt(0) == '>') {
            f2heads.add(line);
            f2seqs.add(tempSeq);
            tempSeq = "";
          }
          // If sequence line
          else {
            tempSeq += line;
          }
        }
      }
      f2seqs.add(tempSeq);
    } catch(Exception e) {
      System.out.println("ERROR: Something went wrong when reading files.");
      System.exit(-1);
    }
    
    // Check for equal # seqs
    if(f1heads.size() != f2heads.size() || f1heads.size() != f1seqs.size() || f1heads.size() != f2seqs.size()) {
      System.out.println("Number of sequences in files don't match. Please check files and try again.");
      System.exit(-1);
    }
    
    System.out.println("seq_from_file1,seq_from_file2,alignment_score,alignment_length");
    
    for(int i = 0; i < f1heads.size(); ++i) {
      System.err.println("Aligning sequence pair " + (i+1) + "/" + f1heads.size());
      int[] optimal = S(f1seqs.get(i),f2seqs.get(i));
      int alignScore = optimal[0];
      int endI = optimal[2];
      int endJ = optimal[3];
      String trimS = f1seqs.get(i).substring(0,endI+1);
      String trimT = f2seqs.get(i).substring(0,endJ+1);
      optimal = S(new StringBuilder(trimS).reverse().toString(), new StringBuilder(trimT).reverse().toString());
      int startI = trimS.length() - optimal[2] - 1;
      int startJ = trimT.length() - optimal[3] - 1;
      
      int alignLength = endI - startI + 1 + optimal[1];
      System.out.println(f1heads.get(i)+","+f2heads.get(i)+","+alignScore+","+alignLength);
    }
  }
  
  // Helper method to find S for two given strings (|s| > |t|)
  public static int[] S( String s, String t ) {
    int n = s.length();
    int m = t.length();
    
    // Create S (score of best alignment s_i to t_j), D (... s_i to gap), and I (... gap to t_j)
    int[][][] S = new int[2][][];
    int[][] D = new int[2][];
    int[][] I = new int[2][];
    
    S[0] = new int[m][2]; // 0 = score, 1 = # gaps
    D[0] = new int[m];
    I[0] = new int[m];
    best = new int[4];
    
    // Fill "table"
    for(int i = 0; i < n; ++i) {
      // "Current" Row is now "Previous", create new "Current"
      S[0] = S[1];
      D[0] = D[1];
      I[0] = I[1];
      //paths[0] = paths[1];
      S[1] = new int[m][2];
      D[1] = new int[m];
      I[1] = new int[m];
      
      for(int j = 0; j < m; ++j) {
        // Find D[i,j]
        int dOption1 = 0;
        int dOption2 = 0;
        if(i > 0) {
          dOption1 = D[0][j] + ge;
          dOption2 = S[0][j][0] + go + ge;
        }
        D[1][j] = dOption1;
        if(dOption2 > D[1][j]) {
          D[1][j] = dOption2;
        }
        if(i == 0) {
          D[1][j] = go + ge;
        }
        
        // Find I[i,j]
        int iOption1 = 0;
        int iOption2 = 0;
        if(j > 0) {
          iOption1 = I[1][j-1] + ge;
          iOption2 = S[1][j-1][0] + go + ge;
        }
        I[1][j] = iOption1;
        if(iOption2 > I[1][j]) {
          I[1][j] = iOption2;
        }
        if(j == 0) {
          I[1][j] = go + ge;
        }
        
        // Initialize "options" (scores)
        int optionStop = 0;
        int optionPair = Integer.MIN_VALUE;
        int optionSgap = D[1][j];
        int optionTgap = I[1][j];
        // Calculate optionPair (si <-> tj)
        if(i > 0 && j > 0) {
          optionPair = S[0][j-1][0] + C( s.charAt(i), t.charAt(j) );
        }
        
        // S[i][j] = max(the 4 options)
        int optBest = optionStop;
        int gapBest = 0;
        if(optionPair > optBest) {
          optBest = optionPair;
          gapBest = S[0][j-1][1];
        }
        if(optionSgap > optBest) {
          optBest = optionSgap;
          gapBest = S[0][j][1] + 1;
        }
        if(optionTgap > optBest) {
          optBest = optionTgap;
          gapBest = S[1][j-1][1];
        }
        S[1][j][0] = optBest;
        S[1][j][1] = gapBest;
        
        // Check to see if this is best score
        if(S[1][j][0] > best[0]) {
          best[0] = S[1][j][0]; // score
          best[1] = S[1][j][1]; // # gaps s
          best[2] = i; // best end i
          best[3] = j; // best end j
        }
      }
    }
    
    // After alignment, S[0] is 2nd to last row, and S[1] is last row
    return best;
  }
  
  // Helper method just to return "C" (score) for a match/mismatch
  public static int C( char si, char tj ) {
    if(si == tj) {
      return match;
    }
    else {
      return mismatch;
    }
  }
}