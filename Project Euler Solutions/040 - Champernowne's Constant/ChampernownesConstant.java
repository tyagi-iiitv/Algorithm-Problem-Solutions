public class ChampernownesConstant {
  public static void main( String[] args ) {
    int[] indices = {1,10,100,1000,10000,100000,1000000};
    System.out.println(prodChampernownesConstant(indices));
  }
  
  public static int prodChampernownesConstant( int[] indices ) {
    int maxI = 0;
    for(int i : indices) {
      if(i > maxI) {
        maxI = i;
      }
    }
    String con = "";
    int i = 1;
    while(con.length() < maxI) {
      con += i++;
    }
    int prod = 1;
    for(int j : indices) {
      prod *= (con.charAt(j-1) - '0');
    }
    return prod;
  }
}