/*
 * Name: Alexander Niema Moshiri
 * Login: cs11wdt
 * Date: February 22, 2013
 * File: ReverseRecurse.java
 * Sources of Help: The PA7 instructions posted on Ord's website.
 *
 * This program reads integer values from the keyboard into an array whose
 * size is specified by the user (user may enter less integers than the size
 * specified, but not more) and reverse the elements in the array via two
 * different recursive methods. One method directly modifies the original
 * array. The other method returns a new array with the elements reversed
 * preserving the original array. Both will use an "ends-and-middle" or
 * "edges-and-center" recursion.
 */

import java.util.Scanner;                 // Scan input from keyboard

/*
 * Name:    ReverseRecurse
 * Purpose: The public class that runs the entire java program.
 */

public class ReverseRecurse
{
  /*
   * Name:       reverse
   * Purpose:    Given an array of integers, reverse it recursively by swapping
   *             the outermost items, then going inward until you hit the
   *             center.
   * Parameters: array: An array of integers (int[])
   *             lo:    The lower index to be swapped
   *             hi:    The higher index to be swapped
   * Return:     void
   */

  public void reverse( int[] array, int lo, int hi )
  {
    int swap;                             // Create Swap Space

    if(lo != hi)                          // If not at the center of odd array,
    {
      // Swap lo and hi
      swap = array[lo];
      array[lo] = array[hi];
      array[hi] = swap;

      if(hi != lo + 1)                    // If not at center of even array,
      {
        reverse(array, lo+1, hi-1);       // Recurse inwards
      }
    }
  }

  /*
   * Name:       reverse
   * Purpose:    
   * Parameters: array: An array of integers (int[])
   * Return:     int[]: The reversed array
   */

  public int[] reverse( int[] array )
  {
    int[] reversed;                       // Create Reversed Array

    if(array.length == 1)                 // If at center of odd array (1 item)
    {
      reversed = array;                   // The reversed is that item
    }

    else                                  // If not in center of odd array,
    {
      reversed = new int[array.length];

      // Set 1st element of array in last spot of "reversed," and last
      // element of array into 1st spot of "reversed"
      reversed[array.length-1] = array[0];
      reversed[0] = array[array.length-1];

      if(array.length != 2)               // If not in center of even array,
      {
        int[] recurse = new int[array.length-2]; // Make array 2 items smaller
        System.arraycopy(array,1,recurse,0,array.length-2); // Copy inners

        System.arraycopy(reverse(recurse),0,reversed,1,array.length-2);
      }
    }

    return reversed;
  }

  /*
   * Name:       printArray
   * Purpose:    Print each item of the parameter array, separated by a space.
   * Parameters: array: An array of integers (int[])
   * Return:     void
   */

  public void printArray( int[] array )
  {
    boolean empty       = false;          // Is the array empty (all 0)?
    int     zeroCounter = 0;              // How many zeroes?

    for(int i = 0; i < array.length; ++i)
    {
      if(array[i] == 0)                   // For each element that == 0,
      {
        zeroCounter++;                    // Increment the Zero Counter
      }
    }

    if(zeroCounter == array.length)       // If all elements are zero,
    {
      System.out.println("Empty array");
    }
    else                                  // If not empty,
    {
      for(int i = 0; i < array.length; ++i)
      {
        System.out.print(array[i] + " "); // Print each character + space
      }
    }

    System.out.println();                 // Print new line
    System.out.println();                 // Print new line
  }

  /*
   * Name:       initArray
   * Purpose:    Ask user for max # of ints expected, create an array of ints
   *             of that size, read at most that many integers from the
   *             keyboard using a Scanner object (ignore extra input beyond the
   *             size of the array), and return the initialize array. The user
   *             must enter a positive integer >0 for the size of the array.
   * Parameters: N/A
   */

  public int[] initArray()
  {
    Scanner in = new Scanner (System.in); // Create Scanner

    int     arraySize   = 0;              // Desired size of array "a"
    int     numCounter  = 0;              // Counter for # of numbers inputted
    Integer inputHolder = 0;              // Holder for integer input

    while(arraySize <= 0)                 // Keep asking until valid int
    {
      System.out.print("Maximum number of integers you wish to enter? ");

      try
      {
        arraySize = in.nextInt();
      }
      catch(Exception e)                  // If there is non-int input:
      {
        System.exit(1);                   // Exit the program
      }

      if(arraySize <= 0)
      {
        System.out.println("You must enter a value > 0; Try again.");
        System.out.println();
      }
    }
    int[] a = new int[arraySize];

    System.out.println("Enter up to " + arraySize + " integers:");

    while(in.hasNext())                   // While there is more input,
    {
      if(in.hasNextInt())                 // If there is more int input,
      {
        a[numCounter] = in.nextInt();
        numCounter++;
      }
      else
      {
        break;                            // Break out of loop
      }

      if(numCounter == arraySize)         // If array is full,
      {
        break;                            // stop allowing number input
      }
    }

    int[] finalArray;

    if(numCounter == 0)                   // If no numbers were entered,
    {
      finalArray = new int[1];            // Set array to simple empty array
    }
    else
    {
      finalArray = new int[numCounter];   // Create new trimmed array
      System.arraycopy(a, 0, finalArray, 0, numCounter); // Copy values
    }

    return finalArray;
  }
}
