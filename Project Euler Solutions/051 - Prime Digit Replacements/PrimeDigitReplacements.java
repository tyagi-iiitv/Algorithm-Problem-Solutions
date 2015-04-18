public class PrimeDigitReplacements {
  public static void main( String[] args ) {
    System.out.println(primeDigitReplacements());
  }
  
  public static int primeDigitReplacements() {
    int[][] five = fiveDigitPatterns();
    int[][] six = sixDigitPatterns();
    int result = Integer.MAX_VALUE;
    for(int i = 11; i < 1000; i += 2) {
      if(i%5 == 0) {
        continue;
      }
      int[][] pats = null;
      if(i < 100) {
        pats = five;
      }
      else {
        pats = six;
      }
      for(int j = 0; j < pats.length; ++j) {
        for(int k = 0; k < 3; ++k) {
          if(pats[j][0] == 0 && k == 0) {
            continue;
          }
          int[] pat = fillPattern(pats[j],i);
          int cand = generateNumber(k,pat);
          if(cand < result && isPrime(cand)) {
            if(familySize(k,pat) == 8) {
              result = cand;
              break;
            }
          }
        }
      }
    }
    return result;
  }

  public static int[][] fiveDigitPatterns() {
    int[][] out = new int[4][];
    out[0] = new int[]{1,0,0,0,1};
    out[1] = new int[]{0,1,0,0,1};
    out[2] = new int[]{0,0,1,0,1};
    out[3] = new int[]{0,0,0,1,1};
    return out;
  }
  
  public static int[][] sixDigitPatterns() {
    int[][] out = new int[10][];
    out[0] = new int[]{1,1,0,0,0,1};
    out[1] = new int[]{1,0,1,0,0,1};
    out[2] = new int[]{1,0,0,1,0,1};
    out[3] = new int[]{1,0,0,0,1,1};
    out[4] = new int[]{0,1,1,0,0,1};
    out[5] = new int[]{0,1,0,1,0,1};
    out[6] = new int[]{0,1,0,0,1,1};
    out[7] = new int[]{0,0,1,1,0,1};
    out[8] = new int[]{0,0,1,0,1,1};
    out[9] = new int[]{0,0,0,1,1,1};
    return out;
  }
  
  public static int[] fillPattern( int[] pattern, int n ) {
    int[] out = new int[pattern.length];
    int temp = n;
    for(int i = out.length-1; i >= 0; --i) {
      if(pattern[i] == 1) {
        out[i] = temp%10;
        temp /= 10;
      }
      else {
        out[i] = -1;
      }
    }
    return out;
  }
  
  public static int generateNumber( int rep, int[] filled ) {
    int temp = 0;
    for(int i = 0; i < filled.length; ++i) {
      temp *= 10;
      if(filled[i] == -1) {
        temp += rep;
      }
      else {
        temp += filled[i];
      }
    }
    return temp;
  }
  
  public static int familySize( int repeat, int[] pattern ) {
    int fam = 1;
    for(int i = repeat + 1; i < 10; ++i) {
      if(isPrime(generateNumber(i,pattern))) {
        ++fam;
      }
    }
    return fam;
  }
  
  public static boolean isPrime( int n ) {
    if(n < 2 || n%2 == 0) {
      return false;
    }
    for(int i = 3; i*i <= n; i += 2) {
      if(n%i == 0) {
        return false;
      }
    }
    return true;
  }
}

