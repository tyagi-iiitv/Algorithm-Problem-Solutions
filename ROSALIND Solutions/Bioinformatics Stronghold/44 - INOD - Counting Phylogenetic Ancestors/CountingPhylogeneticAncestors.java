public class CountingPhylogeneticAncestors {
  public static void main( String[] args ) {
    int n = 7053;
    System.out.println(countingPhylogeneticAncestors(n));
  }
  
  public static int countingPhylogeneticAncestors( int n ) {
    return n-2;
  }
}