/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     compress.cpp
 * Description:   This program will read the contents of the file named by its
 *                first command line argument (infile), construct a Huffman
 *                code for the contents of that file, and use that code to
 *                construct a compressed version which is written to a file
 *                named by the second command line argument (outfile). The
 *                input file can contain data (not just ASCII characters) so it
 *                should be treated as a binary file. This program will work
 *                for input files up to 10MB in size.
 *
 * Usage:         compress infile outfile
 *****************************************************************************/
#include <iostream>
#include <fstream>
#include <vector>
#include <cstdlib>
#include <string.h>
#include "HCTree.hpp"
#include "HCNode.hpp"

#ifndef ASCII
#define ASCII 256
#endif

#ifndef BYTE
#define BYTE 8
#endif

using namespace std;

/** The main() method of this program. Read a file, use the Huffman algorithm
 *  to compress the file, and output the newly compressed file.
 *  PRECONDITION: needs to be passed in valid arguments and input/output files
 *  must be valid.
 *  POSTCONDITION: a compressed file will be created (that can be uncompressed
 *  to be identical to the original via the uncompress program)
 */
int main(int argc, char* argv[]) {
  /******************** BEGIN VARIABLE INITIALIZE ********************/
  /** Input Stream */
  ifstream in;
  if(argc == 3)
  {
    in.open(argv[1], ios::binary);
  }

  /** Output Stream */
  ofstream out;
  if(argc == 3)
  {
    out.open(argv[2], ios::binary);
  }

  /** Bit Output Stream (for compress output) */
  BitOutputStream bitout = BitOutputStream(out);

  vector<int> counts(ASCII); /** Vector that will store all ASCII counts */
  HCTree tree = HCTree();    /** HCTree that will be doing the encoding */

  unsigned char ch;          /** Temporary char to hold next input */
  bool empty = true;         /** Check for if file is empty */

  int numUnique = 0;         /** Keep track of # unique symbols */
  int numChars  = 0;         /** Keep track of # characters parsed */
  /******************** END VARIABLE INITIALIZE ********************/

  /********************* BEGIN ARGUMENT PARSE **********************/
  /** Ensure 2 arguments */
  if(argc != 3)
  {
    cerr << "Invalid usage: must have 2 arguments." << endl;
    exit(-1);
  }

  /** Check for valid input file */
  if(!in.good())
  {
    cerr << "Invalid input file: " << argv[1] << endl;
    exit(-1);
  }

  /** Check for valid output file */
  if(!out.good())
  {
    cerr << "Invalid output file: " << argv[2] << endl;
    exit(-1);
  }

  /** Check for same file name input and output */
  if(!strcmp(argv[1],argv[2]))
  {
    cerr << "Input and Output file names cannot be the same!" << endl;
    exit(-1);
  }
  /********************** END ARGUMENT PARSE ***********************/

  /** Parse through entire file, keeping track of counts of ASCII characters */
  cout << "Reading from file \"" << argv[1] << "\"...";
  while(1)
  {
    /** Save the next character */
    ch = in.get();

    /** If it's the EOF or something failed, break the loop */
    if(!in.good())
    {
      break;
    }

    /** Increment the count of that character */
    if(counts.at((int)ch) == 0)
    {
      numUnique++;
    }
    counts.at((int)ch)++;
    numChars++;
  }
  cout << " done." << endl;
  if(numChars == 0)
  {
    cerr << "Input file is empty!!!" << endl;
    exit(-1);
  }

  cout << "Found " << numUnique << " unique symbols in input file of size ";
  cout << numChars << " bytes." << endl; /** 1 char = 1 byte */

  /** Create HCTree and populate its leaves*/
  cout << "Building Huffman code tree...";
  tree.build(counts);
  cout << " done." << endl;

  /** Clear error flags and set file get pointer back to beginning */
  in.clear();
  in.seekg(0,ios::beg);

  /** Write file header */
  cout << "Writing to file \"" << argv[2] << "\"...";

  /** 1st byte is # unique chars. Note that max value of 1 byte = 255, so I
   *  subtract 1 from numUnique in compress (the 0 unique case is the empty
   *  file, which I handle separately), and I will add 1 when I read it in
   *  uncompress
   */
  out << (byte)(numUnique-1);
  for(int i = 0; i < counts.size(); i++)
  {
    /** Next print each symbol and its count */
    int temp = counts.at(i);
    if(temp > 0)
    {
      out << (char)i;
      out.write( (char*)&temp, sizeof(temp) );
    }
  }

  /** Parse through entire file, encoding each ASCII character */
  while(1)
  {
    ch = in.get();
    if(!in.good())
    {
      break;
    }
    tree.encode(ch, bitout);
  }

  /** Write the last byte (unfinished buffer) */
  bitout.writeLastBit();

  cout << " done." << endl;

  return 0;
}
