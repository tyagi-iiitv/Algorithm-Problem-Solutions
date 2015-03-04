/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  January 18, 2013
 * File:  TwoSmallest.java
 * Sources of Help: The PA2 instructions posted on Ord's website.
 *
 * This program will have the user type in a series of integers and will
 * return the two smallest integers that were entered. The user can EOF
 * to finish entering the series.
 */

import java.util.Scanner; // Scan input from keyboard

/*
 * Name:    TwoSmallest
 * Purpose: The public class of this program.
 */

public class TwoSmallest
{

  /*
   * Name:    main()
   * Purpose: Ask for user int input and return the 2 smallest ints.
   *          If only 1 int is entered, return only that int.
   *          If no ints are entered, give user an error message.
   */

  public static void main(String [] args)
  {
    int num  =         0; // Entered number holder
    int num1 =         0; // Lowest number
    int num2 =         0; // 2nd lowest number
    int totalEntered = 0; // Total amount of entered numbers

    Scanner input = new Scanner( System.in ); // create scanner

    System.out.println( "Enter a series of integers; EOF to Quit." ); //prompt

    while(input.hasNext())       // while there is more input
    {
      num = input.nextInt();     // read next int
      totalEntered++;            // increment the integer counter
      
      if(totalEntered == 1)      // if only 1 int is entered
      {
        num1 = num;              // set num1 as that int
      }

      else if(totalEntered == 2) // OR if a second int is entered
      {
        if(num < num1)           // if it is less than the first,
        {
          num2 = num1;           // move first int to slot 2
          num1 = num;            // put the new (small) int in slot 1
        }

        else if(num > num1)      // OR if second int is bigger
        {
          num2 = num;            // put in slot 2
        }

        else // OR if second int is equal to first int
        {
          totalEntered--; // don't keep, and undo the increment of counter
        }
      }

      else             // IF more than 2 ints are entered
      {
        if(num < num1) // if entered is less than smallest
        {
          num2 = num1; // move smallest to slot 2
          num1 = num;  // put new one into slot 1
        }
        
        else if(num == num1 || num == num2) // OR if entered equals either
        {
          totalEntered--;   // don't keep, and undo the increment of counter
        }

        else if(num < num2) // OR if in between the two values
        {
          num2 = num;       // put new one in slot 2
        }
      }
    }
    
    if(totalEntered == 0) // IF no numbers entered:
    {
      System.out.println("No numbers entered."); // print error
    }

    else // IF more than 0 numbers are entered:
    {
      System.out.println("Smallest distinct number entered was " + num1);
    }

    if(totalEntered > 1){ // IF there's more than 1 distinct number:
    System.out.println("Second smallest distinct number entered was " + num2);}
  }
}
