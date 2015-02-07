import java.util.*;
public class EvenFibonacciNumbers {
  public static void main( String[] args ) {
    int max = 4000000;
    System.out.println(evenFibNumbs(max));
  }
  
  public static int evenFibNumbs( int max ) {
    ArrayList<Integer> fib = new ArrayList<>();
    int sum = 0;
    fib.add(0);
    fib.add(1);
    while(fib.get(fib.size()-1) <= max) {
      int next = fib.get(fib.size()-1) + fib.get(fib.size()-2);
      fib.add(next);
      if(next%2 == 0) {
        sum += next;
      }
    }
    return sum;
  }
}