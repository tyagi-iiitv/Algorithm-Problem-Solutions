/**
 * @author Alexander Niema Moshiri <cs12smj>
 * @since 2013-04-12
 *
 * This is a timer for our <tt>List12</tt> class and for an <tt>ArrayList</tt>
 * class. The program takes two command line arguments. The first is either
 * <tt>linked</tt> or <tt>array</tt> and specifies whether a <tt>List12</tt> or
 * an <tt>ArrayList</tt> will be used. The second is either <tt>front</tt> or
 * <tt>back</tt>, specifying whether items will be added from the beginning or
 * the end of the list. It will then measure the time it takes to build the
 * list (starting with an empty list, with N ranging from 2,000 to 50,000, in
 * steps of 1,000), and print results to standard output.
 */

import java.util.*;

/**
 * Outer Class: TimeList
 */
public class TimeList
{
  private static final int N_START     = 2000;  // Starting N
  private static final int N_INCREMENT = 1000;  // Increment for N
  private static final int N_MAX       = 50000; // Maximum N

  private static boolean isLinked; // LinkedList(t) or ArrayList(f)?
  private static boolean isFront;  // Add from front(t) or back(f)?

  /**
   * Main Method 
   */
  public static void main( String[] args )
  {
    checkArgs(args);    // Check for valid arguments
    List<Integer> test; // Create Test List Reference

    for(int n = N_START; n <= N_MAX; n += N_INCREMENT)
    {
      int runs = 100000 / n; // Find # of runs
      double sum = 0;
      double sumSquare = 0;
      for(int i = 0; i < runs; i++)
      {
        System.gc();
        long start = System.nanoTime();
        /*** START CALCULATIONS ***/
        if(isLinked)
        {
          test = new List12<Integer>();
        }
        else
        {
          test = new ArrayList<Integer>();
        }

        for(int j = 0; j < n; j++)
        {
          if(isFront)
          {
            test.add(0, new Integer(j));
          }
          else
          {
            test.add(test.size(), new Integer(j));
          }
        }
        /**** END CALCULATIONS ****/
        long end = System.nanoTime();
        double seconds = (end - start) / 1.0e9;

        sum += seconds;
        sumSquare += (seconds * seconds);
      }

      double average    = sum       / runs;
      double avgSquared = sumSquare / runs;
      double stdev = Math.sqrt(avgSquared - (average * average));
      System.out.printf("%5d\t %8f\t %8f\n", n, average, stdev);
    }
  }

  /**
   * This method will check for valid arguments.
   *
   * @param args the program's arguments
   */
  private static void checkArgs( String[] args )
  {
    // Check for Correct # Arguments
    if(args.length != 2)
    {
      System.out.println("Incorrect Arguments. Must have 2 total.");
      System.exit(0);
    }

    // Check Argument 1
    if(args[0].equals("linked"))     // If Using LinkedList
    {
      isLinked = true;
    }
    else if(args[0].equals("array")) // If using ArrayList
    {
      isLinked = false;
    }
    else                             // If incorrect Argument 1
    {
      System.out.println("Incorrect Argument 1. OPTIONS: linked,array");
      System.exit(0);
    }

    // Check Argument 2
    if(args[1].equals("front"))      // If adding from front
    {
      isFront = true;
    }
    else if(args[1].equals("back"))  // If adding from back
    {
      isFront = false;
    }
    else                             // If incorrect Argument 2
    {
      System.out.println("Incorrect Argument 2. OPTIONS: front,back");
      System.exit(0);
    }
  }
} // End of public class TimeList
