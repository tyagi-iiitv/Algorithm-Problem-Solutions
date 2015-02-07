public class CalculatingExpectedOffspring {
  public static void main( String[] args ) {
    String in = "18906 18430 16857 16293 19938 18137";
    String[] parts = in.split(" ");
    int n1 = Integer.parseInt(parts[0]);
    int n2 = Integer.parseInt(parts[1]);
    int n3 = Integer.parseInt(parts[2]);
    int n4 = Integer.parseInt(parts[3]);
    int n5 = Integer.parseInt(parts[4]);
    int n6 = Integer.parseInt(parts[5]);
    System.out.println(expectedOffspring(n1,n2,n3,n4,n5,n6));
  }
  
  public static double expectedOffspring( int AA_AA, int AA_Aa, int AA_aa, int Aa_Aa, int Aa_aa, int aa_aa ) {
    double e4 = 0.75*Aa_Aa;
    double e5 = 0.5*Aa_aa;
    return (AA_AA+AA_Aa+AA_aa+e4+e5)*2;
  }
}