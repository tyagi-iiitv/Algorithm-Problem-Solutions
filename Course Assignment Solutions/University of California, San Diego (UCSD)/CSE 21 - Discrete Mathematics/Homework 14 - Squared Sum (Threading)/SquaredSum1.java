/*
 * Name: Alexander Niema Moshiri
 * Student ID: A09850737
 * Login: cs21ffs
 * Filename: SquaredSum1.java
 *
 * Program to sum the squares of all the elements of a large array:
 *
 * 1) Sequentially
 *
 * 2) Divide the array (work) into partitions based on number of threads
 *    available on this system and create a separate thread to perform
 *    a sequential result storing each thread's result into an array indexed
 *    by the thread number to avoid concurrency issues. Then add the
 *    results together for the final result.
 *
 * Compile on ieng6/B230 workstations: javac SquaredSum1.java
 * Run on B230 workstations: java -Xms2048m SquaredSum1
 *
 */

import java.util.Random;

public class SquaredSum1 extends Thread
{
  /*
   * Leave this constant as it is.
   */

  private static int ARRAY_SIZE = 1431000003;

  /*
   * Static variable to hold the results from the different threads.
   * Each thread should index into a different element of the array.
   *
   * This is an easy way to avoid concurrency issues.
   */

  private static long[] results =
                 new long[Runtime.getRuntime().availableProcessors() - 1];

  /*
   * Instance variables for each new thread (each new SquaredSum1)
   */

  private int lo;
  private int hi;
  private byte[] arr;
  private int threadNum;  // Which thread number this thread represents

  /*
   * SquaredSum constructor
   */

  public SquaredSum1( byte[] a, int lo, int hi, int threadNum )
  {
/************************************************************************/
// YOU FILL IN THE CODE HERE!!!
/************************************************************************/
    arr     = a;
    this.lo = lo;
    this.hi = hi;
    this.threadNum = threadNum;
    start();

/*
 * start(); needs to be the *last* statement in the ctor to start this
 * thread. The run() method will be automatically called from start().
 */


  } // end SquaredSum() ctor


  /*
   * The run() method is automatically called from start().
   *
   * Similar to the compute() method in the fork-join framework,
   * but run() does not return a value.
   *
   * Just call the sequential algorithm with the appropriate arguments.
   * Do not recurse.
   *
   * Store this thread's result in the static array results[] using
   * this thread's threadNum variable as the index to avoid concurrency
   * issues.
   */

  public void run()
  {
/************************************************************************/
// YOU FILL IN THE CODE HERE!!!
/************************************************************************/
    int square;

    for(int i=lo; i < hi; ++i)
    {
      square = arr[i] * arr[i];
      results[threadNum] += square;
    }

  } // end run()


  /*
   * Sequential method to sum the squares of all the elements of a
   * in the range [lo, hi).
   *
   * This version just uses a loop to sequentially square then
   * add to the running sum. Return the sum of the squares of all the
   * elements in the range of [lo, hi). lo is inclusive; hi is exclusive.
   */

  static long sequentialSquaredSum( byte[] a, int lo, int hi )
  {
/************************************************************************/
// YOU FILL IN THE CODE HERE!!!
/************************************************************************/

    long SquaredSum = 0;

    for(int i=0; i < hi; ++i)
    {
      SquaredSum += a[i] * a[i];
    }
    return SquaredSum;
  } // end sequentialSquaredSum()

  
/************************************************************************/
/************************************************************************/
// DO NOT CHANGE ANYTHING IN MAIN()
/************************************************************************/
/************************************************************************/

  public static void main( String[] args ) throws InterruptedException
  {
    int numOfThreads = Runtime.getRuntime().availableProcessors();

    System.out.println( "Runtime available processors: " +
                         numOfThreads + "\n" );

    byte array[] = new byte[ARRAY_SIZE];

    System.out.println( "Initializing array with random values\n" );
    Random r = new Random( 41 );
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
     * Now divide-and-conquer by dividing up the array into partitions
     * and have each thread sequentially calculate the squared sum
     * of each partition separately in parallel.
     *
     * Note: The last partition needs to be handled differently
     * to pick up any possible extra elements due to integer division.
     */

    System.out.println( "Multi-Threading squared sum" );

    int partitionSize = array.length / numOfThreads;
    Thread[] threads = new Thread[numOfThreads - 1];
    startTime = System.nanoTime();

    /*
     * Create a new SquaredSum1 thread to calculate the squared sum of
     * each of the first numOfThreads-1 partitions.
     */

    int i;
    for ( i = 0; i < numOfThreads - 1; ++i )
      threads[i] =
         new SquaredSum1( array, partitionSize * i, partitionSize * (i+1), i );

    /*
     * Handle the last partition in this current thread.
     *
     * Remember to get all the rest of the elements from the last cut-off
     * point to the end of the array.
     */

    sum = sequentialSquaredSum( array, partitionSize * i, array.length );

    /*
     * Now cycle thru the threads waiting for each to complete [join()]
     * then add in that thread's result.
     */

    for ( i = 0; i < numOfThreads - 1; ++i )
    {
      threads[i].join();          // make sure this thread has completed
      sum = sum + results[i];
    }

    endTime = System.nanoTime();

    long multiThreadTime = endTime - startTime;
    System.out.println( "Squared Sum is: " + sum );
    System.out.println( "Completed in " + multiThreadTime + " nanoSecs\n" );

    System.out.println( "Speed-up: " +
                        (double) seqTime / multiThreadTime  + "\n" );

  } // end main()

} // end class SquaredSum1