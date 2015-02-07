import java.util.*;
import java.io.*;
public class RNASplicing {
  public static HashMap<String,Character> codonTable = new HashMap<>();
  
  public static void main( String[] args ) {
    initCodonTable();
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> seqs = new ArrayList<>();
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String line;
      String currSeq = "";
      while((line = r.readLine()) != null || true) {
        if(line == null) {
          seqs.add(currSeq);
          break;
        }
        if(line.charAt(0) == '>') {
          ids.add(line.substring(1,line.length()));
          if(currSeq.length() != 0) {
            seqs.add(currSeq);
            currSeq = "";
          }
        }
        else {
          currSeq += line;
        }
      }
    } catch(Exception e) {}
    
    String temp = splice(seqs).replace('T','U');
    System.out.println(rnaToProtein(temp));
  }
  
  public static String splice( ArrayList<String> seqs ) {
    String mainSeq = seqs.get(0);
    for(int i = 1; i < seqs.size(); ++i) {
      mainSeq = mainSeq.replace(seqs.get(i),"");
    }
    return mainSeq;
  }
  
  public static String rnaToProtein( String rna ) {
    rna = rna.toUpperCase();
    String protein = "";
    for(int i = 0; i <= rna.length()-3; i += 3) {
      String codon = rna.substring(i,i+3);
      char next = codonTable.get(codon);
      if(next == '\0') {
        break;
      }
      protein += next;
    }
    return protein;
  }
  
  public static void initCodonTable() {
    codonTable.put("UUU",'F');
    codonTable.put("CUU",'L');
    codonTable.put("AUU",'I');
    codonTable.put("GUU",'V');
    codonTable.put("UUC",'F');
    codonTable.put("CUC",'L');
    codonTable.put("AUC",'I');
    codonTable.put("GUC",'V');
    codonTable.put("UUA",'L');
    codonTable.put("CUA",'L');
    codonTable.put("AUA",'I');
    codonTable.put("GUA",'V');
    codonTable.put("UUG",'L');
    codonTable.put("CUG",'L');
    codonTable.put("AUG",'M');
    codonTable.put("GUG",'V');
    codonTable.put("UCU",'S');
    codonTable.put("CCU",'P');
    codonTable.put("ACU",'T');
    codonTable.put("GCU",'A');
    codonTable.put("UCC",'S');
    codonTable.put("CCC",'P');
    codonTable.put("ACC",'T');
    codonTable.put("GCC",'A');
    codonTable.put("UCA",'S');
    codonTable.put("CCA",'P');
    codonTable.put("ACA",'T');
    codonTable.put("GCA",'A');
    codonTable.put("UCG",'S');
    codonTable.put("CCG",'P');
    codonTable.put("ACG",'T');
    codonTable.put("GCG",'A');
    codonTable.put("UAU",'Y');
    codonTable.put("CAU",'H');
    codonTable.put("AAU",'N');
    codonTable.put("GAU",'D');
    codonTable.put("UAC",'Y');
    codonTable.put("CAC",'H');
    codonTable.put("AAC",'N');
    codonTable.put("GAC",'D');
    codonTable.put("CAA",'Q');
    codonTable.put("AAA",'K');
    codonTable.put("GAA",'E');
    codonTable.put("CAG",'Q');
    codonTable.put("AAG",'K');
    codonTable.put("GAG",'E');
    codonTable.put("UGU",'C');
    codonTable.put("CGU",'R');
    codonTable.put("AGU",'S');
    codonTable.put("GGU",'G');
    codonTable.put("UGC",'C');
    codonTable.put("CGC",'R');
    codonTable.put("AGC",'S');
    codonTable.put("GGC",'G');
    codonTable.put("CGA",'R');
    codonTable.put("AGA",'R');
    codonTable.put("GGA",'G');
    codonTable.put("UGG",'W');
    codonTable.put("CGG",'R');
    codonTable.put("AGG",'R');
    codonTable.put("GGG",'G');
    codonTable.put("UAA",'\0');
    codonTable.put("UAG",'\0');
    codonTable.put("UGA",'\0');
  }
}