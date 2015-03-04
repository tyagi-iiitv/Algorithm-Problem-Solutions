/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     BitOutputStream.cpp
 * Description:   This class delegates to an object of an existing iostream
 *                class and adds some useful methods for bitwise output. This
 *                is the actual class code.
 *****************************************************************************/
#include "BitOutputStream.hpp"

/** Send the buffer to the output, and clear it
 *  PRECONDITION: Buffer is full
 *  POSTCONDITION: Buffer is empty, and output is updated
 */
void BitOutputStream::flush() {
  BitOutputStream::out.put(buf);
  BitOutputStream::out.flush();
  BitOutputStream::buf = BitOutputStream::nbits = 0;
}

/** Write the least significant bit of the argument to the bit buffer, and
 *  increement the bit buffer index. But flush the buffer first, if it is
 *  full.
 *  PRECONDITION: Take in either 0 or 1
 *  POSTCONDITION: Write that bit into the buffer
 */
void BitOutputStream::writeBit(int i) {
  /** Is the bit buffer full? Then flush it */
  if(nbits == 8)
  {
    flush();
  }

  /** Write least significant bit of i into the buffer at current index
   *  0: 00000000 and 1: 00000001
   */
  buf = buf << 1;      /** Bit Shift buffer left 1 space */
  buf = buf | i; /** Save i into rightmost slot */

  /** Increment the index */
  nbits++;
}

/** Write the buffer and nbits (used for last byte written)
 *  PRECONDITION: Unfinished buffer
 *  POSTCONDITION: Buffer is left-aligned and printed
 */
void BitOutputStream::writeLastBit() {
  /** Bit Shift left (8-n) to have buffer left aligned */
  if(nbits > 0)
  {
    buf = buf << (8-nbits);
  }

  /** Flush the buffer */
  flush();
}
