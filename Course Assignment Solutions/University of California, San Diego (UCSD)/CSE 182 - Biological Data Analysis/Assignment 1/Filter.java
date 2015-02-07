/**
  * Alexander Niema Moshiri; 2 October 2014; CSE 182 Assignment 1
  * 
  * STEP 4:
  * Extract and output all mouse and rat sequences
  *
  * USAGE: java Filter fasta_file
  */
import java.io.*;

public class Filter {
  public static void main( String[] args ) {
    // Check for arg length
    if(args.length != 1) {
      System.out.println("ERROR: Incorrect number of arguments. Should have 1 argument.");
      System.exit(-1);
    }
    
    // Read from file
    String line;
    String currHead = null;
    String currSeq = "";
    boolean rat = false;
    try {
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      while((line = in.readLine()) != null) {
        // If line isn't empty
        if(line.length() != 0) {
          // If header line
          if(line.charAt(0) == '>') {
            // If finished a rat sequence, output current sequence
            if(rat) {
              printSeq(currSeq);
            }
            currHead = line;
            if(isRat(currHead)) {
              rat = true;
              System.out.println(currHead);
            }
            else {
              rat = false;
            }
            currSeq = "";
          }
          
          // If not a header line (so a sequence line)
          else {
            // Remove all whitespace and add to currSeq
            currSeq += line.replaceAll("\\s","");
          }
        }
      }
      // Output final count
      if(rat) {
        printSeq(currSeq);
      }
    }
    catch(Exception e) {
      System.out.println("Something went wrong.");
      e.printStackTrace();
      System.exit(-1);
    }
  }
  
  /** Helper method to see if a given header is from a rat/mouse */
  public static boolean isRat( String header ) {
    return header.contains("RAT") || header.contains("rat") || header.contains("Rat") ||
           header.contains("RATTUS") || header.contains("rattus") || header.contains("Rattus") ||
           header.contains("MOUSE") || header.contains("mouse") || header.contains("Mouse") ||
           header.contains("MUS") || header.contains("mus") || header.contains("Mus");
  }
  
  /** Helper method to print sequence 60 characters at a time */
  public static void printSeq( String seq ) {
    String[] parts = seq.split("(?<=\\G.{60})");
    for(int i = 0; i < parts.length; ++i) {
      System.out.println(parts[i]);
    }
  }
}