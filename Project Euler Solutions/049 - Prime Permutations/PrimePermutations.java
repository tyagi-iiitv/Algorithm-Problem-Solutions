import java.util.*;
public class PrimePermutations {
  public static void main( String[] args ) {
    System.out.println(primePermutations());
  }
  
  public static String primePermutations() {
    ArrayList<Integer> primes = new ArrayList<>();
    for(int i = 1001; i < 10000; i += 2) {
      if(isPrime(i)) {
        primes.add(i);
      }
    }
    for(int ind1 = 0; ind1 < primes.size() - 1; ++ind1) {
      for(int ind2 = ind1+1; ind2 < primes.size(); ++ind2) {
        int i = primes.get(ind1);
        int j = primes.get(ind2);
        int k = j + j - i;
        if(isPrime(k) && arePermutations(i,j,k)) {
          String temp = Integer.toString(i) + Integer.toString(j) + Integer.toString(k);
          if(!temp.equals("148748178147")) {
            return temp;
          }
        }
      }
    }
    return "NONE FOUND";
  }
  
  public static boolean arePermutations( int first, int second, int third ) {
    String iStr = ""+first;
    String jStr = ""+second;
    String kStr = ""+third;
    int[] iCounts = new int[10];
    for(char c : iStr.toCharArray()) {
      ++iCounts[c-'0'];
    }
    int[] jCounts = new int[10];
    for(char c : jStr.toCharArray()) {
      ++jCounts[c-'0'];
    }
    int[] kCounts = new int[10];
    for(char c : kStr.toCharArray()) {
      ++kCounts[c-'0'];
    }
    return Arrays.equals(iCounts,jCounts) && Arrays.equals(iCounts,kCounts);
  }
  
  public static boolean isPrime( long n ) {
    if(n < 2 || n%2 == 0) {
      return false;
    }
    for(long i = 3; i*i <= n; i += 2) {
      if(n%i == 0) {
        return false;
      }
    }
    return true;
  }
}