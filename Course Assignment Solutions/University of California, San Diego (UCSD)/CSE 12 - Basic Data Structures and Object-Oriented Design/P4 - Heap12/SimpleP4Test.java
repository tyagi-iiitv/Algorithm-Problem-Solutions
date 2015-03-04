/**
 * Do simple tests on Heap12
 */
public class SimpleP4Test {

  private static class MyBigInteger extends java.math.BigInteger {
    MyBigInteger() {super("0");}
    MyBigInteger(int i) {super(""+i);}
  }
  
  public static void main(String args[]) {

    PQueue<MyBigInteger> a =  new Heap12<MyBigInteger>();
    if(a.size() != 0) {
      System.err.println("Heap12: problem with initial size().");
      System.exit(-1);
    }

    MyBigInteger[] arr = {new MyBigInteger(1), new MyBigInteger(0)};
    Heap12.sort(arr);
    if( arr[0].compareTo(arr[1]) >= 0) {
      System.err.println("Heap12: problem with sort().");
      System.exit(-1);
    }

    System.exit(0);

  }

}
