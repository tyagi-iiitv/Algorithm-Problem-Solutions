/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     BitInputStream.hpp
 * Description:   This class delegates to an object of an existing iostream
 *                class and adds some useful methods for bitwise input. This is
 *                the actual class code.
 *****************************************************************************/
#include "BitInputStream.hpp"

/** Fill the buffer from the input 
 *  PRECONDITION: Buffer is empty
 *  POSTCONDITION: Buffer is filled
 */
void BitInputStream::fill() {
  buf = in.get();

  /** If done reading input, do something that will be able to be checked */
  if(!in.good())
  {
    nbits = -1;
  }
  else
  {
    nbits = 0;
  }
}

/** Read the next bit from the bit buffer.
 *  Fill the buffer from the input stream first if needed.
 *  Return 1 if the bit read is 1;
 *  Return 0 if the bit read is 0;
 *  Return -1 if no more bits to read
 */
int BitInputStream::readBit() {
  /** If all bits in the buffer are read, fill the buffer first */
  if(nbits == 8)
  {
    fill();
  }

  /** If input finished reading, return -1 */
  if(nbits == -1)
  {
    return -1;
  }

  /** Get the bit at the appropriate location in the bit buffer, and return
   *  the appropriate int */
  int leftmost = buf & 128; /** 128 == 10000000 */
  leftmost = leftmost >> 7; /** Shift right 7 to get 0 or 1*/
  buf = buf << 1;           /** Bit Shift buffer left 1 space */

  /** Increment the index */
  nbits++;

  return leftmost;
}
