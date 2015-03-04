/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     BitOutputStream.hpp
 * Description:   This class delegates to an object of an existing iostream
 *                class and adds some useful methods for bitwise output. This
 *                is the header file.
 *****************************************************************************/
#include <iostream>
using namespace std;

class BitOutputStream {
private:
  char buf;           /** one byte buffer of bits */
  int nbits;          /** how many bits have been written to buf */
  std::ostream & out; /** the output stream to use */

public:
  /** Initialize a BitOutputStream that will use given ostream for output */
  BitOutputStream(std::ostream & os) : out(os) {
    buf = nbits = 0; /** clear buffer and bit counter */
  }

  /** Send the buffer to the output, and clear it
   *  PRECONDITION: Buffer is full
   *  POSTCONDITION: Buffer is empty, and output is updated
   */
  void flush();

  /** Write the least significant bit of the argument to the bit buffer, and
   *  increement the bit buffer index. But flush the buffer first, if it is
   *  full.
   *  PRECONDITION: Take in either 0 or 1
   *  POSTCONDITION: Write that bit into the buffer
   */
  void writeBit(int i);

  /** Write the buffer and nbits (used for last byte written)
   *  PRECONDITION: Unfinished buffer
   *  POSTCONDITION: Buffer is left-aligned and printed
   */
  void writeLastBit();
};
