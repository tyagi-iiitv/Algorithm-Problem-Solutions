/**
  * Alexander Niema Moshiri; 14 October 2014; CSE 182 Assignment 2
  *
  * USAGE: java locAL <seq file1> <seq file2> -m <match> -s <mismatch> -go <gap-open> -ge <gap-extend>
  *
  * <seq file1> and <seq file2> MUST be the FIRST TWO PARAMETERS!
  * -m, -s, -go, and -ge can be in any order
  *
  * NOTE: Output is printed with no line-length limitations, so it might be easier to view
  *       the alignment by routing STDOUT to file using the '>' symbol
  *       Example: java locAL human.txt mouse.txt -m 1 -s -3 -go -2 -ge -2 > my_alignment.txt
  */
import java.io.*;
import java.util.*;

public class locAL {
  // Initialize instance vars
  static String seq1head = null;
  static String seq1     = null;
  static String seq2head = null;
  static String seq2     = null;
  static int match       = -1;
  static int mismatch    = -1;
  static int go          = -1;
  static int ge          = -1;
  static int bestEndI = 0;
  static int bestEndJ = 0;
  static int bestOption = 0;

  static int[][] wasteChoices;
  
  // instance var for getting alignment from score
  static String shorter = null; // shorter
  static String longer = null; // longer

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
      // Read Sequence 1
      String line = null;
      BufferedReader readSeq1 = new BufferedReader(new FileReader(fileSeq1));
      while((line = readSeq1.readLine()) != null) {
        // If header line:
        if(line.charAt(0) == '>') {
          seq1head = line;
        }
        // If sequence line
        else if(line.length() != 0) {
          seq1 += line;
        }
      }
      
      // Read Sequence 2
      line = null;
      BufferedReader readSeq2 = new BufferedReader(new FileReader(fileSeq2));
      while((line = readSeq2.readLine()) != null) {
        // If header line:
        if(line.charAt(0) == '>') {
          seq2head = line;
        }
        // If sequence line
        else {
          seq2 += line;
        }
      }
    } catch(Exception e) {
      System.out.println("ERROR: Something went wrong when reading files.");
      System.exit(-1);
    }
    
    // Find which sequence is shorter vs longer
    shorter = seq1;
    longer = seq2;
    if(seq2.length() < seq1.length()) {
      shorter = seq2;
      longer = seq1;
    }
    
    // Output diagnostic notes to STDERR
    /*System.err.println("Sequence 1 length:      " + seq1.length());
    System.err.println("Sequence 2 length:      " + seq2.length());
    System.err.println("Match Score (-m):       " + match);
    System.err.println("Mismatch Score (-s):    " + mismatch);
    System.err.println("Gap Open score (-go):   " + go);
    System.err.println("Gap Extend Score (-ge): " + ge);
    System.err.println("Local Alignment Score:  " + S(longer,shorter));*/
    int optimalScore = S(longer,shorter); // generate score of optimal alignment
    int endI = bestEndI; // end of longer sequence (the last si)
    int endJ = bestEndJ; // end of shorter sequence (the last tj)
    S(new StringBuilder(longer).reverse().toString(),new StringBuilder(shorter).reverse().toString());
    int startI = longer.length() - bestEndI - 1; // start of longer sequence (the first si)
    int startJ = shorter.length() - bestEndJ - 1; // start of shorter sequence (the first tj)
    
    // Create the "trimmed" sequences for the targeted global alignment
    String lTrimmed = longer.substring(startI,endI+1);
    String sTrimmed = shorter.substring(startJ,endJ+1);
    
    // Perform non-linear global alignment
    Swaste(lTrimmed,sTrimmed);
    
    // Create 3 strings (query sequence, alignment symbols, subject sequence)
    String[] alignment = new String[3];
    alignment[0] = "";
    alignment[1] = "";
    alignment[2] = "";
    
    // Construct alignment
    int cI = lTrimmed.length()-1;
    int cJ = sTrimmed.length()-1;
    while(cI >= 0 && cJ >= 0) {
      // 1 = STOP, 2 = match/mismatch, 3 = s with -, 4 = - with t
      int curr = wasteChoices[cI][cJ];
      if(curr == 1) {
        alignment[0] += lTrimmed.charAt(cI--);
        alignment[1] += '|';
        alignment[2] += sTrimmed.charAt(cJ--);
        break;
      }
      else if(curr == 2) {
        alignment[0] += lTrimmed.charAt(cI--);
        alignment[1] += '|';
        alignment[2] += sTrimmed.charAt(cJ--);
      }
      else if(curr == 3) {
        alignment[0] += lTrimmed.charAt(cI--);
        alignment[1] += ' ';
        alignment[2] += '-';
      }
      else if(curr == 4) {
        alignment[0] += '-';
        alignment[1] += ' ';
        alignment[2] += sTrimmed.charAt(cJ--);
      }
    }
    alignment[0] = new StringBuilder(alignment[0]).reverse().toString();
    alignment[1] = new StringBuilder(alignment[1]).reverse().toString();
    alignment[2] = new StringBuilder(alignment[2]).reverse().toString();
    
    // Remove "null" if included
    if(alignment[0].contains("null")) {
      alignment[0] = alignment[0].substring(4,alignment[0].length());
      alignment[1] = alignment[1].substring(4,alignment[1].length());
      alignment[2] = alignment[2].substring(4,alignment[2].length());
    }
    
    // Print alignment to STDOUT
    System.out.println("OPTIMAL SCORE:    " + optimalScore);
    System.out.println("ALIGNMENT LENGTH: " + alignment[1].length());
    System.out.println();
    System.out.println(alignment[0]);
    System.out.println(alignment[1]);
    System.out.println(alignment[2]);
  }
  
  // S method, but not linear space (wasteful)
  public static void Swaste( String s, String t ) {
    int n = s.length();
    int m = t.length();
    
    // Create S (score of best alignment s_i to t_j), D (... s_i to gap), and I (... gap to t_j)
    int[][] wasteS = new int[n][m];
    int[][] wasteD = new int[n][m];
    int[][] wasteI = new int[n][m];
    wasteChoices = new int[n][m];
    
    // Fill "table"
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        // Find D[i,j]
        int dOption1 = 0;
        int dOption2 = 0;
        if(i > 0) {
          dOption1 = wasteD[i-1][j] + ge;
          dOption2 = wasteS[i-1][j] + go + ge;
        }
        wasteD[i][j] = dOption1;
        if(dOption2 > wasteD[i][j]) {
          wasteD[i][j] = dOption2;
        }
        if(i == 0) {
          wasteD[i][j] = go + ge;
        }
        
        // Find I[i,j]
        int iOption1 = 0;
        int iOption2 = 0;
        if(j > 0) {
          iOption1 = wasteI[i][j-1] + ge;
          iOption2 = wasteS[i][j-1] + go + ge;
        }
        wasteI[i][j] = iOption1;
        if(iOption2 > wasteI[i][j]) {
          wasteI[i][j] = iOption2;
        }
        if(j == 0) {
          wasteI[i][j] = go + ge;
        }
        
        // Initialize "options" (scores)
        int optionStop = 0;
        int optionPair = Integer.MIN_VALUE;
        int optionSgap = wasteD[i][j];
        int optionTgap = wasteI[i][j];
        // Calculate optionPair (si <-> tj)
        if(i > 0 && j > 0) {
          optionPair = wasteS[i-1][j-1] + C( s.charAt(i), t.charAt(j) );
        }
        
        // S[i][j] = max(the 4 options)
        wasteS[i][j] = optionStop;
        int choice = 1; // 1 = STOP, 2 = match/mismatch, 3 = s with -, 4 = - with t
        if(optionPair > wasteS[i][j]) {
          wasteS[i][j] = optionPair;
          choice = 2;
        }
        if(optionSgap > wasteS[i][j]) {
          wasteS[i][j] = optionSgap;
          choice = 3;
        }
        if(optionTgap > wasteS[i][j]) {
          wasteS[i][j] = optionTgap;
          choice = 4;
        }
        wasteChoices[i][j] = choice;
      }
    }
  }
  
  // Helper method to find S for two given strings (|s| > |t|)
  public static int S( String s, String t ) {
    int n = s.length();
    int m = t.length();
    
    // Create S (score of best alignment s_i to t_j), D (... s_i to gap), and I (... gap to t_j)
    int[][] S = new int[2][];
    int[][] D = new int[2][];
    int[][] I = new int[2][];
    
    String[][][] paths = new String[2][][];
    
    int bestScore = 0;
    S[0] = new int[m];
    D[0] = new int[m];
    I[0] = new int[m];
    
    // Fill "table"
    for(int i = 0; i < n; ++i) {
      // "Current" Row is now "Previous", create new "Current"
      S[0] = S[1];
      D[0] = D[1];
      I[0] = I[1];
      //paths[0] = paths[1];
      S[1] = new int[m];
      D[1] = new int[m];
      I[1] = new int[m];
      
      for(int j = 0; j < m; ++j) {
        // Find D[i,j]
        int dOption1 = 0;
        int dOption2 = 0;
        if(i > 0) {
          dOption1 = D[0][j] + ge;
          dOption2 = S[0][j] + go + ge;
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
          iOption2 = S[1][j-1] + go + ge;
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
          optionPair = S[0][j-1] + C( s.charAt(i), t.charAt(j) );
        }
        
        // S[i][j] = max(the 4 options)
        S[1][j] = optionStop;
        int choice = 1; // 1 = STOP, 2 = match/mismatch, 3 = s with -, 4 = - with t
        if(optionPair > S[1][j]) {
          S[1][j] = optionPair;
          choice = 2;
        }
        if(optionSgap > S[1][j]) {
          S[1][j] = optionSgap;
          choice = 3;
        }
        if(optionTgap > S[1][j]) {
          S[1][j] = optionTgap;
          choice = 4;
        }
        
        // Check to see if this is best score
        if(S[1][j] > bestScore) {
          bestScore = S[1][j];
          bestEndI = i;
          bestEndJ = j;
          bestOption = choice;
        }
      }
    }
    
    // After alignment, S[0] is 2nd to last row, and S[1] is last row
    return bestScore;
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