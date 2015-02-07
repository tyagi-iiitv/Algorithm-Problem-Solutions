public class MultiplesOfThreeAndFive {
  public static void main( String[] args ) {
    int n = 1000;
    System.out.println(multiplesOfThreeAndFive(n));
  }
  
  public static int multiplesOfThreeAndFive( int n ) {
    int sum = 0;
    for(int i = 3; i < n; ++i) {
      if(i%3 == 0 || i%5 == 0) {
        sum += i;
      }
    }
    return sum;
  }
}