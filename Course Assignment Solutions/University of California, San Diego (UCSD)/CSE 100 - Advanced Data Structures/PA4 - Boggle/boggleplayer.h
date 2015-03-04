/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA4 (No Partner)
 * File Name:     boggleplayer.h
 * Description:   This is the overarching Boggle Player class. It is a subclass
 *                of the Base Boggle Player class, which essentially provides
 *                definitions for useful functions. This is the class for any
 *                computer Boggle player. This is the header file.
 *****************************************************************************/
#ifndef BOGGLEPLAYER_H
#define BOGGLEPLAYER_H
#include "baseboggleplayer.h"
#include "boggleutil.h"

class BogglePlayer : public BaseBogglePlayer {
private:
  /************************* INITIALIZE VARIABLES *************************/
  string** board;          /** The Boggle Board */
  vector<bool>* checked;   /** Mirrored bool board to keep track of checked */
  NiemaTrie lexicon;       /** The Lexicon of Words */
  const set<string>* orig; /** A saved pointer to the original set of words */
  unsigned int rows;       /** The number of rows of the Boggle Board */
  unsigned int cols;       /** The number of columns of the Boggle Board */
  bool isSet = false;      /** Has the board been set? */
  bool isLex = false;      /** Has the lexicon been built? */
  vector<bool> firsts = vector<bool>(256); /** Does letter n show up first? */
  /************************* INITIALIZE VARIABLES *************************/

  /** This method is called by isOnBoard() and performs a recursive depth-
   *  first traversal of the board to actually perform the search.
   */
  bool traverseBoard( int x, int y, const string& word_to_check,
                      vector<int>& vect );

  /** Convert an x-y pair to an int index */
  int convert( int x, int y );

  /** Given an x-y pair, return the int position of the up item */
  int getU( int x, int y );

  /** Given an x-y pair, return the int position of the down item */
  int getD( int x, unsigned int y );

  /** Given an x-y pair, return the int position of the left item */
  int getL( int x, int y );

  /** Given an x-y pair, return the int position of the right item */
  int getR( unsigned int x, int y );

  /** Given an x-y pair, return the int position of the up-left item */
  int getUL( int x, int y );

  /** Given an x-y pair, return the int position of the down-left item */
  int getDL( int x, unsigned int y );

  /** Given an x-y pair, return the int position of the up-right item */
  int getUR( unsigned int x, int y );

  /** Given an x-y pair, return the int position of the down-right item */
  int getDR( unsigned int x, unsigned int y );

public:
  /** Public Default Constructor
   *  PRECONDITION:  Nothing
   *  POSTCONDITION: The new BogglePlayer is created
   */
  explicit BogglePlayer(){}

  /** Public Destructor of BogglePlayer
   *  PRECONDITION:  This is a valid BogglePlayer
   *  POSTCONDITION: This BogglePlayer is destroyed
   */
  ~BogglePlayer();

  /** This function takes as argument a set containing the words specifying the
   *  official lexicon to be used for the game. Each word in the set will be a
   *  string consisting of lowercase letters a-z only. This function must load
   *  the words into an efficient data structure that will be used internally as
   *  needed by the BogglePlayer.
   */
  void buildLexicon( const set<string>& word_list );

  /** This function takes as arguments the number of rows and columns in the
   *  board, and an array of arrays of strings representing what is shown on the
   *  face of dice on a Boggle board. A Boggle board is a rectangular grid of
   *  dice; the height (number of rows) is given by the first argument, the
   *  width of this grid (i.e. number of columns) is given by the second
   *  argument.
   */
  void setBoard( unsigned int rows, unsigned int cols, string** diceArray );

  /** This function takes two arguments: an integer specifying a minimum word
   *  length, and a pointer to a set of strings. It returns false if either
   *  setBoard() or buildLexicon() have not yet been called for this
   *  BogglePlayer. If they have both been called, it will return true, after
   *  filling the set with all the words that (1) are of at least the given
   *  minimum length, (2) are in the lexicon specified by the most recent call
   *  to buildLexicon(), and (3) can be found by following an acyclic simple
   *  path on the board specified by the most recent call to setBoard().
   */
  bool getAllValidWords(unsigned int minimum_word_length, set<string>* words);

  /** This function takes as argument a const string passed by reference, and
   *  determines whether it be found in the lexicon specified by the most
   *  recent call to buildLexicon(). The function returns true if the word is
   *  in the lexicon, and returns false if it is not in the lexicon or if
   *  buildLexicon() has not yet been called.
   */
  bool isInLexicon( const string& word_to_check );

  /** This function takes as argument a string passed by reference. It
   *  determine whether the string can be found by following an acyclic simple
   *  path on the board specified by the most recent call to setBoard(). If it
   *  is possible to find the word in the current board, the function returns
   *  vector with integers specifying the locations of dice that can be used to
   *  form the word, in the order that spells the word.
   */
  vector<int> isOnBoard( const string& word_to_check );

  /** This is a method that will allow you to implement extra functionality,
   *  should you choose to (you may find it very useful for testing). This
   *  function will be called by the GUI application when a choice of "custom",
   *  non-randomly selected board layout is requested. This function should
   *  populate the array of arrays referenced by the first argument, and the
   *  integers pointed to by the second and third argument, with a Boggle board
   *  specification suitable for passing to setBoard(). Note, the first
   *  argument, which will contain the array of arrays that represents the
   *  board, will not be allocated for you; you must allocate the memory for it
   *  yourself.
   */
  void getCustomBoard( string** &new_board, unsigned int *rows,
                       unsigned int *cols );
};
#endif // BOGGLEPLAYER_H
