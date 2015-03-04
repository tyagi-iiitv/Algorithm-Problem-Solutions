/*
 * Name: Alexander Niema Moshiri
 * StudentID: A09850737
 * Login: cs21ffs
 *
 * hw16 - Power / Exponentiation (Recursive)
 */

#include <iostream>

#define MAX 512

int power1_Mult_Cnt;    // # of multiplications for power1()
int power2_Mult_Cnt;    // # of multiplications for power2()

/*
 * double power1( double x, int n ) - Recursive
 *
 * Preconditions: n >= 0        - ignoring negative n for now
 *                x != 0
 * Postcondition: power1( x, n ) = x^n
 *
 * Use traditional recursive algorithm in Notes and Exercise 13 in book.
 */

double power1( double x, int n )
{
        /* Fill in your code for power1() here. */
        /* Keep track of the number of multiplications with power1_Mult_Cnt */
  if(n == 0) // x^0 = 1
  {
    return 1;
  }

  else if(n == 1) // x^1 = x
  {
    return x;
  }

  else // If x != 0 or 1, recurse
  {
    power1_Mult_Cnt++;
    return x * power1(x, n-1);
  }

} // end power1()

/*
 * double power2( double x, int n ) - Recursive reducing the number
 *                                 of recursions and multiplications
 *
 * Preconditions: n >= 0        - ignoring negative n for now
 *                x != 0
 * Postcondition: power2( x, n ) = x^n
 *
 * Use the quicker recursive version:
 *  If n is equal to 0, return 1.
 *  If n is even, compute result = power2( x, n/2 ) and return result * result.
 *  If n is odd, return x * power2( x, n - 1 ).
 */

double power2( double x, int n )
{
        /* Fill in your code for power2() here. */
        /* Keep track of the number of multiplications with power2_Mult_Cnt */
  if(n == 0) // x^0 = 1
  {
    return 1;
  }


  else if(n % 2 == 0) // If n is even
  {
    double result = power2(x, n/2);
    power2_Mult_Cnt++;
    return result * result;
  }

  else // If n != 0 and !even (therefore odd)
  {
    power2_Mult_Cnt++;
    return x * power2(x, n-1);
  }

} // end power2()

/*
 * main driver
 */

int main()
{
  double x = 2;

  for ( int n = 0; n <= MAX; ++n )
  {
    power1_Mult_Cnt = 0;
    power2_Mult_Cnt = 0;

    std::cout << "power1(" << x << "," << n << ") = " << power1( x, n );
    std::cout << "\t# of multiplies = " << power1_Mult_Cnt << std::endl;

    std::cout << "power2(" << x << "," << n << ") = " << power2( x, n );
    std::cout << "\t# of multiplies = " << power2_Mult_Cnt << std::endl;
    std::cout << std::endl;
  }
} // end main()