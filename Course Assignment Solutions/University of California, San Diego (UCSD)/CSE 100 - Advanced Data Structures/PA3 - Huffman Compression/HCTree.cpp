/******************************************************************************
 * Name:          Alexander Niema Moshiri
 * PID Number:    A09850737
 * Email Address: a1moshir@ucsd.edu
 * Assignment:    PA3 (No Partner)
 * File Name:     HCTree.cpp
 * Description:   This is the overarching Huffman Compress Tree class. It uses
 *                the HCNode class to create a Trie out of nodes that contain
 *                the byte they're keeping track of, a pointer to the 0 (left)
 *                child, and a pointer to the 1 (right) child. This trie will
 *                be used to create the compression program. This is the actual
 *                class code.
 *****************************************************************************/
#include <queue>
#include <stack>
#include <vector>
#include <fstream>
#include "HCNode.hpp"
#include "HCTree.hpp"

#ifndef ASCII
#define ASCII 256
#endif

/*#include "BitInputStream.hpp"
#include "BitOutputStream.hpp"*/

/** Destructor of HCTree
 *  PRECONDITION: This is a valid HCTree
 *  POSTCONDITION: This HCTree is destroyed
 */
HCTree::~HCTree()
{
  /** Delete all HCNodes in leaves, then delete leaves */
  for(int i = 0; i < leaves.size(); i++)
  {
    delete leaves.at(i);
  }
}

/** Use the Huffman algorithm to build a Huffman coding trie.
 *  PRECONDITION: freqs is a vector of ints, such that freqs[i] is 
 *  the frequency of occurrence of byte i in the message.
 *  POSTCONDITION:  root points to the root of the trie,
 *  and leaves[i] points to the leaf node containing byte i.
 */
int HCTree::build(const vector<int>& freqs)
{
  /** Create the Priority Queue */
  priority_queue<HCNode*, vector<HCNode*>, HCNodePtrComp> pq;

  /** For every ASCII symbol... */
  for(int i = 0; i < freqs.size(); i++)
  {
    /** If the current ASCII symbol showed up... */
    if(freqs.at(i) != 0)
    {
      /** Create a new node with its frequency and its symbol */
      HCNode* newNode = new HCNode( freqs.at(i), (unsigned char)i );
      leaves.at(i) = newNode;
      pq.push(newNode);
    }
  }

  /** While there is more than 1 tree in the forest */
  while(pq.size() > 1)
  {
    /** Remove the 2 lowest count trees */
    HCNode* lowest = pq.top();
    pq.pop();
    HCNode* nextLowest = pq.top();
    pq.pop();

    /** Combine these two trees into a new tree (summing their counts) */
    int sum = lowest->count + nextLowest->count;
    HCNode* newTree = new HCNode(sum,0);

    /** Set pointers */
    newTree->c0   = lowest;
    newTree->c1   = nextLowest;
    lowest->p     = newTree;
    lowest->is0   = true;
    nextLowest->p = newTree;
    nextLowest->is0 = false;

    /** Insert the new tree back into the forest */
    pq.push(newTree);
  }

  /** Set the last tree in the forest to root */
  root = pq.top();
  pq.pop();
}

/** Write to the given BitOutputStream
 *  the sequence of bits coding the given symbol.
 *  PRECONDITION: build() has been called, to create the coding
 *  tree, and initialize root pointer and leaves vector.
 */
int HCTree::encode(byte symbol, BitOutputStream& out) const
{
  /** Find the symbol's leaf */
  HCNode* myNode = leaves.at((int)symbol);

  /** Push all nodes' 1's or 0's to stack */
  std::stack<int> pathStack;

  /** Keep going until reach root */
  do
  {
    if(myNode->is0)
    {
      pathStack.push(0);
    }
    else
    {
      pathStack.push(1);
    }
    myNode = myNode->p; /** Go up the tree */
    if(myNode == 0)
    {
      break;
    }
  }while(myNode->p != 0);
 string str="";

  /** Now pop all items and print */
  int numPrints = 0;
  while(!pathStack.empty())
  {
    out.writeBit(pathStack.top());
    str+=to_string(pathStack.top());
    numPrints++;
    pathStack.pop();
  }

  /** Return number of prints (which == number of bits) */
  return numPrints;
}

/** Write to the given ofstream
 *  the sequence of bits (as ASCII) coding the given symbol.
 *  PRECONDITION: build() has been called, to create the coding
 *  tree, and initialize root pointer and leaves vector.
 *  THIS METHOD IS USEFUL FOR THE CHECKPOINT BUT SHOULD NOT 
 *  BE USED IN THE FINAL SUBMISSION.
 *  POSTCONDITION: writes the given symbol as its encoded equivalent,
 *  and returns the size of the encoded symbol
 */
int HCTree::encode(byte symbol, ofstream& out) const
{
  /** Find the symbol's leaf */
  HCNode* myNode = leaves.at((int)symbol);

  /** Push all nodes up to root into stack */
  std::stack<HCNode*> nodeStack;
  nodeStack.push(myNode);

  /** While we haven't reached the root yet... */
  while(nodeStack.top()->p != 0)
  {
    nodeStack.push( nodeStack.top()->p );
  }

  /** Get rid of root node */
  nodeStack.pop();

  /** Now that stack is populated, pop all items and print */
  int numPrints = 0;
  while(!nodeStack.empty())
  {
    HCNode* top = nodeStack.top();
    if(top->is0)
    {
      out << '0';
    }
    else
    {
      out << '1';
    }
    numPrints++;
    nodeStack.pop();
  }

  /** Return numPrints * size of char in bits (1 byte = 8 bits) */
  return 8*numPrints;
}

/** Return symbol coded in the next sequence of bits from the stream.
 *  PRECONDITION: build() has been called, to create the coding
 *  tree, and initialize root pointer and leaves vector.
 */
int HCTree::decode(BitInputStream& in) const
{
  HCNode* curr = root; /** Holder HCNode* */
  int bit;             /** Holder bit */
  bool only = false;   /** Is this the only character? */

  /** If 1 character, root is the only character */
  if(curr->c0 == 0 && curr->c1 == 0)
  {
    only = true;
  }

  while(1)
  {
    /** Read the next bit */
    bit = in.readBit();

    /** If only 1 character (root), return right away */
    if(only)
    {
      return (int)(curr->symbol);
    }

    /** If no more bits to read, return -1 */
    if(bit == -1)
    {
      return -1;
    }

    /** If next path is 0, go to '0' child */
    else if(bit == 0)
    {
      curr = curr->c0;
    }

    /** If next path is 1, go to '1' child */
    else if(bit == 1)
    {
      curr = curr->c1;
    }

    /** If neither, fatal error */
    else
    {
      cerr << "Something went wrong during compression!!!" << endl;
      exit(1);
    }

    /** If reached leaf, return its value */
    if((curr == 0) || (curr->c0 == 0 && curr->c1 == 0))
    {
      return (int)(curr->symbol);
    }
  }
}

/** Return the symbol coded in the next sequence of bits (represented as 
 *  ASCII text) from the ifstream.
 *  PRECONDITION: build() has been called, to create the coding
 *  tree, and initialize root pointer and leaves vector.
 *  THIS METHOD IS USEFUL FOR THE CHECKPOINT BUT SHOULD NOT BE USED
 *  IN THE FINAL SUBMISSION.
 */
int HCTree::decode(ifstream& in) const
{
  HCNode* curr = root; /** Holder HCNode* */
  unsigned char ch;    /** Holder char */
  while(1)
  {
    ch = in.get();
    if(!in.good())
    {
      return -1;
    }

    /** If next path is 0, go to '0' child */
    if(ch == '0')
    {
      curr = curr->c0;
    }

    /** If next path is 1, go to '1' child */
    else if(ch == '1')
    {
      curr = curr->c1;
    }

    /** If neither, fatal error */
    else
    {
      cerr << "Something went wrong during compression!!!" << endl;
      exit(1);
    }

    /** If reached leaf, return its value */
    if(curr->c0 == 0 && curr->c1 == 0)
    {
      return (int)(curr->symbol);
    }
  }
}
