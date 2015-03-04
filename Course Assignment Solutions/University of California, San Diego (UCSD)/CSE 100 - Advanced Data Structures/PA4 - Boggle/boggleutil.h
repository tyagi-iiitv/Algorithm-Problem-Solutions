/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA4 (No Partner)
 * File Name:     boggleutil.h
 * Description:   This is the Boggle Utility file. It contains all supporting
 *                methods for BogglePlayer. This is the header file.
 *****************************************************************************/
#ifndef BOGGLEUTIL_H
#define BOGGLEUTIL_H
#include <cstddef>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

/** This is the NiemaNode class. It will be used to create a NiemaTrie, a data
 *  structure that is (hopefully) more efficient at finding words than a set.
 */
class NiemaNode {
private:
  char letter;                 /** This node's letter */
  bool isEnd;                  /** Is this node an end node? */
  vector<NiemaNode*> children; /** This node's children */

public:
  /** Public Default Constructor of NiemaNode
   *  PRECONDITION:  Nothing
   *  POSTCONDITION: A new NiemaNode is made with the default conditions
   */
  NiemaNode()
  {
    letter = ' ';
    isEnd = false;
  }

  /** Public Constructor of NiemaNode
   *  PRECONDITION:  Nothing
   *  POSTCONDITION: A new NiemaNode is made
   */
  NiemaNode( char l, bool b )
  {
    letter = l;
    isEnd = b;
  }

  /** Public Deconstructor of NiemaNode
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: This NiemaNode is destroyed
   */
  ~NiemaNode() {}

  /** Return the letter of this NiemaNode
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: This NiemaNode's letter is returned
   */
  char getLetter()
  {
    return letter;
  }

  /** Set the letter of this NiemaNode
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: This NiemaNode's letter is reassigned
   */
  void setLetter( char l )
  {
    letter = l;
  }

  /** Check if this NiemaNode is an end node
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: Return true if this an end node, otherwise false
   */
  bool getIsEnd()
  {
    return isEnd;
  }

  /** Set the "end" bool of this NiemaNode
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: This NiemaNode's "end" bool is reassigned
   */
  void setIsEnd( bool b )
  {
    isEnd = b;
  }

  /** Add the given node as the last of this NiemaNode's children vector
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: The designated node is added to this node's children
   */
  void addChild( NiemaNode* c )
  {
    children.push_back(c);
  }

  /** Return this NiemaNode's "children" vector
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: This NiemaNode's "children" vector is returned
   */
  vector<NiemaNode*> getChildren()
  {
    return children;
  }

  /** Find the child with the given letter
   *  PRECONDITION:  This is a valid NiemaNode
   *  POSTCONDITION: The child with the given letter is returned
   */
  NiemaNode* findChild( char l );
};


/** This is the NiemaTrie class. It is a data structure that is (hopefully)
 *  more efficient at finding words than a set.
 */
class NiemaTrie {
private:
  NiemaNode*   root;      /** The root node of this NiemaTrie */
  unsigned int occupancy; /** The occupancy of this NiemaTrie */

public:
  /** Default Constructor of NiemaTrie
   *  PRECONDITION:  Nothing
   *  POSTCONDITION: A new NiemaTrie is made
   */
  NiemaTrie();

  /** Default Destructor of NiemaTrie
   *  PRECONDITION:  This is a valid NiemaTrie
   *  POSTCONDITION: This NiemaTrie is destroyed
   */
  ~NiemaTrie();

  /** Add the given word to this NiemaTrie
   *  PRECONDITION:  This is a valid NiemaTrie
   *  POSTCONDITION: The given word has been added to the NiemaTrie
   */
  void addWord( string s );

  /** Search for the given word
   *  PRECONDITION:  This is a valid NiemaTrie
   *  POSTCONDITION: Return true if the given word is in this NiemaTrie,
   *                 otherwise false
   */
  bool findWord( string s );
};
#endif // BOGGLEUTIL_H
