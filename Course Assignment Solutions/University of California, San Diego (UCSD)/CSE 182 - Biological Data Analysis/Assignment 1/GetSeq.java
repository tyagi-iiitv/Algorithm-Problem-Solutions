/**
  * Alexander Niema Moshiri; 2 October 2014; CSE 182 Assignment 1
  * 
  * STEP 6:
  * Takes a short sequence as query, and, using the index files "data.seq" and
  * "data.in" (must be placed in the same directory as the program), returns
  * the gi number of the database sequence containing the query string.
  *
  * USAGE: java CreateDB fasta_file
  */
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GetSeq {
  public static void main( String[] args ) {
    // Check for arg length
    if(args.length != 1) {
      System.out.println("ERROR: Incorrect number of arguments. Should have 1 argument.");
      System.exit(-1);
    }
    
    // Read from file
    String line;
    try {
      // Read "data.seq" and find offset of query
      String dataSeq = new String(Files.readAllBytes(Paths.get("data.seq")));
      int offset = dataSeq.indexOf(args[0]);
      if(offset == -1) {
        System.out.println("ERROR: Query not found in database.");
        System.exit(-1);
      }
      
      // Search for offset
      BufferedReader dataIn  = new BufferedReader(new FileReader("data.in"));
      int possible = 0;
      String posString = null;
      while((line = dataIn.readLine()) != null) {
        String[] parts = line.split(" ");
        int currOff = Integer.parseInt(parts[1]);
        if(offset >= currOff) {
          possible = currOff;
          posString = parts[0];
        }
        else {
          break;
        }
      }
      System.out.println(posString);
    }
    catch(Exception e) {
      System.out.println("Something went wrong.");
      e.printStackTrace();
      System.exit(-1);
    }
  }
}