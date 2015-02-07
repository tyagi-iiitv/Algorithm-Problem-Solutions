/**
  * Alexander Niema Moshiri; 2 October 2014; CSE 182 Assignment 1
  * 
  * STEP 3:
  * Reads each line of FASTA and dumps the header line of each sequence in the
  * database followed by the length of that sequence
  *
  * USAGE: java Cat fasta_file
  */
import java.io.*;

public class Cat {
  public static void main( String[] args ) {
    // Check for arg length
    if(args.length != 1) {
      System.out.println("ERROR: Incorrect number of arguments. Should have 1 argument.");
      System.exit(-1);
    }
    
    // Read from file
    String line;
    String currHead = null;
    int count = 0;
    try {
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      while((line = in.readLine()) != null) {
        // If line isn't empty
        if(line.length() != 0) {
          // If header line
          if(line.charAt(0) == '>') {
            // If not first line, output current count
            if(currHead != null) {
              System.out.println(count);
            }
            currHead = line;
            System.out.println(currHead);
            count = 0;
          }
          
          // If not a header line (so a sequence line)
          else {
            // Remove all whitespace
            line = line.replaceAll("\\s","");
            
            // Add length to 'count'
            count += line.length();
          }
        }
      }
      // Output final count
      System.out.println(count);
    }
    catch(Exception e) {
      System.out.println("Something went wrong.");
      e.printStackTrace();
      System.exit(-1);
    }
  }
}