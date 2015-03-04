/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA4 (No Partner)
 * File Name:     boggleutil.cpp
 * Description:   This is the Boggle Utility file. It contains all supporting
 *                methods for BogglePlayer. This is the source file.
 *****************************************************************************/
#include "boggleutil.h"

/** Find the child with the given letter
 *  PRECONDITION:  This is a valid NiemaNode
 *  POSTCONDITION: The child with the given letter is returned
 */
NiemaNode* NiemaNode::findChild( char l )
{
  /** For every child: */
  for(unsigned int i = 0; i < children.size(); ++i)
  {
    NiemaNode* temp = children.at(i);

    /** If we have a match, return it */
    if(temp->letter == l)
    {
      return temp;
    }
  }

  /** If we didn't find it, return NULL */
  return NULL;
}


/** Default Constructor of NiemaTrie
 *  PRECONDITION:  Nothing
 *  POSTCONDITION: A new NiemaTrie is made
 */
NiemaTrie::NiemaTrie()
{
  root = new NiemaNode();
  occupancy = 0;
}

/** Default Destructor of NiemaTrie
 *  PRECONDITION:  This is a valid NiemaTrie
 *  POSTCONDITION: This NiemaTrie is destroyed
 */
NiemaTrie::~NiemaTrie()
{
  /** FILL THIS OUT */
}

/** Add the given word to this NiemaTrie
 *  PRECONDITION:  This is a valid NiemaTrie
 *  POSTCONDITION: The given word has been added to the NiemaTrie
 */
void NiemaTrie::addWord( string s )
{
  bool newWord = false; /** Has a new word been added? */

  /** Start at root */
  NiemaNode* curr = root;

  /** If empty string, set current to end */
  if(s.length() == 0)
  {
    curr->setIsEnd(true);
    return;
  }

  /** Otherwise: */
  for(unsigned int i = 0; i < s.length(); ++i)
  {
    /** Find the ith letter child */
    NiemaNode* child = curr->findChild(s[i]);

    /** If found, go to child */
    if(child)
    {
      curr = child;
    }

    /** If not found, create a new child with the needed letter */
    else
    {
      newWord = true;
      NiemaNode* temp = new NiemaNode(s[i],false);
      curr->addChild(temp);
      curr = temp;
    }

    /** If reached end of the word, set current node as an end node */
    if(i == s.length() - 1)
    {
      curr->setIsEnd(true);
    }
  }

  /** If a new word was added, increment occupancy */
  if(newWord)
  {
    occupancy++;
  }
}

/** Search for the given word
 *  PRECONDITION:  This is a valid NiemaTrie
 *  POSTCONDITION: Return true if the given word is in this NiemaTrie,
 *                 otherwise false
 */
bool NiemaTrie::findWord( string s )
{
  /** Start at root */
  NiemaNode* curr = root;

  while(curr != NULL)
  {
    /** For all letters in string s: */
    for(unsigned int i = 0; i < s.length(); ++i)
    {
      /** Find the child with that letter */
      NiemaNode* temp = curr->findChild(s[i]);

      /** If the child exists, go to that child */
      if(temp)
      {
        curr = temp;
      }

      /** Otherwise, word not found! */
      else
      {
        return false;
      }
    }

    /** If the last node is an end node, word is found */
    return curr->getIsEnd();
  }

  /** If didn't even get into loop, return false */
  return false;
}
