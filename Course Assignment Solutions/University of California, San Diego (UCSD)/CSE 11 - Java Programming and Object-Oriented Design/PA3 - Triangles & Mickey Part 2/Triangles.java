/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  January 25, 2013
 * File:  Triangles.java
 * Sources of Help: The PA3 instructions posted on Ord's website.
 *
 * This program displays triangle patterns (using the * character) with size
 * depending on a user-inputted value.
 */

import java.util.Scanner; // Scan input from keyboard

/*
 * Name:    Triangles
 * Purpose: The public class of this program.
 */

public class Triangles
{

  /*
   * Name:    main()
   * Purpose: Create triangle pattern depending on user input.
   */

  private static boolean isTriangle = false; // Triangle Exist Toggle

  private static int r = 0,                  // Row counter
                     p = 0;                  // Print counter

  public static void main( String [] args )
  {
    Scanner input = new Scanner (System.in); // create scanner

    while(!isTriangle) // While there is no triangle:
    {
      // Prompt user
      System.out.print("Enter the size of the triangles to display: ");
      int size = input.nextInt(); // Assign to int

      if(size < 2) // If triangle size isn't valid (< 1)
      {
        // Print error
        System.out.println("Triangle size must be > 1; Try again.");
        System.out.println();     // Line break
      }

      else // If triangle size is valid (> 1)
      {
        while(r < size)  // While you're in the row range
        {
// START TRIANGLES DRAW
          // Print triangle1 stars for this row
          while(p <= r)  // While the number printed is smaller than row number
          {
            System.out.print("*");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter

          // Print triangle1 spaces for this row
          while(p < size - r)
          {
            System.out.print(" ");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter

          // Print triangle2 stars for this row
          while(p < size - r)
          {
            System.out.print("*");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter

          // Print triangle2 spaces for this row
          while(p < r)
          {
            System.out.print(" ");
            ++p;         // Increment counter
          }
          p = 0;         // Restart Counter

          // Print triangle3 spaces for this row
          while(p <= r)
          {
            System.out.print(" ");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter

          // Print triangle3 stars for this row
          while(p < size - r)
          {
            System.out.print("*");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter

          // Print triangle4 spaces for this row
          while(p < size - r)
          {
            System.out.print(" ");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter

          // Print triangle4 stars for this row
          while(p <= r)
          {
            System.out.print("*");
            ++p;         // Increment counter
          }
          p = 0;         // Restart counter
// END TRIANGLES DRAW

          System.out.println(); // Line break
          ++r;           // Increment row counter
        }
      
      isTriangle = true; // There is now a triangle
      }
    }
  }
}
