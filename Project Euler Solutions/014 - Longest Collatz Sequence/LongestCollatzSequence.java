import java.util.*;
public class LongestCollatzSequence {
  public static void main( String[] args ) {
    int max = 1000000;
    System.out.println(numLongestCollatzSequence(max));
  }
  
  public static long numLongestCollatzSequence( int max ) {
    HashMap<Long,Long> lengths = new HashMap<>();
    lengths.put(1L,1L);
    long best = 1L;
    for(long i = 2L; i <= max; ++i) {
      ArrayList<Long> path = new ArrayList<>();
      long curr = i;
      while(!lengths.containsKey(curr)) {
        path.add(curr);
        if(curr%2L == 0L) {
          curr = curr/2L;
        }
        else {
          curr = 3L*curr + 1L;
        }
      }
      if(curr != i) {
        long l = lengths.get(curr);
        for(int j = path.size()-1; j >= 0; --j) {
          lengths.put(path.get(j),++l);
        }
      }
      if(lengths.get(i) > lengths.get(best)) {
        best = i;
      }
    }
    return best;
  }
}