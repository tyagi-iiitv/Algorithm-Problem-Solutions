/**
  * Alexander Niema Moshiri; 2 October 2014; CSE 182 Assignment 1
  * 
  * STEP 5:
  * Read the data file and create two index files.
  * 
  * The first file data.seq contains the concatenation of all of the sequences
  * from each file with no headers, and no newline symbols. Insert the special
  * symbol '@' between any two sequences (13pt).
  *
  * The second file data.in contains a line with two terms for each sequence.
  * The first term is the 'gi number' for the sequence, and the second term is
  * the offset in "data.seq" where the sequence starts (13pt).
  *
  * USAGE: java CreateDB fasta_file
  */
import java.io.*;
import java.util.*;

public class CreateDB {
  public static void main( String[] args ) {
    // Check for arg length
    if(args.length != 1) {
      System.out.println("ERROR: Incorrect number of arguments. Should have 1 argument.");
      System.exit(-1);
    }
    
    ArrayList<String> giNums = new ArrayList<>();
    ArrayList<String> seqs   = new ArrayList<>();
    
    String line;
    String currSeq = "";
    try {
      // Read from file
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      while((line = in.readLine()) != null) {
        // If line isn't empty
        if(line.length() != 0) {
          // If header line
          if(line.charAt(0) == '>') {
            // Add previous sequence to seqs
            if(!currSeq.equals("")) {
              seqs.add(currSeq);
              currSeq = "";
            }
            
            // Get GI number and add to giNums
            String[] parts = line.split("\\|");
            giNums.add(parts[1]);
          }
          
          // If not a header line (so a sequence line)
          else {
            // Remove all whitespace and add to currSeq
            currSeq += line.replaceAll("\\s","");
          }
        }
      }
      // Add final sequence to seqs
      seqs.add(currSeq);
      
      // giNums should be same size as seqs
      if(giNums.size() != seqs.size()) {
        System.out.println("ERROR: # GI Numbers and # Sequences differs.");
        System.exit(-1);
      }
      
      // Output sequences to data.seq
      int offset = 0;
      File f1 = new File("data.seq");
      f1.createNewFile();
      File f2 = new File("data.in");
      f2.createNewFile();
      BufferedWriter dataSeq = new BufferedWriter(new FileWriter(f1));
      BufferedWriter dataIn  = new BufferedWriter(new FileWriter(f2));
      
      for(int i = 0; i < giNums.size()-1; ++i) {
        dataIn.write(giNums.get(i) + " " + offset);
        dataIn.newLine();
        
        dataSeq.write(seqs.get(i) + '@');
        offset += (seqs.get(i).length() + 1);
      }
      dataIn.write(giNums.get(giNums.size()-1) + " " + offset);
      dataSeq.write(seqs.get(giNums.size()-1));
      
      in.close();
      dataIn.close();
      dataSeq.close();
    }
    catch(Exception e) {
      System.out.println("Something went wrong.");
      e.printStackTrace();
      System.exit(-1);
    }
  }
}