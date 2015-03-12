public class PandigitalMultiples {
  public static void main( String[] args ) {
    System.out.println(pandigitalMultiples());
  }
  
  public static int pandigitalMultiples() {
    int best = 192384576;
    for(int i = 1; i < 1000000; ++i) {
      int pan = pandigitalMultiple(i);
      if(pan > best) {
        best = pan;
      }
    }
    return best;
  }
  
  public static int pandigitalMultiple( int n ) {
    String pString = ""+n;
    int i = 2;
    while(pString.length() < 9) {
      pString += (n*i++);
    }
    if(pString.length() > 9) {
      return -1;
    }
    boolean[] has = new boolean[9];
    for(i = 0; i < pString.length(); ++i) {
      char c = pString.charAt(i);
      if(c == '0') {
        return -1;
      }
      has[pString.charAt(i)-'0'-1] = true;
    }
    for(boolean curr : has) {
      if(!curr) {
        return -1;
      }
    }
    return Integer.parseInt(pString);
  }
}