/*
 * Name: Alexander Niema Moshiri
 * Student ID: A09850737
 * Login: cs21ffs
 * Filename: SquaredSum.java
 *
 * Program to sum the squares of all the elements of a large array:
 *
 * 1) Sequentially
 *
 * 2) Recursive divide-and-conquer using Java's ForkJoin framework
 * http://homes.cs.washington.edu/~djg/teachingMaterials/spac/sophomoricParallelismAndConcurrency.pdf
 *
 *    In particular model your program based on the example on page 21
 *    of the above document.
 *
 * Compile on ieng6/B230 workstations: javac SquaredSum.java
 * Run on B230 workstations: java -Xms2048m SquaredSum
 *
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Random;

/*
 * Our RecursiveTask class that will be the basis of our divide-and-conquer
 * and fork-join multi-threaded processing.
 */

public class SquaredSum extends RecursiveTask<Long>
{
  /*
   * Leave these constants as they are.
   */

  private static int SEQUENTIAL_THRESHOLD = 1024;
  private static int ARRAY_SIZE = 1431000000;

  /*
   * Instance variables for each new subproblem (each new SquaredSum)
   */

  private int lo;
  private int hi;
  private byte[] arr;

  /*
   * SquaredSum constructor
   */

  public SquaredSum( byte[] a, int lo, int hi )
  {
    arr = a;
    this.lo = lo;
    this.hi = hi;
  } // end SquaredSum() ctor

  /*
   * The compute() method does the main computation performed by this task
   * Refer to the example on page 21 in the above doc.
   *
   * Recursively divide the problem into halves to solve.
   * At the sequential cut-off, square each element and add to the running
   * sum and return this sum. Do not store the squared value back into the
   * array!
   */

  public Long compute()
  {
    Long recursiveSquaredSum = new Long(0);

    if ( hi - lo <= SEQUENTIAL_THRESHOLD )
    {
      for(int i = lo; i < hi; ++i)
      {
        recursiveSquaredSum += arr[i] * arr[i];
      }
      return recursiveSquaredSum;
    }
    else
    {
      int mid = (hi - lo)/2 + lo;
      SquaredSum left  = new SquaredSum(arr, lo, mid);
      SquaredSum right = new SquaredSum(arr, mid, hi);
      left.fork();
      Long rightSquaredSum = right.compute();
      Long leftSquaredSum  = left.join();
      recursiveSquaredSum = leftSquaredSum + rightSquaredSum;
      return recursiveSquaredSum;
    }
  } // end compute()


  /*
   * Sequential method to sum the squares of all the elements of a.
   *
   * This version just uses a loop to sequentially square then
   * add to the running sum. Return the sum of the squares of all the
   * elements. Remember - do not store the squared value of each element
   * back into the array!
   */

  static long sequentialSquaredSum( byte[] a, int lo, int hi )
  {
    long SquaredSum = 0;

    for(int i = 0; i < hi; ++i)
    {
      SquaredSum += a[i] * a[i];
    }
    return SquaredSum;
  } // end sequentialSquaredSum()


/************************************************************************/
/************************************************************************/
// DO NOT CHANGE ANYTHING IN MAIN() BELOW!
/************************************************************************/
/************************************************************************/

  public static void main( String[] args )
  {
    /*
     * These are examples of getting the number of processors available.
     */

    System.out.println( "Runtime available processors: " +
                         Runtime.getRuntime().availableProcessors() );

    final ForkJoinPool fjPool = new ForkJoinPool();

    System.out.println( "Fork-Join target parallelism: " +
                         fjPool.getParallelism() + "\n" );

    byte array[] = new byte[ARRAY_SIZE];

    System.out.println( "Initializing array with random values\n" );
    Random r = new Random( 41 );  // A nice prime number seed - close to 42!
    r.nextBytes( array );

    /*
     * First do sequential sum
     */

    System.out.println( "Sequential squared sum" );

    long startTime = System.nanoTime();
    long sum = sequentialSquaredSum( array, 0, array.length );
    long endTime = System.nanoTime();

    long seqTime = endTime - startTime;
    System.out.println( "Squared Sum is: " + sum );
    System.out.println( "Completed in " + seqTime + " nanoSecs\n" );

    /*
     * Now do fork-join divide-and-conquer sum
     */

    System.out.println( "Fork-Join squared sum" );

    startTime = System.nanoTime();
    sum = fjPool.invoke( new SquaredSum( array, 0, array.length ) );
    endTime = System.nanoTime();

    long fork_joinTime = endTime - startTime;
    System.out.println( "Squared Sum is: " + sum );
    System.out.println( "Completed in " + fork_joinTime + " nanoSecs\n" );

    System.out.println( "Speed-up: " +
                        (double) seqTime / fork_joinTime  + "\n" );

  } // end main()

} // end class SquaredSum
