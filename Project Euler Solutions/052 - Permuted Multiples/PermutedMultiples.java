public class PermutedMultiples {
  public static void main( String[] args ) {
    System.out.println(permutedMultiples());
  }
  
  public static long permutedMultiples() {
    long start = 1;
    while(true) {
      start *= 10;
      for(long i = start; i < start * 10 / 6; ++i) {
        boolean found = true;
        for(int j = 2; j < 7; ++j) {
          if(!sameDigits(i,i*j)) {
            found = false;
            break;
          }
        }
        if(found) {
          return i;
        }
      }
    }
  }
  
  public static boolean sameDigits( long a, long b ) {
    char[] aArr = Long.toString(a).toCharArray();
    char[] bArr = Long.toString(b).toCharArray();
    if(aArr.length != bArr.length) {
      return false;
    }
    for(int i = 0; i < aArr.length; ++i) {
      boolean valid = false;
      for(int j = 0; j < bArr.length; ++j) {
        if(aArr[i] == bArr[j]) {
          valid = true;
          break;
        }
      }
      if(!valid) {
        return false;
      }
    }
    return true;
  }
}