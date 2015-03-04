/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA4 (No Partner)
 * File Name:     boggleplayer.h
 * Description:   This is the overarching Boggle Player class. It is a subclass
 *                of the Base Boggle Player class, which essentially provides
 *                definitions for useful functions. This is the class for any
 *                computer Boggle player. This is the source code file.
 *****************************************************************************/
#include "boggleplayer.h"

/** Public Destructor of BogglePlayer
 *  PRECONDITION:  This is a valid BogglePlayer
 *  POSTCONDITION: This BogglePlayer is destroyed
 */
BogglePlayer::~BogglePlayer()
{
  delete checked;
}

/** This function takes as argument a set containing the words specifying the
 *  official lexicon to be used for the game. Each word in the set will be a
 *  string consisting of lowercase letters a-z only. This function must load
 *  the words into an efficient data structure that will be used internally as
 *  needed by the BogglePlayer.
 *  PRECONDITION:  No Lexicon internally
 *  POSTCONDITION: Lexicon 
 */
void BogglePlayer::buildLexicon( const set<string>& word_list )
{
  /** First save the word_list */
  orig = &word_list;

  /** Add every word in the word_list to the lexicon */
  for(set<string>::iterator it = word_list.begin();it != word_list.end();++it)
  {
    lexicon.addWord(*it);
  }

  /** Lexicon has been built */
  isLex = true;
}

/** This function takes as arguments the number of rows and columns in the
 *  board, and an array of arrays of strings representing what is shown on the
 *  face of dice on a Boggle board. A Boggle board is a rectangular grid of
 *  dice; the height (number of rows) is given by the first argument, the width
 *  of this grid (i.e. number of columns) is given by the second argument.
 */
void BogglePlayer::setBoard( unsigned int rows, unsigned int cols,
                             string** diceArray )
{
  /** Set board, rows, columns, and boolean */
  this->board   = diceArray;
  this->rows    = rows;
  this->cols    = cols;
  this->checked = new vector<bool>(rows*cols,false);
  this->isSet   = true;

  for(unsigned int x = 0; x < cols; ++x)
  {
    for(unsigned int y = 0; y < rows; ++y)
    {
      firsts.at(tolower(board[y][x][0])) = true;
    }
  }
}

/** This function takes two arguments: an integer specifying a minimum word
 *  length, and a pointer to a set of strings. It returns false if either
 *  setBoard() or buildLexicon() have not yet been called for this
 *  BogglePlayer. If they have both been called, it will return true, after
 *  filling the set with all the words that (1) are of at least the given
 *  minimum length, (2) are in the lexicon specified by the most recent call to
 *  buildLexicon(), and (3) can be found by following an acyclic simple path on
 *  the board specified by the most recent call to setBoard().
 */
bool BogglePlayer::getAllValidWords( unsigned int minimum_word_length,
                        set<string>* words )
{
  /** Check for setBoard and buildLexicon */
  if(!isSet || !isLex)
  {
    return false;
  }

  /** For every word in the original word list: */
  for(set<string>::iterator it = orig->begin(); it != orig->end(); ++it)
  {
    string temp = *it;

    /** Check first character */
    if(!firsts.at(tolower(temp[0])))
    {
      continue;
    }

    /** Check size and if it's on the board */
    if(temp.length() >= minimum_word_length && isOnBoard(temp).size() > 0)
    {
      words->insert(temp);
    }
  }

  return true;
}

/** This function takes as argument a const string passed by reference, and
 *  determines whether it be found in the lexicon specified by the most recent
 *  call to buildLexicon(). The function returns true if the word is in the
 *  lexicon, and returns false if it is not in the lexicon or if buildLexicon()
 *  has not yet been called.
 */
bool BogglePlayer::isInLexicon( const string& word_to_check )
{
  /** Will only return true if isLex is true AND word is found */
  return isLex && lexicon.findWord(word_to_check);
}

/** This function takes as argument a string passed by reference. It determines
 *  whether the string can be found by following an acyclic simple path on the
 *  board specified by the most recent call to setBoard(). If it is possible to
 *  find the word in the current board, the function returns vector with
 *  integers specifying the locations of dice that can be used to form the
 *  word, in the order that spells the word.
 */
vector<int> BogglePlayer::isOnBoard( const string& word_to_check )
{
  /** Initialize the vector of ints to use */
  vector<int> vect = vector<int>();

  /** If setBoard() hasn't been called yet, return empty vector */
  if(!isSet || !firsts.at(tolower(word_to_check[0])))
  {
    return vect;
  }

  /** Parse through every x and y of the array for start point */
  for(unsigned int x = 0; x < cols; ++x)
  {
    for(unsigned int y = 0; y < rows; ++y)
    {
      /** Recursively traverse board to find word */
      bool temp = traverseBoard(x,y,word_to_check,vect);

      /** Reset the boolean vector */
      for(unsigned int i = 0; i < checked->size(); ++i)
      {
        checked->at(i) = false;
      }

      /** If successful, return vector */
      if(temp)
      {
        return vect;
      }

      /** If it gets here, this isn't the start node, so reset vector */
      vect.clear();
    }
  }

  /** If it gets here, reset the boolean vector anyways */
  for(unsigned int i = 0; i < checked->size(); ++i)
  {
    checked->at(i) = false;
  }

  return vect;
}

/** This method is called by isOnBoard() and performs a recursive depth-
 *  first traversal of the board to actually perform the search.
 *  PRECONDITION:  Valid string and valid board
 *  POSTCONDITION: Returns true if word is found, and vect is updated
 *                 otherwise false
 */
bool BogglePlayer::traverseBoard( int x, int y, const string& word_to_check,
                                  vector<int>& vect )
{
  /** Convert from x-y coordinate to 0-(n-1) index */
  int index = convert(x,y);

  /** Get this block's string */
  string myString = board[y][x];

  /** Check if this block works, first check size */
  if(myString.length() > word_to_check.length())
  {
    return false;
  }

  /** Now check the characters */
  for(unsigned int i = 0; i < myString.length(); ++i)
  {
    if(tolower(myString.at(i)) != tolower(word_to_check.at(i)))
    {
      return false;
    }
  }

  /** It got here, so it's valid */
  vect.push_back(index);

  /** This word works, so let's make a new substring */
  string sub = word_to_check;
  sub.erase(0,myString.length());

  /** If substring is now empty, job is done! */
  if(sub.empty())
  {
    return true;
  }

  /** This element has now been checked */
  checked->at(index) = true;

  /** Find indices of all surrounding blocks */
  int U  = getU(x,y);
  int D  = getD(x,y);
  int L  = getL(x,y);
  int R  = getR(x,y);
  int UL = getUL(x,y);
  int DL = getDL(x,y);
  int UR = getUR(x,y);
  int DR = getDR(x,y);

  /** Save the current size of the vector */
  unsigned int pre = vect.size();

  /** Traverse Up if valid and not checked */
  if(U != -1 && !(checked->at(U)))
  {
    if(traverseBoard(x,y-1,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Down if valid and not checked */
  if(D != -1 && !(checked->at(D)))
  {
    if(traverseBoard(x,y+1,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Left if valid and not checked */
  if(L != -1 && !(checked->at(L)))
  {
    if(traverseBoard(x-1,y,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Right if valid and not checked */
  if(R != -1 && !(checked->at(R)))
  {
    if(traverseBoard(x+1,y,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Up-Left if valid and not checked */
  if(UL != -1 && !(checked->at(UL)))
  {
    if(traverseBoard(x-1,y-1,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Up-Right if valid and not checked */
  if(UR != -1 && !(checked->at(UR)))
  {
    if(traverseBoard(x+1,y-1,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Down-Left if valid and not checked */
  if(DL != -1 && !(checked->at(DL)))
  {
    if(traverseBoard(x-1,y+1,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** Traverse Down-Right if valid and not checked */
  if(DR != -1 && !(checked->at(DR)))
  {
    if(traverseBoard(x+1,y+1,sub,vect))
    {
      return true;
    }

    /** If unsuccessful, if vector was modified, revert it */
    else if(vect.size() != pre)
    {
      vect.pop_back();
    }
  }

  /** If none of these returned true, tough luck! */
  return false;
}

/** This is a method that will allow you to implement extra functionality,
 *  should you choose to (you may find it very useful for testing). This
 *  function will be called by the GUI application when a choice of "custom",
 *  non-randomly selected board layout is requested. This function should
 *  populate the array of arrays referenced by the first argument, and the
 *  integers pointed to by the second and third argument, with a Boggle board
 *  specification suitable for passing to setBoard(). Note, the first argument,
 *  which will contain the array of arrays that represents the board, will not
 *  be allocated for you; you must allocate the memory for it yourself.
 */
void BogglePlayer::getCustomBoard( string** &new_board, unsigned int *rows,
                                   unsigned int *cols )
{
  *rows = 7;
  *cols = 10;
  new_board = new string*[*rows];
  for(unsigned int i = 0; i < *rows; ++i)
  {
    new_board[i] = new string[*cols];
  }

  new_board[0][0] = "a";
  new_board[0][1] = "b";
  new_board[0][2] = "c";
  new_board[0][3] = "d";
  new_board[0][4] = "e";
  new_board[0][5] = "f";
  new_board[0][6] = "g";
  new_board[0][7] = "h";
  new_board[0][8] = "i";
  new_board[0][9] = "j";

  new_board[1][0] = "k";
  new_board[1][1] = "l";
  new_board[1][2] = "m";
  new_board[1][3] = "n";
  new_board[1][4] = "o";
  new_board[1][5] = "p";
  new_board[1][6] = "q";
  new_board[1][7] = "r";
  new_board[1][8] = "s";
  new_board[1][9] = "t";

  new_board[2][0] = "u";
  new_board[2][1] = "v";
  new_board[2][2] = "w";
  new_board[2][3] = "x";
  new_board[2][4] = "y";
  new_board[2][5] = "z";
  new_board[2][6] = "aa";
  new_board[2][7] = "ab";
  new_board[2][8] = "ac";
  new_board[2][9] = "ad";

  new_board[3][0] = "ae";
  new_board[3][1] = "af";
  new_board[3][2] = "ag";
  new_board[3][3] = "ah";
  new_board[3][4] = "ai";
  new_board[3][5] = "aj";
  new_board[3][6] = "ak";
  new_board[3][7] = "al";
  new_board[3][8] = "am";
  new_board[3][9] = "an";

  new_board[4][0] = "ao";
  new_board[4][1] = "ap";
  new_board[4][2] = "aq";
  new_board[4][3] = "ar";
  new_board[4][4] = "as";
  new_board[4][5] = "at";
  new_board[4][6] = "au";
  new_board[4][7] = "av";
  new_board[4][8] = "aw";
  new_board[4][9] = "ax";

  new_board[5][0] = "ay";
  new_board[5][1] = "az";
  new_board[5][2] = "a";
  new_board[5][3] = "b";
  new_board[5][4] = "c";
  new_board[5][5] = "d";
  new_board[5][6] = "e";
  new_board[5][7] = "f";
  new_board[5][8] = "g";
  new_board[5][9] = "h";

  new_board[6][0] = "i";
  new_board[6][1] = "j";
  new_board[6][2] = "k";
  new_board[6][3] = "l";
  new_board[6][4] = "m";
  new_board[6][5] = "n";
  new_board[6][6] = "o";
  new_board[6][7] = "p";
  new_board[6][8] = "q";
  new_board[6][9] = "r";
}

/** Convert an x-y pair to an int index */
int BogglePlayer::convert( int x, int y )
{
  return cols*y + x;
}

/** Given an x-y pair, return the int position of the up item */
int BogglePlayer::getU( int x, int y )
{
  /** If the position will be invalid, return -1 */
  if(y <= 0)
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*(y-1) + x;
}

/** Given an x-y pair, return the int position of the down item */
int BogglePlayer::getD( int x, unsigned int y )
{
  /** If the position will be invalid, return -1 */
  if(y >= (rows-1))
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*(y+1) + x;
}

/** Given an x-y pair, return the int position of the left item */
int BogglePlayer::getL( int x, int y )
{
  /** If the position will be invalid, return -1 */
  if(x <= 0)
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*y + (x-1);
}

/** Given an x-y pair, return the int position of the right item */
int BogglePlayer::getR( unsigned int x, int y )
{
  /** If the position will be invalid, return -1 */
  if(x >= (cols-1))
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*y + (x+1);
}

/** Given an x-y pair, return the int position of the up-left item */
int BogglePlayer::getUL( int x, int y )
{
  /** If the position will be invalid, return -1 */
  if(x <= 0 || y <= 0)
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*(y-1) + (x-1);
}

/** Given an x-y pair, return the int position of the down-left item */
int BogglePlayer::getDL( int x, unsigned int y )
{
  /** If the position will be invalid, return -1 */
  if(x <= 0 || y >= (rows-1))
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*(y+1) + (x-1);
}

/** Given an x-y pair, return the int position of the up-right item */
int BogglePlayer::getUR( unsigned int x, int y )
{
  /** If the position will be invalid, return -1 */
  if(x >= (cols-1) || y <= 0)
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*(y-1) + (x+1);
}

/** Given an x-y pair, return the int position of the down-right item */
int BogglePlayer::getDR( unsigned int x, unsigned int y )
{
  /** If the position will be invalid, return -1 */
  if(x >= (cols-1) || y >= (rows-1))
  {
    return -1;
  }

  /** Otherwise, do the algorithm (I drew it on paper) */
  return cols*(y+1) + (x+1);
}
