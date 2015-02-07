/** Simple program to find info for #4b
    USAGE: java Num4B <swissprot_fasta>
 */
import java.io.*;

public class Num4B {
  static String alphabet = "ACDEFGHIKLMNPQRSTVWY";
  public static void main( String[] args ) {
    if(args.length != 1) {
      System.out.println("Please provide Swissprot FASTA filename as argument.");
      System.out.println("USAGE: java Num4B <swissprot_fasta>");
      System.exit(-1);
    }
    
    double[] freqs = new double[alphabet.length()];
    long total = 0;
    
    String line;
    try {
      // Read from file
      BufferedReader in = new BufferedReader(new FileReader(args[0]));
      while((line = in.readLine()) != null) {
        // If line isn't empty and isn't header
        if(line.length() > 0 && line.charAt(0) != '>') {
          for(int i = 0; i < line.length(); ++i) {
            char curr = line.charAt(i);
            int index = alphabet.indexOf(curr);
            if(index >= 0) {
              ++freqs[alphabet.indexOf(curr)];
              ++total;
            }
          }
        }
      }
    }
    catch(Exception e) {
      System.out.println("Something went wrong reading Swissprot FASTA.");
      System.out.println(e);
      System.exit(-1);
    }
    
    System.out.println("AMINO ACID FREQUENCIES:");
    for(int i = 0; i < freqs.length; ++i) {
      freqs[i] = freqs[i] / total;
      System.out.println(alphabet.charAt(i) + ": " + freqs[i]);
    }
    System.out.println();
    
    System.out.println("PROBABILITIES OF MONOMERS:");
    String[] threeMer = {"STAGCN","RKH","LIVMAFY"};
    double product = 1;
    for(int mon = 0; mon < threeMer.length; ++mon) {
      String monomer = threeMer[mon];
      System.out.print(monomer + ": ");
      // fix formatting
      if(mon == 0) {
        System.out.print(" ");
      }
      else if(mon == 1) {
        System.out.print("    ");
      }
      double prob = 0;
      for(int i = 0; i < monomer.length(); ++i) {
        char c = monomer.charAt(i);
        prob += freqs[alphabet.indexOf(c)];
      }
      System.out.println(prob);
      product = product * prob;
    }
    System.out.println();
    System.out.println("Therefore, P(["+threeMer[0]+"]-["+threeMer[1]+"]-["+threeMer[2]+"]): " + product);
  }
}