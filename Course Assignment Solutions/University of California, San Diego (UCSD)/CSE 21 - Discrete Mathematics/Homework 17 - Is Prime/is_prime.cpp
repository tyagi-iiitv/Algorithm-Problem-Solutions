/*
 * Name:  Alexander Niema Moshiri
 * PID:   A09850737
 * Login: cs21ffs
 *
 * Compile with a C++11 compiler ( /software/common/gcc/bin/g++ ):
 *   g++ -O3 -std=c++11 -lpthread -o is_prime is_prime.cpp
 *
 * Run:
 *   ./is_prime
 *
 * This is an example using C++11 async()/get() multi-threading
 */

#include <math.h>
#include <iostream>
#include <thread>
#include <future>
#include <sys/time.h>

/*
 * Here are 3 large primes.
 */

long long num1 = 489133282872437279LL;
long long num2 = 790738119649411319LL;
long long num3 = 6312646216567629137LL;

/*
 * Be sure to only turn in using num3.
 */

long long num = num3;

/*
 * isPrime()
 *
 * Params: n is the value we are checking to determine if it is prime or not.
 *         lo is the low (inclusive) range of possible factors to check.
 *         hi is the high (exclusive) range of possible factors to check.
 *
 * Algorithm:
 *   if n <= 1 return false (we won't deal with values less than 2)
 *
 *   if n == 2 return true (2 is the first prime)
 *
 *   if n is even return false (quickly eliminate; evenly divisible by 2)
 *
 *   if lo < 3 set lo = 3 (want to start factors at 3 as lowest factor)
 *       (this helps so we don't have a special case for the
 *        1st parallel partition.)
 *
 *   if lo is even increment lo by 1 to start at an odd factor
 *       (this could be due to the integer divide for the thread partitions)
 *       (and we already eliminated any even factors above)
 *
 *   loop thru the range [lo, hi) as factors incrementing by 2 each time
 *       checking if n is evenly divisible by each factor. Remember we are
 *       starting at an odd factor for lo. We do not need to check any even
 *       factors.
 *       If it is evenly divisible, return false immediately.
 *
 *   If you make it thru the range of factor and have not returned false yet,
 *   return true.
 *
 *   There may be other more efficient isPrime algorithms, but this will do.
 *   We want to see how multi-threading in C++11 with async()/get()
 *   might improve overall performance.
 */

bool isPrime( long long n, long long lo, long long hi )
{
/********************
 * Put your code here
 ********************/

  bool stopCheck = false;
  bool TF = false;

  if(n < 2 || n % 2 == 0)
  {
    TF = false;
  }

  else
  {
    if(lo < 3)
    {
      lo = 3;
    }

    else if(lo % 2 == 0)
    {
      lo++;
    }

    else
    {
      for(lo; lo < hi; ++lo && stopCheck == false)
      {
        if(n % lo == 0)
        {
          TF = false;
          stopCheck = true;
        }

        else
        {
          TF = true;
          lo = lo + 2;
        }
      }
    }
  }
  return TF;
}


/********************************/
// MAIN - DO NOT CHANGE.
/********************************/

int main()
{
  struct timeval t0, t1;

  int numOfThreads = std::thread::hardware_concurrency();

  std::cout << "Number of threads = " << numOfThreads << std::endl << std::endl;

  /*
   * Only need to check factors up to the square root of the number.
   */

  long long max = (long long) (sqrt( num ) + 1);

  /*
   * First do sequential isPrime
   */

  std::cout << "Sequential isPrime" << std::endl;

  gettimeofday( &t0, NULL ); // start

  bool answer = isPrime( num, 3, max );

  gettimeofday( &t1, NULL ); // finish

  double seqTime = t1.tv_sec - t0.tv_sec + (t1.tv_usec - t0.tv_usec)/1000000.0;
  std::cout << num << (answer == true ? " is " : " is not ")
            << "prime" << std::endl;
  std::cout << "Completed in " << seqTime << " sec" << std::endl << std::endl;

  /*
   * Now do async-get C++11 multi-threading
   */

  std::cout << "Async-get parallel isPrime" << std::endl;

  long long partitionSize = max / numOfThreads;

  /*
   * results[] holds the future<bool> values from the different async threads
   *           We have to get() [join] each thread
   *
   * answers[] holds the actual bool value returned in each future<bool>
   *           Makes it a little easier to iterate over this to check if any
   *           threads found the number not to be prime.
   */

  std::future<bool> results[numOfThreads - 1];
  bool answers[numOfThreads];

  gettimeofday( &t0, NULL ); // start parallel part

  /*
   * Map each of the partitions to a separate thread with async()
   * (except the last partition).
   */

  int i;
  for ( i = 0; i < numOfThreads - 1; ++i )
  {
    results[i] = std::async( std::launch::async, isPrime,
                             num, partitionSize * i, partitionSize * (i+1) );
  }

  /*
   * Run the last partition in this current thread.
   *
   * Be sure to include any extra elements due to integer divide.
   * Hi (exclusive) value needs to be max and not partitionSize * (i+1).
   */

  answers[i] = isPrime( num, partitionSize * i, max );

  /*
   * We have to join/wait on all the threads with get()
   * This is a reduction - reducing all the separate thread results into
   * a single answer.
   */

  for ( i = 0; i < numOfThreads - 1; ++i )
  {
    answers[i] = results[i].get();
  }

  /*
   * Now see if any thread found num not to be prime.
   */

  answer = true;

  for ( i = 0; i < numOfThreads; ++i )
  {
    if ( answers[i] == false )
    {
      answer = false;
      break;
    }
  }

  gettimeofday( &t1, NULL ); // finish - have to include reduction work

  double parallelTime = t1.tv_sec - t0.tv_sec +
                        (t1.tv_usec - t0.tv_usec)/1000000.0;
  std::cout << num << (answer == true ? " is " : " is not ")
            << "prime" << std::endl;
  std::cout << "Completed in " << parallelTime << " sec" << std::endl << std::endl;

  std::cout << "Speed-up: " << (double) seqTime / parallelTime << std::endl << std::endl;

  return 0;
}