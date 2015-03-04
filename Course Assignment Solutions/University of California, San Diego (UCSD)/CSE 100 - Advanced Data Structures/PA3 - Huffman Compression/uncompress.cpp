/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     compress.cpp
 * Description:   This program will read the contents of the file named by its
 *                first command line argument, which should be a file that has
 *                been created by the compress program. It will use the
 *                contents of that file to reconstruct the original,
 *                uncompressed version, which is written to a file named by the
 *                second command line argument. In other words, running
 *                'compress F G' and then 'uncompress G H' should result in
 *                files F and H being identical.
 *
 * Usage:         uncompress infile outfile
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
 *  (input file must be the output of the compress program)
 *  POSTCONDITION: a file that is identical to the one passed into the compress
 *  program
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

  /** Bit Input Stream (for compress input) */
  BitInputStream bitin = BitInputStream(in);

  vector<int> counts(ASCII); /** Vector that will store all ASCII counts */
  HCTree tree = HCTree();    /** HCTree that will be doing the decoding */

  unsigned char ch;          /** Temporary char to hold next input */
  int currentVal = 0;        /** Temporary int to hold current vector value */

  int numUnique  = 0;        /** Keep track of # unique symbols */
  int numOutChar = 0;        /** Keep track of # output characters */
  /******************** END VARIABLE INITIALIZE ********************/

  /******************** BEGIN ARGUMENT PARSE ********************/
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

  /** Check for valid input file */
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

  /********************* END ARGUMENT PARSE *********************/

  /** Parse through header file to read counts vector */
  cout << "Reading header from file \"" << argv[1] << "\"...";

  /** First byte is numUnique */
  ch = in.get();
  if(!in.good())
  {
    cerr << "Input file is empty!!!" << endl;
    exit(1);
  }

  /** 1st byte is # unique chars. Note that max value of 1 byte = 255, so I
   *  subtract 1 from numUnique in compress (the 0 unique case is the empty
   *  file, which I handle separately), and I add 1 when I read it in
   *  uncompress
   */
  numUnique = ((int)ch + 1);

  /** Read header for each unique symbol */
  for(int i = 0; i < numUnique; i++)
  {
    /** First char is symbol */
    ch = in.get();
    unsigned char symbol = ch;

    /** Second char is count */
    int symCount;
    in.read( (char*)&symCount, sizeof(symCount) );

    /** Update count at symbol with the correct count */
    counts.at((int)((unsigned char)symbol)) = symCount;
    numOutChar += symCount;
  }
  cout << " done." << endl;

  /** Create HCTree and populate its leaves*/
  cout << "Building Huffman code tree...";
  tree.build(counts);
  cout << " done." << endl;

  /** Read rest of file and decode */
  cout << "Writing to file \"" << argv[2] << "\"...";

  int i = 0;
  while(i < numOutChar)
  {
    out << (char)tree.decode(bitin);
    i++;
  }
  cout << " done." << endl;

  return 0;
}
