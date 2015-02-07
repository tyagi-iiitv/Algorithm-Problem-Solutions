/**
  * Alexander Niema Moshiri; 23 October 2014; CSE 182 Assignment 3
  *
  * USAGE: java randomDNA <number of seqs> <length of seqs>
  */
import java.io.*;
import java.util.*;

public class randomDNA {
  public static void main( String[] args ) {
    // Check args for validity
    if(args.length != 2) {
      System.out.println("ERROR: Incorrect number of arguments. Correct usage:");
      System.out.println("java randomDNA <number of seqs> <length of seqs>");
      System.exit(-1);
    }
    
    int numSeqs = -1;
    int lenSeqs = -1;
    
    // Parse args
    try {
      numSeqs = Integer.parseInt(args[0]);
      lenSeqs = Integer.parseInt(args[1]);
    } catch(Exception e) {
      System.out.println("ERROR: Arguments were not valid integers. Correct usage:");
      System.out.println("java randomDNA <number of seqs> <length of seqs>");
      System.exit(-1);
    }
    
    // Check ints
    if(numSeqs < 1 || lenSeqs < 1) {
      System.out.println("ERROR: Number of Sequences and Length of Sequences must be positive integers");
      System.exit(-1);
    }
    
    // Create array of seqs
    String[] seqs = new String[numSeqs];
    Random rand = new Random();
    for(int seq = 0; seq < numSeqs; ++seq) {
      seqs[seq] = "";
      for(int i = 0; i < lenSeqs; ++i) {
        seqs[seq] += numToNucleo(rand.nextInt(4));
      }
    }
    
    // Print seqs
    for(int i = 0; i < seqs.length; ++i) {
      System.out.println(">Random Sequence " + (i+1));
      System.out.println(seqs[i]);
    }
    
    // Compute nucleotide freqs
    int[] counts = new int[4]; // counts[0] = A, 1 = C, 2 = G, 3 = T
    for(int seq = 0; seq < seqs.length; ++seq) {
      for(int i = 0; i < seqs[seq].length(); ++i) {
        counts[nucleoToNum(seqs[seq].charAt(i))]++;
      }
    }
    
    // Print nucleotide freqs
    System.err.println();
    System.err.println("p(A) = " + ((double)(counts[0]) / (double)(numSeqs*lenSeqs)));
    System.err.println("p(C) = " + ((double)(counts[1]) / (double)(numSeqs*lenSeqs)));
    System.err.println("p(G) = " + ((double)(counts[2]) / (double)(numSeqs*lenSeqs)));
    System.err.println("p(T) = " + ((double)(counts[3]) / (double)(numSeqs*lenSeqs)));
  }
  
  /* Helper method, A = 0, C = 1, G = 2, T = 3 */
  public static int nucleoToNum( char n ) {
    switch(n) {
      case 'A': return 0;
      case 'C': return 1;
      case 'G': return 2;
      case 'T': return 3;
      default: System.out.println("ERROR: Invalid nucleotide");
      System.exit(-1);
      return -1;
    }
  }
  
  /* Helper method, 0 = A, 1 = C, 2 = G, T = 3 */
  public static char numToNucleo( int n ) {
    switch(n) {
      case 0: return 'A';
      case 1: return 'C';
      case 2: return 'G';
      case 3: return 'T';
      default: System.out.println("ERROR: Invalid random number.");
      System.exit(-1);
      return 'X';
    }
  }
}