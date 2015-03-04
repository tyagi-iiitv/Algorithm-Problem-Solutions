/*
 * Name: Alexander Niema Moshiri
 * Student ID: A09850737
 * Login: cs21ffs
 * Filename: bubble_sort.cpp
 *
 * Bubble Sort with Early Out implementation
 *
 * Compile on ieng6.ucsd.edu: g++ -o bubble_sort bubble_sort.cpp
 * Run on ieng6.ucsd.edu: ./bubble_sort
 */

#include <iostream>
#include <stdlib.h>

int numOfComparisons = 0;
int numOfSwaps = 0;

/*
 * Bubble Sort algorithm implementing an Early-Out scheme
 * and other enhancements to minimize the number of comparisons
 * to fully sort an array. Do not implement another sorting algorithm.
 *
 * Precondition:
 *   - n (number of elements in array) > 1
 */

void bubble_sort( int array[], int n )
{
  bool noSwap = false;
  bool duplicate = false;

  for(int i = 0; i < n-1 && !noSwap; ++i)
  {
    noSwap = true;

    for(int j = 0; j < n - 1 - i; ++j)
    {
      numOfComparisons++;
      if(array[j] > array[j+1])
      {
        numOfSwaps++;
        int swap = array[j];
        array[j] = array[j+1];
        array[j+1] = swap;
        noSwap = false;
      }
    }
  }
} // end bubble_sort()

/*
 * main driver - DO NOT CHANGE
 */

int main()
{
  const int SIZE = 16;
  int array[SIZE];

  /*
   * Random seed value - all runs will have same pseudo-random values
   */

  srand( 37 );

  /*
   * Initialize array with random values between 0 and 99
   */

  for ( int i = 0; i < SIZE; ++i )
  {
    array[i] = rand() % 100;
  }

  int numOfElements = sizeof( array ) / sizeof( array[0] );

  std::cout << "Array before sort:";
  for ( int i = 0; i < numOfElements; ++i )
  {
    std::cout << " " << array[i];
  }
  std::cout << std::endl;

  /*
   * Call our bubble sort algorithm
   */

  bubble_sort( array, numOfElements );

  std::cout << "Array after sort:";
  for ( int i = 0; i < numOfElements; ++i )
  {
    std::cout << " " << array[i];
  }

  std::cout << std::endl << std::endl;

  std::cout << "Traditional bubble sort would have C("
            << numOfElements << ",2) = "
            << numOfElements * (numOfElements - 1) / 2
            << " comparisons" << std::endl << std::endl;

  std::cout << "But your bubble sort with early out had ..." << std::endl;
  std::cout << "Number of comparisons = " << numOfComparisons << std::endl;
  std::cout << std::endl;

  if ( numOfComparisons < numOfElements * (numOfElements - 1) / 2 )
  {
    std::cout << "You did better - Congratulations!" << std::endl;
  }
  else
  {
    std::cout << "Hey!!! You are supposed to do better!";
    std::cout << " Come on - try again." << std::endl;
  }
  std::cout << std::endl;

  std::cout << "Number of swaps = " << numOfSwaps << std::endl;

  return 0;
} // end main()