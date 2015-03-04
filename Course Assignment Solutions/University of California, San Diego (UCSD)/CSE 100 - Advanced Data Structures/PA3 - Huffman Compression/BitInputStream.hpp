/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     BitInputStream.hpp
 * Description:   This class delegates to an object of an existing iostream
 *                class and adds some useful methods for bitwise input. This is
 *                the header file.
 *****************************************************************************/
#include <iostream>
using namespace std;

class BitInputStream {
private:
  char buf;          /** one byte buffer of bits */
  int nbits;         /** how many bits have been read from buf */
  std::istream & in; /** the input stream to use */

public:
  /** Initialize a BitInputStream that will use the given istream for input */
  BitInputStream(std::istream & is) : in(is) {
    buf = 0;   /** clear buffer */
    nbits = 8; /** initialize bit index */ /** FIX THIS!!! */
  }

  /** Fill the buffer from the input 
   *  PRECONDITION: Buffer is empty
   *  POSTCONDITION: Buffer is filled
   */
  void fill();

  /** Read the next bit from the bit buffer.
   *  Fill the buffer from the input stream first if needed.
   *  Return 1 if the bit read is 1;
   *  Return 0 if the bit read is 0;
   */
  int readBit();
};
