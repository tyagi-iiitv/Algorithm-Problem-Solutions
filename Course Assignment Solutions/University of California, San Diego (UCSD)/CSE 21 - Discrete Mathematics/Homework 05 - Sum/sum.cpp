/*
 * Name: Alexander Niema Moshiri
 * Student ID: A09850737
 * Login: cs21ffs
 * Filename: sum.cpp
 *
 * Program to sum an array of ints using two different recursive techniques:
 *   sum1(L) = x                               if x is the only element in L
 *   sum1(L) = x + sum1(L') [where L = x,L']   otherwise
 *
 *   sum2(L) = x                               if x is the only element in L
 *   sum2(L) = sum2(X) + sum2(Y) [where L = (X,Y)]  otherwise
 *             where X and Y are approximately the same size
 *
 * Compile on ieng6: g++ -o sum sum.cpp
 * Run on ieng6: ./sum
 */

#include <iostream>

/*
 * Function prototypes for the two sum functions
 */

long long sum1( int list[], int lo, int hi );
long long sum2( int list[], int lo, int hi );

/*
 * Variables to keep track of the number of addition operations for
 * each sum version. Need to be global for both function and main to access.
 */
 
int sum1Adds;
int sum2Adds;

/*
 * Preconditions:
 *   - list[] is not empty
 *   - lo >= 0
 *   - lo < hi  (lo is inclusive index; hi is exclusive index)
 * 
 * sum1(L) = x                               if x is the only element in L
 * sum1(L) = x + sum1(L') [where L = x,L']   otherwise
 */

long long sum1( int list[], int lo, int hi )
{
  // lo is inclusive index; hi is exclusive index

  // To help you debug your lo and hi values if needed
  // std::cout << lo << " " << hi << std::endl;

/************************************************************************/
// YOU FILL IN THE CODE HERE!!!
/************************************************************************/

  if(lo == hi - 1) // Base Case
  {
    return list[lo];
  }

  else             // Recursive Sum
  {
    sum1Adds++;
    return list[lo] + sum1(list, ++lo, hi);
  }

}

/*
 * Preconditions:
 *   - list[] is not empty
 *   - lo >= 0
 *   - lo < hi  (lo is inclusive index; hi is exclusive index)
 * 
 * sum2(L) = x                               if x is the only element in L
 * sum2(L) = sum2(X) + sum2(Y) [where L = (X,Y)]  otherwise
 *           where X and Y are approximately the same size
 *
 * Calculate a mid point/index based on hi and lo of current segment and
 * use this mid point to divide the segment into left and right halves
 * to recurse on.
 */

long long sum2( int list[], int lo, int hi )
{
  // lo is inclusive index; hi is exclusive index

  // To help you debug your lo and hi values if needed
  // std::cout << lo << " " << hi << std::endl;

/************************************************************************/
// YOU FILL IN THE CODE HERE!!!
/************************************************************************/

  if(lo == hi - 1) // Base Case
  {
    return list[lo];
  }

  else
  {
    int midlow = (lo + hi) / 2;
    sum2Adds++;
    return sum2(list, lo, midlow) + sum2(list, midlow, hi);
  }

}

/*
 * main driver
 *
 * Do no change anything in main()
 */

int main()
{
  /*
   * Test with list1 = { 42 }, then test with longer list2 = { 1, ... , 10 }
   * Then test with longest list3 = { 91, ... , -1 } 
   * list1 is a base case size; list2 is even sized; list3 is odd sized.
   * Turnin with all the list arrays uncommented.
   */

  int list1[] = { 42 }; 

  int list2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; 

  int list3[] = { 91, 28, 73, 46, 55, 67, 87, 80, 109, 1011, 17, 23,
                -420, 13, 37, 42, -99, 58, 70, 66, 44, 21, -1 }; 

  int numOfElements;

  /*
   * Call both sum1 and sum2 on list1
   *
   * lo index is inclusive; hi index is exclusive
   */

  numOfElements = sizeof( list1 ) / sizeof( list1[0] );
  sum1Adds = 0;
  sum2Adds = 0;

  std::cout << "list1 ..." << std::endl;
  std::cout << "sum1 = " << sum1( list1, 0, numOfElements ) << std::endl;
  std::cout << "Number of sum1 adds = " << sum1Adds << std::endl;

  std::cout << "sum2 = " << sum2( list1, 0, numOfElements ) << std::endl;
  std::cout << "Number of sum2 adds = " << sum2Adds << std::endl;

  /*
   * Call both sum1 and sum2 on list2
   *
   * lo index is inclusive; hi index is exclusive
   */

  numOfElements = sizeof( list2 ) / sizeof( list2[0] );
  sum1Adds = 0;
  sum2Adds = 0;

  std::cout << std::endl << "list2 ..." << std::endl;
  std::cout << "sum1 = " << sum1( list2, 0, numOfElements ) << std::endl;
  std::cout << "Number of sum1 adds = " << sum1Adds << std::endl;

  std::cout << "sum2 = " << sum2( list2, 0, numOfElements ) << std::endl;
  std::cout << "Number of sum2 adds = " << sum2Adds << std::endl;

  /*
   * Call both sum1 and sum2 on list3
   *
   * lo index is inclusive; hi index is exclusive
   */

  numOfElements = sizeof( list3 ) / sizeof( list3[0] );
  sum1Adds = 0;
  sum2Adds = 0;

  std::cout << std::endl << "list3 ..." << std::endl;
  std::cout << "sum1 = " << sum1( list3, 0, numOfElements ) << std::endl;
  std::cout << "Number of sum1 adds = " << sum1Adds << std::endl;

  std::cout << "sum2 = " << sum2( list3, 0, numOfElements ) << std::endl;
  std::cout << "Number of sum2 adds = " << sum2Adds << std::endl;

  return 0;
}


