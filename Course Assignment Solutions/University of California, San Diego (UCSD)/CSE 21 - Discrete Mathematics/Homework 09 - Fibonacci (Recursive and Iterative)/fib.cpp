/*
 * Name: Alexander Niema Moshiri
 * Student ID: A09850737
 * Login: cs21sffs
 * Filename: fib.cpp
 *
 * Program to calculate Fibonacci numbers two different ways:
 *   fib_R(0) = 0                           if n is 0
 *   fib_R(1) = 1                           if n is 1
 *   fib_R(n) = fib_R(n-1) + fib_R(n-2)     otherwise
 *     - recursive solution
 *
 *   fib_I(n) = Summation of i = 2 to n : fib_i = fib_i-1 + fib_i-2
 *                                        where fib_0 = 0 , fib_1 = 1
 *     - iterative solution
 *
 * Compile on ieng6: g++ -o fib fib.cpp
 * Run on ieng6: ./sum
 */

#include <iostream>

long long fib_R_Adds = 0;  // Count the # of adds in the Recursive solution
long long fib_I_Adds = 0;  // Count the # of adds in the Iterative solution

/*
 * long long fib_R( int n )
 *
 * Precondition:
 *   - n >= 0
 *
 *   fib_R(0) = 0                           if n is 0
 *   fib_R(1) = 1                           if n is 1
 *   fib_R(n) = fib_R(n-1) + fib_R(n-2)     otherwise
 *     - recursive solution
 */

long long fib_R( int n )
{
  if(n == 0 || n == 1) // Base Case
  {
    return n;
  }

  else                 // All other cases (Recursive)
  {
    fib_R_Adds++;
    return fib_R(n - 2) + fib_R(n - 1);
  }
}

/*
 * long long fib_I( int n )
 *
 * Precondition:
 *   - n >= 0
 *
 *   fib_I(n) = Summation of i = 2 to n : fib_i = fib_i-1 + fib_i-2
 *                                        where fib_0 = 0 , fib_1 = 1
 *     - iterative solution
 */

long long fib_I( int n )
{
  if(n == 0 || n == 1) // Base Case
  {
    return n;
  }

  int fib_minus2 = 0;  // fib_i-2 in instructions
  int fib_minus1 = 1;  // fib_i-1 in instructions
  int fib_i      = 0;  // fib_i   in instructions
  int i = 2;           // Counter "i"

  for(i; i <= n; i++)
  {
    fib_I_Adds++;
    fib_i = fib_minus1 + fib_minus2;
    fib_minus2 = fib_minus1;
    fib_minus1 = fib_i;
  }
  return fib_i;
}

/*
 * main driver - DO NOT CHANGE
 */

int main()
{
  const int MAX = 45;
  long long result;

  std::cout << "Recursive Fib" << std::endl;

  for ( int i = 0; i <= MAX; ++i )
  {
    fib_R_Adds = 0;
    result = fib_R( i );
    std::cout << "fib(" << i << ") = " << result
              << " using " << fib_R_Adds << " additions" << std::endl;
  }

  std::cout << std::endl;

  std::cout << "Iterative Fib" << std::endl;

  for ( int i = 0; i <= MAX; ++i )
  {
    fib_I_Adds = 0;
    result = fib_I( i );
    std::cout << "fib(" << i << ") = " << result
              << " using " << fib_I_Adds << " additions" << std::endl;
  }

  return 0;
}
