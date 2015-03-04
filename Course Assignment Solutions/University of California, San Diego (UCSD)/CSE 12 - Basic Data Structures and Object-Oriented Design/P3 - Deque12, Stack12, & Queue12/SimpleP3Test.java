/**
 * Do a couple of simple tests on Deque12, Stack12, Queue12
 */
public class SimpleP3Test {

  public static void main(String args[]) {
    BoundedDeque<Integer> a =  new Deque12<Integer>(30);
    if(a.capacity() != 30) {
      System.err.println("Deque12: problem with capacity().");
      System.exit(-1);
    }

    BoundedQueue<Integer> q =  new Queue12<Integer>(30);
    if(q.capacity() != 30) {
      System.err.println("Queue12: problem with capacity().");
      System.exit(-1);
    }

    BoundedStack<Integer> s =  new Stack12<Integer>(30);
    if(s.capacity() != 30) {
      System.err.println("Stack12: problem with capacity().");
      System.exit(-1);
    }

    System.exit(0);

  }

}
